#include <stdio.h>
#include <stdlib.h>

#include "sist.h"

int main()
{

    ABB *arv = criaABB(sizeof(InfoMain));
    sistema(arv);
    char word[50], wordC[50];

    printf("Busca qual palavra procura:\n");
    scanf("%s", word);
    int value = calculateIntStr(word);
    InfoMain *resp = malloc(sizeof(InfoMain));
    if (buscaABB(arv, resp, value) == 0)
    {
        printf("não Encontrou\n");
        return 0;
    }
    else
    {

        NoLC *aux = (*resp).listColi->inicio;
        for (int i = 0; i < tamanhoDaListaC((*resp).listColi); i++)
        {

            if (!compareStr((*aux).dados.str, word) == 1)
            {
                printf("Palavra não encontrada!\n");
                return 0;
            }
            aux = aux->prox;
        }
    }

    NoLP *auxLP = (*resp).listColi->inicio->dados.pos->inicio;
    printf("Coluna  -  Linha\n");
    for (int i = 0; i < tamanhoDaLista((*resp).listColi->inicio->dados.pos); i++)
    {
        printf("%i     -     %i \n", (*auxLP).dados.col, (*auxLP).dados.lin);
        auxLP = auxLP->prox;
    }

    return 0;
}