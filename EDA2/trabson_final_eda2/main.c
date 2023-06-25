#include <time.h>
#include <stdlib.h>
#include "utils.c"
#include "avl.c"

int main()
{
    Arvore *a = criar();

    srand(time(NULL));

    for (int x = 0; x < 1000; x++)
    {

        int size = rand() % 100;
        while (size == 0)
            size = rand() % 100;
        int *values = malloc(size * sizeof(int));
        int esforcoAddTotal = 0;
        int esforcoRemoTotal = 0;
        for (int j = 0; j < 10; j++)
        {

            int esforcoAdd = 0;
            for (int i = 0; i < size; i++)
            {
                int item = rand() % 10000;
                for (int w = 0; w < i; w++)
                {
                    if (values[w] == item)
                    {
                        item = rand() % size;
                        w = -1;
                    }
                }
                adicionar(a, item, &esforcoAdd);
                values[i] = item;
            }
            esforcoAddTotal += esforcoAdd;
            // printf("\n");
            // printf("Size: %i - EsforcoAdd: %i\n", size, esforcoAdd);

            int esforcoRemo = 0;
            for (int c = 0; c < size; c++)
            {

                // escolhendo aleatóriamente uma posição do vetor
                int pos = rand() % size;
                // garantindo que a posição ainda não foi visitada
                while (values[pos] == -1)
                {
                    // escolhendo aleatóriamente outra posição do vetor q ñ foi visitada
                    pos = rand() % size;
                }
                // removendo o nó na posição encontrada
                remover(a, values[pos], &esforcoRemo);
                // marcando a posição como visitada
                values[pos] = -1;
            }
            esforcoRemoTotal += esforcoRemo;
            // printf("Size: %i - EsforcoRemo: %i\n", size, esforcoRemo);
        }
        saveData(size, esforcoAddTotal, "dados_avl_add.txt");
        saveData(size, esforcoRemoTotal, "dados_avl_remo.txt");
    }

    return 0;
}