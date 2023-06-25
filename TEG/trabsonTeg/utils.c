#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define CLUSTERS 38

float encontrarMenor(float vetDist[][3], int linhas)
{
    float menor = vetDist[0][2];
    for (int i = 0; i < linhas; i++)
    {
        menor = fmin(menor, vetDist[i][2]);
    }
    return menor;
}

float encontrarMaior(float vetDist[][3], int linhas)
{
    float maior = vetDist[0][2];
    for (int i = 0; i < linhas; i++)
    {
        maior = fmax(maior, vetDist[i][2]);
    }
    return maior;
}

int typeFlower(int cluster[][60], int pos)
{
    int countSet = 0;
    int countVirg = 0;
    int countVers = 0;

    for (int c = 0; cluster[pos][c] != NULL; c++)
    {
        if (cluster[pos][c] < 51)
            countSet++;
        else if (cluster[pos][c] < 101)
            countVers++;
        else
            countVirg++;
    }
    if (countSet > countVers && countSet > countVirg)
        return 1;
    if (countVers > countSet && countVers > countVirg)
        return 2;
    return 3;
}

void resetVector(int visited[], int size)
{
    for (int i = 0; i < size; i++)
    {
        visited[i] = 0; // Inicializa todos os vértices como não visitados
    }
}

void resetMatriz(int visited[][2], int size)
{
    for (int i = 0; i < size; i++)
    {
        visited[i][0] = NULL;
        visited[i][1] = NULL;
    }
}

void blancVector(int vector[][50], int size, int marcador)
{
    for (int i = 0; i < size; i++)
    {
        for (int c = 0; c < size; c++)
        {
            vector[i][c] = marcador; // Inicializa todos os vértices como não visitados
        }
    }
}

float distVectors(float vets[4], float vets2[4])
{
    float dist = 0;
    for (int i = 0; i < 4; i++)
    {
        dist += pow((vets[i] - vets2[i]), 2);
    }
    return sqrt(dist);
}
