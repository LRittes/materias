#define LIVRE 0
#define OCUPADA 1
#define FRACASSO 0
#define SUCESSO 1
#define FALSO 0
#define VERDADEIRO 1
#define NAO 0
#define SIM 1
#define MAIOR 1
#define IGUAL 0
#define MENOR -1
#include "stdlib.h"
#include "stdio.h"
#include "string.h"

typedef struct
{
	int idade;
	char nome[30];
}info;

struct noFila
{
	info dados;
	struct noFila *atras;
	struct noFila *defronte;
};

struct descF
{
	struct noFila *frente;
	struct noFila *cauda;
	struct noFila *refMovel;
	int tamInfo;
};

struct descF *cria();

int insereNaFila(info *novo, struct descF *fila);

int tamanhoDaFila(struct descF *fila);

int reinicia(struct descF *fila);

struct descF *destroi(struct descF *fila);

int buscaNaCauda(info *ele, struct descF *fila);

int buscaNaFrente(info *ele, struct descF *fila);

int buscaNoRefMovel(info *ele, struct descF *fila);

int retiraDaFila(info *ele, struct descF *fila);

int testaVazia(struct descF *fila);

int inverte(struct descF *fila);