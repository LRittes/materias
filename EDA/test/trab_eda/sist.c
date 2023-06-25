#include <stdio.h>
#include "sist.h"

void sistema(ABB *arv)
{

    FILE *arq;
    char Linha[100];
    char *result;
    int c;

    // Abre um arquivo TEXTO para LEITURA
    arq = fopen("ArqTeste2.txt", "rt");

    if (arq == NULL) // Se houve erro na abertura
    {
        printf("Problemas na abertura do arquivo\n");
    }

    c = 1;
    while (!feof(arq))
    {
        // Lê uma linha (inclusive com o '\n')
        result = fgets(Linha, 150, arq); // o 'fgets' lê até 99 caracteres ou até o '\n'

        int end = 0, init = 0, time = howManySpaceIn(result);

        for (int i = 0; i <= time; i++)
        {
            char *tmp = malloc(50 * sizeof(char));
            int initWord = init;

            // Pegar a palavra que entre o init e o end
            getWord(&end, &init, result, tmp, arv);

            // Calcular o valor da palavra
            int t = calculateIntStr(tmp);

            // printf("%s %i \n", tmp, t);

            InfoMain aux = {t, criaListaLC(sizeof(InfoLC))};

            InfoMain *perc = malloc(sizeof(InfoMain));
            insereABB(arv, &aux);

            buscaABB(arv, perc, t);

            InfoLC *auxLC = malloc(sizeof(InfoLC));
            if (searchWord(tmp, (*perc).listColi, auxLC))
                ;
            else
            {
                InfoLC auxLC2 = {criaListPos(), tmp};
                memcpy(auxLC, &auxLC2, sizeof(InfoLC));
                insereNaPoslogC(tamanhoDaListaC((*perc).listColi), auxLC, (*perc).listColi);
            }

            InfoLP auxLP = {initWord, c};

            insereNaPoslog(tamanhoDaLista((*auxLC).pos), &auxLP, (*auxLC).pos);

            // NoLP *auxFor = (*auxLC).pos->inicio;
            // for (int i = 0; i < tamanhoDaLista((*auxLC).pos); i++)
            // {
            //     printf("%i - %i\n\n", auxFor->dados.col, auxFor->dados.lin);
            //     auxFor = auxFor->prox;
            // }
        }
        if (result) // Se foi possível ler
            c++;
    }
    fclose(arq);
}
