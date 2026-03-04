#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

const char *metodos[] = {"GET", "POST", "PUT", "DELETE"};
const char *rotas[] = {"/", "/login", "/home", "/api/user", "/checkout", "/products"};
const int codigos[] = {200, 201, 400, 401, 403, 404, 500};

typedef struct req_t {
    int ip;
    char metodo[8];
    char rota[64];
    int cod_status;
    int tempo_resposta;
    time_t timestamp;
} req_t;

req_t gerar_req_aleatoria(){
    req_t requisicao;

    requisicao.ip = rand() % 256;
    requisicao.cod_status = codigos[rand() % 7];
    requisicao.tempo_resposta = 10 + rand() % 491;
    requisicao.timestamp = time(NULL);
    
    strcpy(requisicao.metodo, metodos[rand() % 4]);
    strcpy(requisicao.rota, rotas[rand() % 6]);

    return requisicao;
}