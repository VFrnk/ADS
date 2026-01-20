#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

// --- CONFIGURAÇÕES ---
#define TAM_VOLUME 1024 * 1024     // 1 MB
#define TAM_BLOCO 4096             // 4 KB
#define NOME_ARQUIVO_TAM_MAX 64
#define MAX_BLOCOS_POR_ARQUIVO 10
#define TOTAL_BLOCOS (TAM_VOLUME / TAM_BLOCO)
#define LEN_MAX_CMD 256

// --- ESTRUTURAS DE DADOS ---

typedef enum { TYPE_FILE, TYPE_DIR } NodeTipo;

typedef struct APFS_Node {
    char nome[NOME_ARQUIVO_TAM_MAX];
    NodeTipo tipo;
    size_t tamanho;
    int indices_blocos[MAX_BLOCOS_POR_ARQUIVO]; // Aponta para índices no disco virtual
    
    struct APFS_Node *parent;       // Para navegação (cd ..)
    struct APFS_Node *child;        // Lista de filhos
    struct APFS_Node *next_sibling; // Próximo item na lista
} APFS_Node;

typedef struct {
    unsigned char *disco;
    int bloco_ref_contador[TOTAL_BLOCOS]; // MAPA DE REFERÊNCIAS (0=livre, 1=usado, 2+=clone)
    APFS_Node *raiz;
    APFS_Node *diretorio_atual;         // shell
} APFS_Contexto;

// --- GERENCIAMENTO DE ESPAÇO (Ref Counting & CoW) ---

//procura o primeiro bloco vazio
int alocar_bloco(APFS_Contexto *fs) {
    for (int i = 0; i < TOTAL_BLOCOS; i++) {
        if (fs->bloco_ref_contador[i] == 0) {
            fs->bloco_ref_contador[i] = 1; // Aloca com 1 referência
            return i;
        }
    }
    return -1; // Disco cheio
}

//incrementa no mapa de referencias
void ref_bloco(APFS_Contexto *fs, int index) {
    if (index >= 0 && index < TOTAL_BLOCOS) fs->bloco_ref_contador[index]++;
}

void deref_bloco(APFS_Contexto *fs, int index) {
    if (index >= 0 && index < TOTAL_BLOCOS && fs->bloco_ref_contador[index] > 0)
        fs->bloco_ref_contador[index]--;
}

// --- FUNÇÕES DE NÓS (Arquivos/Pastas) ---

APFS_Node* find_node(APFS_Node *parent, const char *nome) {
    APFS_Node *curr = parent->child;
    while (curr) {
        if (strcmp(curr->nome, nome) == 0) return curr;
        curr = curr->next_sibling;
    }
    return NULL;
}

APFS_Node* create_node(APFS_Contexto *fs, const char *nome, NodeTipo type) {
    if (find_node(fs->diretorio_atual, nome)) {
        printf("Erro: '%s' ja existe.\n", nome);
        return NULL;
    }
    APFS_Node *node = (APFS_Node*) malloc(sizeof(APFS_Node));
    strcpy(node->nome, nome);
    node->tipo = type;
    node->tamanho = 0;
    node->parent = fs->diretorio_atual; // Aponta para o pai atual
    node->child = NULL;
    
    // Insere na lista
    node->next_sibling = fs->diretorio_atual->child;
    fs->diretorio_atual->child = node;

    for(int i=0; i<MAX_BLOCOS_POR_ARQUIVO; i++) node->indices_blocos[i] = -1;
    return node;
}

// --- I/O & CLONAGEM ---

