#include <stdio.h>
#include <stdlib.h>

typedef struct Item {
    int codigo;
    float preco;
    struct Item *prox;
} Produto;

void listarCatalogo(Produto *lista) {
    Produto *aux = lista;
    printf("\n--- CATALOGO ATUAL ---\n");
    if (aux == NULL) printf("Estoque Vazio.");
    while (aux != NULL) {
        printf("[Cod: %d | $%.2f] -> ", aux->codigo, aux->preco);
        aux = aux->prox;
    }
    printf("NULL\n----------------------\n");
}

// --- SUA TAREFA: Identifique a lógica e implemente ---

// 1. Inserção otimizada (O(1)). Retorna o novo ponteiro de referência da lista.
Produto* cadastrar(Produto *lista, int codigo, float preco) {
    // TODO: Implemente a inserção

    Produto* novo_produto = (Produto*) malloc(sizeof(Produto));

    if(novo_produto == NULL)
        exit(1);

    novo_produto -> codigo = codigo;
    novo_produto -> preco = preco;
    novo_produto -> prox = lista;

    return novo_produto;
}

// 2. Busca e Remoção por código. Retorna o ponteiro de referência atualizado.
Produto* vender(Produto *lista, int codigo) {
    // TODO: Implemente a busca, remoção (cuidado com meio/inicio/fim) e free
    
    if(lista -> codigo == codigo){ //remover do inicio
        Produto* nova_lista = lista -> prox;

        free(lista);
        return nova_lista;
    }

    Produto* aux = lista;

    while(aux -> prox != NULL){
        if(aux -> prox -> codigo == codigo){
            Produto* produto_remover = aux -> prox;

            aux -> prox = produto_remover -> prox;
            free(produto_remover);

            return lista;
        }

        aux = aux -> prox;
    }
    
    return lista; 
}

// --- FIM DA SUA TAREFA ---

int main() {
    Produto *catalogo = NULL;
    int opcao, cod;
    float preco;

    do {
        printf("\nLOJA ONLINE:\n");
        printf("1 - Cadastrar Produto\n");
        printf("2 - Vender Produto (Baixa no estoque)\n");
        printf("3 - Listar Catalogo\n");
        printf("0 - Sair\n");
        printf("Escolha: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                printf("Codigo: ");
                scanf("%d", &cod);
                printf("Preco: ");
                scanf("%f", &preco);
                catalogo = cadastrar(catalogo, cod, preco);
                break;
            case 2:
                printf("Codigo do produto vendido: ");
                scanf("%d", &cod);
                catalogo = vender(catalogo, cod);
                printf("Processo de venda finalizado.\n");
                break;
            case 3:
                listarCatalogo(catalogo);
                break;
            case 0: break;
            default: printf("Invalido!\n");
        }
    } while (opcao != 0);

    return 0;
}