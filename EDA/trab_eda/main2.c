#include <stdio.h>
#include "utils.h"

void imprime(NoABB *raiz, int nivel);

int main()
{

    ABB *arv = criaABB(sizeof(InfoMain));

    InfoMain info[6] = {{20}, {5}, {90}, {2}, {55}, {15}};
    insereABB(arv, &info[0]);
    insereABB(arv, &info[1]);
    // insereABB(arv, &info[1]);

    // for (int i = 0; i < 6; i++)
    // {
    // }
    imprime(arv->raiz, 1);

    return 0;
}

void imprime(NoABB *raiz, int nivel)
{
    int i;
    if (raiz != NULL)
    {
        imprime(raiz->dir, nivel + 1);
        printf("\n\n");

        for (i = 0; i < nivel; i++)
            printf("\t");

        printf("%d", raiz->dados.identificador);
        imprime(raiz->esq, nivel + 1);
    }
}