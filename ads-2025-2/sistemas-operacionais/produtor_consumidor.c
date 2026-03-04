#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdbool.h>
#include <time.h>

#define NUM_PRODUTORES 1
#define NUM_CONSUMIDORES 1
#define TAM_BUFFER 5

sem_t s_vaga, s_item, s_buffer;

int buffer[TAM_BUFFER];
int in = 0, out = 0;

void* produtor(void* args){
    int id = (int)(long) args + 1;

    while(true){
        int pause = rand() % 4;
        sleep(pause);

        sem_wait(&s_vaga);
        sem_wait(&s_buffer);

        int item = rand() % 1000;
        buffer[in] = item;
        in = (in + 1) % TAM_BUFFER;

        sem_post(&s_buffer);
        sem_post(&s_item);

        printf("p%d produziu %d\n", id, item);
    }
}

void* consumidor(void* args){
    int id = (int)(long) args + 1;

    while(true){
        sem_wait(&s_item);
        sem_wait(&s_buffer);

        int item = buffer[out];
        out = (out + 1) % TAM_BUFFER;

        sem_post(&s_buffer);
        sem_post(&s_vaga);

        printf("                    c%d consumiu %d\n", id, item);

        int pause = rand() % 4;
        sleep(pause);
    }
}

int main(){
    pthread_t threads_produtoras[NUM_PRODUTORES];
    pthread_t threads_consumidoras[NUM_CONSUMIDORES];

    sem_init(&s_buffer, 0, 1);
    sem_init(&s_vaga, 0, TAM_BUFFER);
    sem_init(&s_item, 0, 0);

    srand(time(NULL));

    for(int i = 0; i < NUM_PRODUTORES; i++)
        pthread_create(&threads_produtoras[i], NULL, produtor, (void*)(long)i);

    for(int i = 0; i < NUM_CONSUMIDORES; i++)
        pthread_create(&threads_consumidoras[i], NULL, consumidor, (void*)(long)i);

    for(int i = 0; i < NUM_PRODUTORES; i++)
        pthread_join(threads_produtoras[i], NULL);

    for(int i = 0; i < NUM_CONSUMIDORES; i++)
        pthread_join(threads_consumidoras[i], NULL);
}