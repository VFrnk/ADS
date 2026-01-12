#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>
#include "logs.c"

#define NUM_PRODUTORES 5
#define NUM_CONSUMIDORES 1
#define TAM_BUFFER 10

#define DELAY_MAX_PRODUTORES_MS 4000 
#define DELAY_MAX_CONSUMIDORES_MS 1000

sem_t s_vazios, s_cheios, s_buffer;

req_t buffer[TAM_BUFFER];
int in = 0, out = 0, usados = 0;

void atualizar_monitor();

void* produtor_rotina(void* args){
    while(1) {
        usleep(1000 * (rand() % DELAY_MAX_PRODUTORES_MS));
        
        req_t ultima_requisicao = gerar_req_aleatoria();

        sem_wait(&s_vazios);
        sem_wait(&s_buffer);

        buffer[in] = ultima_requisicao;
        in = (in + 1) % TAM_BUFFER;
        usados++;

        atualizar_monitor();

        sem_post(&s_buffer);
        sem_post(&s_cheios);
    }
}

void* consumidor_rotina(void* args){
    while(1){
        sem_wait(&s_cheios);
        sem_wait(&s_buffer);
        
        req_t requisicao = buffer[out];
        out = (out + 1) % TAM_BUFFER;
        usados--;

        atualizar_monitor();

        FILE* arquivo = fopen("log.txt", "a");
        fprintf(arquivo, "%ld | %3dms | 192.168.0.%d %s %s %d\n", 
            requisicao.timestamp,
            requisicao.tempo_resposta,
            requisicao.ip,
            requisicao.metodo,
            requisicao.rota, 
            requisicao.cod_status
        );
        fclose(arquivo);

        sem_post(&s_buffer);
        sem_post(&s_vazios);

        usleep(1000 * (rand() % DELAY_MAX_CONSUMIDORES_MS));
    }
}

int main(){
    srand(time(NULL));

    pthread_t produtor[NUM_PRODUTORES];
    pthread_t consumidor[NUM_CONSUMIDORES];

    sem_init(&s_buffer, 0, 1);
    sem_init(&s_vazios, 0, TAM_BUFFER);
    sem_init(&s_cheios, 0, 0);

    for(int i = 0; i < NUM_PRODUTORES; i++)
        pthread_create(&produtor[i], NULL, produtor_rotina, NULL);

    for(int i = 0; i < NUM_CONSUMIDORES; i++)
        pthread_create(&consumidor[i], NULL, consumidor_rotina, NULL);

    for(int i = 0; i < NUM_PRODUTORES; i++)
        pthread_join(produtor[i], NULL);

    for(int i = 0; i < NUM_CONSUMIDORES; i++)
        pthread_join(consumidor[i], NULL);

    return 0;
}

void atualizar_monitor(){
    printf("\033[H\033[J");

    printf("==========================================\n");
    printf("           Monitor do Sistema             \n");
    printf("==========================================\n");

    printf("Buffer: [ ");

    for(int i = 0; i < TAM_BUFFER; i++){
        int ocupado = 0;

        if (usados > 0){
            if (in > out){
                if (i >= out && i < in) ocupado = 1;
            } else {
                if (i >= out || i < in) ocupado = 1;
            }
        }

        if (ocupado)
            printf("\033[1;32m\u25A0 \033[0m"); //verde
        else 
            printf("\033[1;30m\u25A1 \033[0m"); //cinza
    }

    printf("]\n\n");

    
    req_t ultima_requisicao = buffer[(out + usados - 1) % TAM_BUFFER];

    printf("%ld | 192.168.0.%d %s %s %d | %dms\n", 
        ultima_requisicao.timestamp,
        ultima_requisicao.ip,
        ultima_requisicao.metodo,
        ultima_requisicao.rota, 
        ultima_requisicao.cod_status,
        ultima_requisicao.tempo_resposta
    );

    printf("\n");
}