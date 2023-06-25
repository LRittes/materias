#include <stdio.h>

void saveData(int size, int esforçoTotal, char *nomeArquivo)
{
    FILE *arquivo;
    arquivo = fopen(nomeArquivo, "a");
    if (arquivo == NULL)
    {
        printf("Erro ao abrir o arquivo.");
    }
    fprintf(arquivo, "%i %i\n", size, esforçoTotal / 10);

    fclose(arquivo);
}