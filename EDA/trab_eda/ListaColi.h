#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "ListaPos.h"
#define SIM 1
#define NAO 0
#define SUCESSO 1
#define FRACASSO 0

typedef char Word;

typedef struct infoLC
{
    DescListaPos *pos;
    char *str;
} InfoLC;

typedef struct noLC
{
    InfoLC dados;
    struct noLC *prox;
} NoLC;

typedef struct DescLC
{
    int tamInfo;
    NoLC *inicio;
} DescLC;

//======================APLICACAO=====================

//======================FILA=====================
DescLC *criaListaLC(int tamInfo);
int tamanhoDaListaC(DescLC *p);
int reiniciaC(DescLC *p);
DescLC *destroiC(DescLC *p);

int insereNaPoslogC(int posLog, InfoLC *novo, DescLC *p);
int insereNovoUltimoC(InfoLC *reg, DescLC *p);
int insereNovoPrimeiroC(InfoLC *reg, DescLC *p);

int buscaOultimoC(InfoLC *reg, DescLC *p);
int buscaOprimeiroC(InfoLC *reg, DescLC *p);
int buscaNaPoslogC(int posLog, InfoLC *reg, DescLC *p);

int removeDaPoslogC(int Poslog, InfoLC *reg, DescLC *p);
int removeOultimoC(InfoLC *reg, DescLC *p);
int removeOprimeiroC(InfoLC *reg, DescLC *p);

int testaVaziaC(DescLC *p);
int inverteC(DescLC *p);
DescLC *destroiC(DescLC *p);

int searchWord(char str[], DescLC *list, InfoLC *destino);
void copyTo(int tamInfo, InfoLC *destino, InfoLC *fonte);
int compareStr(char str1[], char str2[]);