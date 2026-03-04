#include <stdio.h>
#include <stdlib.h>

typedef struct Doc {
    int id;
    int paginas;
    struct Doc *prox;
} Documento;

typedef struct {
    Documento *entrada; // Ponteiro auxiliar A
    Documento *saida;   // Ponteiro auxiliar B
} Spooler;

void inicializar(Spooler *s) {
    s->entrada = NULL;
    s->saida = NULL;
}

void mostrarStatus(Spooler *s) {
    Documento *aux = s->saida;
    printf("\n--- STATUS DO SPOOLER ---\n");
    if (aux == NULL) {
        printf("Nenhum documento aguardando.\n");
    } else {
        while (aux != NULL) {
            printf("[ID: %d | Pgs: %d] -> ", aux->id, aux->paginas);
            aux = aux->prox;
        }
        printf("NULL\n");
    }
    printf("-------------------------\n");
}

// --- SUA TAREFA: Identifique a lógica e implemente ---

// 1. Armazena um novo documento na estrutura
void receberDocumento(Spooler *s, int id, int paginas) {
    // TODO: Alocação e lógica de ponteiros para manter a ordem cronológica
    
    Documento* novo_doc = (Documento*) malloc(sizeof(Documento));

    if (novo_doc == NULL) 
        exit(1);
    
    novo_doc -> id = id;
    novo_doc -> paginas = paginas;
    
    if(s -> saida == NULL){ // lista vazia
        s -> saida = novo_doc;
        s -> entrada = novo_doc;
        return;
    }
    
    s -> entrada -> prox = novo_doc;
    s -> entrada = novo_doc;
}

// 2. Retira o documento correto para impressão e limpa memória
int processarImpressao(Spooler *s) {
    // TODO: Lógica de remoção baseada na regra de negócio e free()
    
    Documento* doc_remover = s -> saida;
    
    if(doc_remover == NULL) 
        return -1;
    
    int id_remover = doc_remover -> id;

    s -> saida = doc_remover -> prox;
    free(doc_remover);
    
    if(s -> saida == NULL) // lista vazia
        s -> entrada = NULL;
    
    return id_remover;
}

// --- FIM DA SUA TAREFA ---

int main() {
    Spooler impressora;
    inicializar(&impressora);
    
    int opcao, id, pags, idImpresso;

    do {
        printf("\nSISTEMA DE IMPRESSAO:\n");
        printf("1 - Enviar Documento\n");
        printf("2 - Imprimir Proximo\n");
        printf("3 - Ver Status\n");
        printf("0 - Sair\n");
        printf("Escolha: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                printf("ID do doc: ");
                scanf("%d", &id);
                printf("Paginas: ");
                scanf("%d", &pags);
                receberDocumento(&impressora, id, pags);
                break;
            case 2:
                idImpresso = processarImpressao(&impressora);
                if (idImpresso != -1) printf("\n>>> IMPRIMINDO ID: %d <<<\n", idImpresso);
                else printf("\n>>> Nada para imprimir! <<<\n");
                break;
            case 3:
                mostrarStatus(&impressora);
                break;
            case 0: break;
            default: printf("Invalido!\n");
        }
    } while (opcao != 0);

    return 0;
}