#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char descricao[50];
    struct Node *link; // Link para o restante do histórico
} Acao;

typedef struct {
    Acao *ref_principal; // Ponto de acesso à estrutura
} Historico;

void inicializar(Historico *h) {
    h->ref_principal = NULL;
}

void verHistorico(Historico *h) {
    Acao *aux = h->ref_principal;
    printf("\n--- HISTORICO DE ACOES ---\n");
    if (aux == NULL) printf("Historico Vazio.\n");
    while (aux != NULL) {
        printf("['%s'] \n  |\n  v\n", aux->descricao);
        aux = aux->link;
    }
    printf("NULL\n--------------------------\n");
}

// --- SUA TAREFA: Identifique a lógica e implemente ---

// 1. Guarda uma nova ação no histórico
void registrarAcao(Historico *h, char *texto) {
    // TODO: Alocação e manipulação do ponteiro principal

    Acao* nova_acao = (Acao*) malloc(sizeof(Acao));

    if(nova_acao == NULL)
        exit(1);

    strcpy(nova_acao -> descricao, texto);

    nova_acao -> link = h -> ref_principal;
    h -> ref_principal = nova_acao;
}

// 2. Reverte a ação conforme a regra de negócio
void desfazerAcao(Historico *h) {
    if (h->ref_principal == NULL) {
        printf("Nada a desfazer.\n");
        return;
    }
    // TODO: Remover o item correto, printar e liberar memória

    Acao* acao_remover = h -> ref_principal;
    h -> ref_principal = acao_remover -> link;

    printf("%s\n", acao_remover -> descricao);

    free(acao_remover);
}

// --- FIM DA SUA TAREFA ---

int main() {
    Historico editor;
    inicializar(&editor);
    
    int opcao;
    char buffer[50];

    do {
        printf("\nEDITOR DE TEXTO (CTRL+Z):\n");
        printf("1 - Executar Acao (Digitar/Apagar)\n");
        printf("2 - Desfazer Ultima Acao\n");
        printf("3 - Ver Historico\n");
        printf("0 - Sair\n");
        printf("Escolha: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                printf("Descricao da acao: ");
                scanf(" %[^\n]", buffer);
                registrarAcao(&editor, buffer);
                break;
            case 2:
                printf("\n>>> DESFAZENDO... <<<\n");
                desfazerAcao(&editor);
                break;
            case 3:
                verHistorico(&editor);
                break;
            case 0: break;
            default: printf("Invalido!\n");
        }
    } while (opcao != 0);

    return 0;
}