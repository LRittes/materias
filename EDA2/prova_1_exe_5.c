#include <stdio.h>
#include <stdlib.h>

int comparador(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

int main()
{
    int n;
    printf("Quantas luvas: ");
    scanf("%i", &n);

    int entrada[n];
    for (int i = 0; i < n; i++)
    {
        printf("Insira o tamanha da luva na posição %i: ", i);
        scanf("%i", &entrada[i]);
    }
    int aux[n], sum = 0;

    qsort(entrada, n, sizeof(int), comparador);
    for (int i = 0; i < n; i++)
    {
        if (entrada[i] == entrada[i + 1])
        {
            aux[sum] = entrada[i];
            sum++;
            i++;
        }
    }
    if (sum > 0)
    {
        printf("%i par(es) de luva(s): ", sum);

        for (int i = 0; i < sum; i++)
        {
            printf("Tamanho %i ", aux[i]);
        }
    }
    printf("\n");

    return 0;
}