void cmd_write(APFS_Contexto *fs, char *filename, char *data) {
    APFS_Node *node = find_node(fs->diretorio_atual, filename);
    if (!node || node->tipo != TYPE_FILE) { printf("Arquivo invalido.\n"); return; }

    // NOTA: Em um APFS real, se ref_count > 1, aqui faríamos o Copy-on-Write (alocar novo bloco)
    // Para simplificar este shell, assumimos escrita destrutiva ou nova alocação simples.
    
    size_t len = strlen(data);
    int blocks_needed = (len + TAM_BLOCO - 1) / TAM_BLOCO;

    // Liberar blocos antigos antes de escrever novos (simplificação)
    for(int i=0; i<MAX_BLOCOS_POR_ARQUIVO; i++) {
        if (node->indices_blocos[i] != -1) deref_bloco(fs, node->indices_blocos[i]);
        node->indices_blocos[i] = -1;
    }

    int bytes_written = 0;
    for (int i=0; i<blocks_needed && i<MAX_BLOCOS_POR_ARQUIVO; i++) {
        int bid = alocar_bloco(fs);
        if (bid == -1) { printf("Disco cheio!\n"); break; }
        
        node->indices_blocos[i] = bid;
        size_t chunk = (len - bytes_written > TAM_BLOCO) ? TAM_BLOCO : (len - bytes_written);
        memcpy(fs->disco + (bid * TAM_BLOCO), data + bytes_written, chunk);
        bytes_written += chunk;
    }
    node->tamanho = 0;

    while(node != NULL){
        node -> tamanho += bytes_written;
        node = node -> parent;
    }

    printf("Escrito %d bytes em '%s'.\n", bytes_written, filename);
}

void cmd_cat(APFS_Contexto *fs, char *filename) {
    APFS_Node *node = find_node(fs->diretorio_atual, filename);
    if (!node || node->tipo != TYPE_FILE) { printf("Arquivo nao encontrado.\n"); return; }

    printf("--- CONTEUDO DE %s ---\n", filename);
    for (int i=0; i<MAX_BLOCOS_POR_ARQUIVO; i++) {
        int bid = node->indices_blocos[i];
        if (bid != -1) printf("%.*s", TAM_BLOCO, fs->disco + (bid * TAM_BLOCO));
    }
    printf("\n----------------------\n");
}

// CLONAGEM APFS (Instantânea, Zero Espaço)
void cmd_clone(APFS_Contexto *fs, char *src_name, char *dest_name) {
    APFS_Node *src = find_node(fs->diretorio_atual, src_name);
    if (!src || src->tipo != TYPE_FILE) { printf("Origem invalida.\n"); return; }

    APFS_Node *dest = create_node(fs, dest_name, TYPE_FILE);
    if (!dest) return;

    dest->tamanho = src->tamanho;
    
    // Copia apenas os PONTEIROS e incrementa REFERÊNCIAS
    for (int i=0; i<MAX_BLOCOS_POR_ARQUIVO; i++) {
        int bid = src->indices_blocos[i];
        dest->indices_blocos[i] = bid;
        if (bid != -1) ref_bloco(fs, bid);
    }
    printf("Clone criado! Blocos compartilhados. Espaco extra usado: 0.\n");
}

void cmd_rm(APFS_Contexto *fs, char *nome) {
    APFS_Node *curr = fs->diretorio_atual->child;
    APFS_Node *prev = NULL;

    while (curr) {
        if (strcmp(curr->nome, nome) == 0) {
            // Remove da lista
            if (prev) prev->next_sibling = curr->next_sibling;
            else fs->diretorio_atual->child = curr->next_sibling;

            // Decrementa referências
            if (curr->tipo == TYPE_FILE) {
                for (int i=0; i<MAX_BLOCOS_POR_ARQUIVO; i++) {
                    if (curr->indices_blocos[i] != -1) deref_bloco(fs, curr->indices_blocos[i]);
                }
            }
            // (Nota: Deletar diretórios recursivamente omitido para brevidade)
            
            free(curr);
            printf("Item '%s' removido.\n", nome);
            return;
        }
        prev = curr;
        curr = curr->next_sibling;
    }
    printf("Nao encontrado.\n");
}

// --- SHELL UTILS ---

void print_status(APFS_Contexto *fs) {
    int used = 0;
    for(int i=0; i<TOTAL_BLOCOS; i++) if(fs->bloco_ref_contador[i] > 0) used++;
    printf("[STATUS] Blocos Usados: %d / %d | Livres: %d\n", used, TOTAL_BLOCOS, TOTAL_BLOCOS - used);
}

