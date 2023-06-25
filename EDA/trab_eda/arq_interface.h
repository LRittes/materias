#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "ListaColi.h"
#define SIM 1
#define NAO 0
#define SUCESSO 1
#define FRACASSO 0

typedef int tipoChave;

typedef struct infoMain
{
    tipoChave identificador;
    // DescLC *listColi;
} InfoMain;

typedef struct noABB
{
    InfoMain dados;
    int altura;
    struct noABB *esq;
    struct noABB *dir;
} NoABB;

typedef struct ABB
{
    NoABB *raiz;
    int tamInfo;
} ABB;

//======================APLICACAO=====================

void menu(ABB *p);
//======================ABB=====================

void apagaNoABB(NoABB *p);

int calcNumFolhas(NoABB *p);

ABB *criaABB(int tamInfo);

ABB *destroiABB(ABB *p);

void reiniciaABB(ABB *p);

int buscaABB(ABB *pa, InfoMain *destino, int chaveDeBusca);

int insereABB(ABB *p, InfoMain *novoReg);

int removeABB(ABB *p, int chaveDeBusca, InfoMain *copia);

int testaVaziaABB(ABB *p);

int numFolhas(ABB *p);

int emOrdem(ABB *pa);

int posOrdem(ABB *pa);

int preOrdem(ABB *pa);

int maior(int a, int b);

int alturaNo(NoABB *no);

int fatorBalanceamento(NoABB *no);

NoABB *rotaEsq(NoABB *no);

NoABB *rotaDir(NoABB *no);

NoABB *rotaDirEsq(NoABB *no);

NoABB *rotaEsqDir(NoABB *no);

NoABB *balancear(NoABB *no);