#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct Node {
    int id;
    int age;
    char name[60];
    struct Node* next;
} Node;

Node* head = NULL;

void clear_buffer(){
    int c;
    while((c = getchar()) != '\n' && c != EOF);
}
void input_string(char* str, size_t size){
    clear_buffer();
    fgets(str, size, stdin);
    str[strcspn(str, "\n")] = '\0';
}

bool is_empty(){
    return head == NULL;
}
void show_data(Node* node){
    printf("| %03d | %-30s | %02d    |\n", 
        node -> id, 
        node -> name,
        node -> age
    );
}
void free_list(){
    if(is_empty())
        return;
    
    Node* current = head;
    while(current){
        Node* next = current -> next;
        
        free(current);
        current = next;
    }

    head = NULL;
}
Node* search_node_by_id(int id){
    if(is_empty())
        return NULL;

    Node* current = head;

    while(current){
        if(current -> id == id)
            return current;

        current = current -> next;
    }

    return NULL;
}

void create_node(){
    Node* new_node = (Node*) malloc(sizeof(Node));

    if(!new_node){
        printf("Erro: Alocacao falhou. Operacao nao concluida.\n\n");
        return;
    }

    printf("Digite o id: ");
    scanf("%d", &new_node -> id);

    printf("Digite o nome: ");
    input_string(new_node -> name, sizeof(new_node -> name));
    
    printf("Digite a idade: ");
    scanf("%d", &new_node -> age);

    new_node -> next = NULL;

    
    if(search_node_by_id(new_node -> id) != NULL){
        printf("Erro. Usuario {id: %d} ja exite. Operacao nao concluida.\n\n", new_node -> id);
        free(new_node);
        return;
    }
        
    new_node -> next = head;
    head = new_node;

    printf("Cadastro realizado com sucesso.\n\n");
}
void list_all(){
    if(is_empty()){
        printf("Lista Vazia.\n\n");
        return;
    }

    printf("| UID | Nome                           | idade |\n");
    Node* current = head;
    while(current){
        show_data(current);
        current = current -> next;
    }

    printf("\n");
}
void search_node(){
    if(is_empty()){
        printf("Erro: Lista Vazia. Impossivel realizar busca.\n\n");
        return;
    }
    
    int target_id = 0;
    
    printf("Digite um id para busca: ");
    scanf("%d", &target_id);
    
    Node* found_node = search_node_by_id(target_id);

    if(found_node){
        printf("| UID | Nome                           | idade |\n");
        show_data(found_node);
    } else {
        printf("Nenhum usuario {id: %d} encontrado\n", target_id);
    }   

    printf("\n");
}
void update_node(){
    if(is_empty()){
        printf("Erro: Lista Vazia. Impossivel atualizar.\n\n");
        return;
    }
    
    int target_id = 0;
    int opt = 0;
    
    printf("Digite um id para atualizar: ");
    scanf("%d", &target_id);
    
    Node* found_node = search_node_by_id(target_id);
    
    if(!found_node){
        printf("Nenhum usuario {id: %d} encontrado\n\n", target_id);
        return;
    }
    
    printf("Selecione o campo para atualizar:\n");
    printf("1 - nome\n");
    printf("2 - idade\n");
    scanf("%d", &opt);
    
    switch(opt){
        case 1:
            printf("Digite um novo nome: ");
            input_string(found_node -> name, sizeof(found_node -> name));
            break;
        case 2:
            printf("Digite uma nova idade: ");
            scanf("%d", &found_node -> age);
            break;
        default:
            printf("Opcao Invalida.\n\n");
            return;
    }

    printf("Usuario {id: %d} atualizado com sucesso.\n\n", target_id);
}
void delete_node(){
    if(is_empty()){
        printf("Erro: Lista vazia. Impossivel remover.\n\n");
        return;
    }
    
    int target_id = 0;
    
    printf("Digite um id para remover: ");
    scanf("%d", &target_id);
    
    Node* current = head;
    Node* prev = NULL;
    
    while(current){
        if(current -> id == target_id)
            break;
        
        prev = current;
        current = current -> next;
    }
    
    if(!current){
        printf("Nenhum usuario {id: %d} encontrado\n\n", target_id);
        return;
    }
    
    if(current == head)
        head = current -> next;
    else
        prev -> next = current -> next;
    free(current);
    
    printf("Registro {id: %d} removido com sucesso.\n\n", target_id);
}

int main(){
    int opt = 0;

    do {
        printf("Menu de Cadastro\n");
        printf("1 - Cadastrar Pessoa.\n");
        printf("2 - Buscar Pessoa.\n");
        printf("3 - Listar Todos.\n");
        printf("4 - Alterar Pessoa.\n");
        printf("5 - Remover Pessoa.\n");
        printf("0 - Sair.\n");

        scanf("%d", &opt);

        printf("\n");

        switch(opt){
            case 1:
                create_node();
                break;
            case 2:
                search_node();
                break;
            case 3:
                list_all();
                break;
            case 4:
                update_node();
                break;
            case 5:
                delete_node();
                break;
        }

    } while (opt);

    free_list();

    printf("Programa Encerrado.");
}