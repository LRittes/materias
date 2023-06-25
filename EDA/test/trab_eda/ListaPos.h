
#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#define SIM 1
#define NAO 0
#define SUCESSO 1
#define FRACASSO 0

typedef struct infoLP
{
    int col;
    int lin;
} InfoLP;

typedef struct noLP
{
    InfoLP dados;
    struct noLP *prox;
} NoLP;

typedef struct DescListaPos
{
    int tamInfo;
    NoLP *inicio;
} DescListaPos;

//======================APLICACAO=====================

//======================FILA=====================
DescListaPos *criaListPos();
int tamanhoDaLista(DescListaPos *p);
int reinicia(DescListaPos *p);
DescListaPos *destroi(DescListaPos *p);

int insereNaPoslog(int posLog, InfoLP *novo, DescListaPos *p);
int insereNovoUltimo(InfoLP *reg, DescListaPos *p);
int insereNovoPrimeiro(InfoLP *reg, DescListaPos *p);

int buscaOultimo(InfoLP *reg, DescListaPos *p);
int buscaOprimeiro(InfoLP *reg, DescListaPos *p);
int buscaNaPoslog(int posLog, InfoLP *reg, DescListaPos *p);

int removeDaPoslog(int Poslog, InfoLP *reg, DescListaPos *p);
int removeOultimo(InfoLP *reg, DescListaPos *p);
int removeOprimeiro(InfoLP *reg, DescListaPos *p);

int testaVazia(DescListaPos *p);
int inverte(DescListaPos *p);
DescListaPos *destroi(DescListaPos *p);