void cmd_ls(APFS_Contexto *fs) {
    APFS_Node *curr = fs->diretorio_atual->child;
    printf("Diretorio: %s\n", fs->diretorio_atual->nome);
    if (!curr) printf("  (vazio)\n");
    while (curr) {
        printf("  %s\t%s\t(%zu bytes)\n", 
            (curr->tipo == TYPE_DIR ? "<DIR>" : "<FILE>"), 
            curr->nome, curr->tamanho);
        curr = curr->next_sibling;
    }
}

// --- MAIN LOOP ---

int main() {
    APFS_Contexto *fs = malloc(sizeof(APFS_Contexto));
    fs->disco = calloc(TAM_VOLUME, 1);
    memset(fs->bloco_ref_contador, 0, sizeof(fs->bloco_ref_contador));
    
    // Setup raiz
    fs->raiz = malloc(sizeof(APFS_Node));
    strcpy(fs->raiz->nome, "/");
    fs->raiz->tipo = TYPE_DIR;
    fs->raiz->child = NULL; fs->raiz->parent = NULL; fs->raiz->next_sibling = NULL;
    fs->diretorio_atual = fs->raiz;

    char input[LEN_MAX_CMD];
    char *cmd, *arg1, *arg2;

    printf("=== APFS SYMBOLIC SHELL ===\n");
    printf("Comandos: ls, mkdir [nome], touch [nome], write [arq] [texto], \n");
    printf("          cat [arq], cp [orig] [dest] (CLONE), rm [nome], status, exit\n");

    while (true) {
        printf("\nAPFS:%s > ", fs->diretorio_atual->nome);
        if (!fgets(input, LEN_MAX_CMD, stdin)) break;
        
        // Remove newline
        input[strcspn(input, "\n")] = 0;

        cmd = strtok(input, " ");
        if (!cmd) continue;
        arg1 = strtok(NULL, " ");
        arg2 = strtok(NULL, ""); // Pega o resto da linha para texto

        if (strcmp(cmd, "exit") == 0) break;
        else if (strcmp(cmd, "ls") == 0) cmd_ls(fs);
        else if (strcmp(cmd, "status") == 0) print_status(fs);
        else if (strcmp(cmd, "mkdir") == 0) {
            if(arg1) create_node(fs, arg1, TYPE_DIR);
            else printf("Uso: mkdir <nome>\n");
        }
        else if (strcmp(cmd, "touch") == 0) {
            if(arg1) create_node(fs, arg1, TYPE_FILE);
            else printf("Uso: touch <nome>\n");
        }
        else if (strcmp(cmd, "write") == 0) {
            if(arg1 && arg2) cmd_write(fs, arg1, arg2);
            else printf("Uso: write <arquivo> <texto>\n");
        }
        else if (strcmp(cmd, "cat") == 0) {
            if(arg1) cmd_cat(fs, arg1);
        }
        else if (strcmp(cmd, "cp") == 0) {
            if(arg1 && arg2) cmd_clone(fs, arg1, arg2); // O "cp" aqui é um CLONE
            else printf("Uso: cp <origem> <novo_clone>\n");
        }
        else if (strcmp(cmd, "rm") == 0) {
            if(arg1) cmd_rm(fs, arg1);
        }
        else if (strcmp(cmd, "cd") == 0) {
            if (!arg1) continue;
            if (strcmp(arg1, "..") == 0) {
                if (fs->diretorio_atual->parent) fs->diretorio_atual = fs->diretorio_atual->parent;
            } else {
                APFS_Node *target = find_node(fs->diretorio_atual, arg1);
                if (target && target->tipo == TYPE_DIR) fs->diretorio_atual = target;
                else printf("Diretorio nao encontrado.\n");
            }
        }
        else printf("Comando desconhecido.\n");
    }

    // Limpeza de memória omitida para brevidade
    free(fs->disco);
    free(fs);
    return 0;
}