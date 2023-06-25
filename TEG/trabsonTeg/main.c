#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "dfs.c"
#include "utils.c"

int main()
{
  FILE *arquivo;
  char linha[100];
  char *token;

  arquivo = fopen("IrisDataset.csv", "r"); // abre o arquivo em modo leitura

  if (arquivo == NULL)
  {
    printf("Erro ao abrir o arquivo1!\n");
    exit(1);
  }

  int c = 0;
  float vets[150][4];
  while (fgets(linha, 100, arquivo))
  { // lê uma linha do arquivo
    token =
        strtok(linha, ","); // separa a linha em campos separados por vírgula

    int i = 0;

    while (token != NULL)
    { // lê cada campo
      vets[c][i] = atof(token);
      token = strtok(NULL, ",");
      i++;
    }
    c++;
  }
  fclose(arquivo); // fecha o arquivo

  float vetDist[12000][3];
  int totalLines = 0;

  // Calculando a distancia de todos os vetores com todos os vetores
  for (int j = 0; j < 150; j++)
  {
    for (int s = j + 1; s < 150; s++)
    {
      vetDist[totalLines][0] = j + 1;
      vetDist[totalLines][1] = s + 1;
      vetDist[totalLines][2] = distVectors(vets[j], vets[s]);
      totalLines++;
    }
  }

  float menor = encontrarMenor(vetDist, totalLines);
  float maior = encontrarMaior(vetDist, totalLines);

  float vetNorms[totalLines][3];
  for (int j = 0; j < totalLines; j++)
  {
    vetNorms[j][0] = vetDist[j][0];
    vetNorms[j][1] = vetDist[j][1];
    vetNorms[j][2] = (vetDist[j][2] - menor) / (maior - menor);
  }

  int matAdj[150][150];
  int countToMat = 0;
  for (int l = 0; l < 150; l++)
  {
    for (int c = 0; c < 150; c++)
    {
      matAdj[l][c] = vetNorms[countToMat][2] <= 0.2 && l != c ? 1 : 0;
      countToMat++;
    }
  }

  int vetMarca[150];
  for (int i = 0; i < 150; i++)
  {
    vetMarca[i] = 0;
  }

  float vetLimiar[totalLines][3];
  int count = 0;
  for (int i = 0; i < totalLines; i++)
  {
    if (vetNorms[i][2] <= 0.05)
    {
      vetLimiar[count][0] = vetNorms[i][0];
      vetLimiar[count][1] = vetNorms[i][1];
      vetLimiar[count][2] = vetNorms[i][2];
      count++;
    }
  }

  // FILE *arquivo2;
  // arquivo2 = fopen("grafo.txt", "a");
  // if (arquivo2 == NULL)
  // {
  //   printf("Erro ao abrir o arquivo2.");
  //   return 1;
  // }
  // fprintf(arquivo2, "%i\n", count);
  // for (int i = 0; i < count; i++)
  // {
  //   fprintf(arquivo2, "%i,%i", (int)vetLimiar[i][0], (int)vetLimiar[i][1]);
  //   fprintf(arquivo2, "\n");
  // }
  // fclose(arquivo2);

  // Cria um array de listas de adjacência para representar o grafo
  struct Node *graph[MAX_SIZE];
  for (int i = 0; i < MAX_SIZE; i++)
  {
    graph[i] = NULL;
  }

  FILE *arquivo2;
  char linha2[100];
  char *token2;

  arquivo2 = fopen("grafo.txt", "r"); // abre o arquivo em modo leitura

  if (arquivo2 == NULL)
  {
    printf("Erro ao abrir o arquivo5!\n");
    exit(1);
  }

  int v = 0;
  int vets2[305][2];
  while (fgets(linha2, 100, arquivo2))
  { // lê uma linha do arquivo
    if (v != 0)
    {

      token2 =
          strtok(linha2, ","); // separa a linha em campos separados por vírgula

      int i = 0;

      while (token2 != NULL)
      { // lê cada campo
        vets2[v][i] = (int)atof(token2);
        token2 = strtok(NULL, ",");
        i++;
      }
    }
    v++;
  }
  fclose(arquivo2); // fecha o arquivo

  for (int j = 1; j < 305; j++)
  {
    addEdge(graph, vets2[j][1], vets2[j][0]);
  }

  int visited[MAX_SIZE];
  resetVector(visited, MAX_SIZE);

  for (int w = 1; w < MAX_SIZE; w++)
  {
    if (visited[w] != 1)
    {
      // Define o vértice inicial para a busca em profundidade
      int startVertex = w;

      // Realiza a busca em profundidade a partir do vértice inicial
      DFS(graph, startVertex, visited);
    }
  }

  FILE *arquivo3;
  char linha3[200];
  char *token3;

  arquivo3 = fopen("clusters.txt", "r"); // abre o arquivo3 em modo leitura

  if (arquivo3 == NULL)
  {
    printf("Erro ao abrir o arquivo3!\n");
    exit(1);
  }

  int countClusters = 0;
  int cluster[CLUSTERS][60];
  blancVector(cluster, CLUSTERS, NULL);
  while (fgets(linha3, 200, arquivo3))
  { // lê uma linha3 do arquivo3
    token3 =
        strtok(linha3, " "); // separa a linha3 em campos separados por espaço

    int i = 0;

    while (token3 != NULL)
    { // lê cada campo
      cluster[countClusters][i] = (int)atof(token3);
      token3 = strtok(NULL, " ");
      i++;
    }
    countClusters++;
  }
  fclose(arquivo3); // fecha o arquivo

  int first = 0;
  int middle = 0;
  int last = 0;

  int firstPos = -1;
  int middlePos = -1;
  int lastPos = -1;

  int firstVector = 0;
  int middleVector = 0;
  int lastVector = 0;

  //  Encontrando os 3 maiores clusters
  for (int i = 0; i < CLUSTERS; i++)
  {
    int sum = 0;
    for (int c = 0; cluster[i][c] != NULL; c++)
    {
      sum++;
    }
    if (sum > first)
    {
      first = sum;
      firstVector = cluster[i][0];
      firstPos = i;
    }
    else if (sum > middle)
    {
      middle = sum;
      middleVector = cluster[i][0];
      middlePos = i;
    }
    else if (sum > last)
    {
      last = sum;
      lastVector = cluster[i][0];
      lastPos = i;
    }
  }

  // Encontrando o centro geometrico de cada cluster
  float geoCenters[CLUSTERS][4];
  for (int i = 0; i < CLUSTERS; i++)
  {
    float sepal_length = 0;
    float sepal_width = 0;
    float petal_length = 0;
    float petal_width = 0;
    int div;

    for (int c = 0; cluster[i][c] != NULL; c++)
    {
      sepal_length += vets[cluster[i][c] - 1][0];
      sepal_width += vets[cluster[i][c] - 1][1];
      petal_length += vets[cluster[i][c] - 1][2];
      petal_width += vets[cluster[i][c] - 1][3];
      div = c + 1;
    }
    geoCenters[i][0] = sepal_length / div;
    geoCenters[i][1] = sepal_width / div;
    geoCenters[i][2] = petal_length / div;
    geoCenters[i][3] = petal_width / div;
  }

  int countGraphFinal = 0;
  int vertFinal[500][2];
  resetMatriz(vertFinal, 500);
  for (int l = 0; l < count; l++)
  {
    vertFinal[countGraphFinal][0] = vetLimiar[l][0];
    vertFinal[countGraphFinal][1] = vetLimiar[l][1];
    countGraphFinal++;
  }
  // Juntando vetores que estão fora dos 3 principais clusters
  for (int i = 0; i < CLUSTERS; i++)
  {
    if (i != firstPos && i != middlePos && i != lastPos)
    {
      for (int c = 0; cluster[i][c] != NULL; c++)
      {

        float distToFirst = distVectors(geoCenters[firstPos], vets[cluster[i][c] - 1]);
        float distToMiddle = distVectors(geoCenters[middlePos], vets[cluster[i][c] - 1]);
        float distToLast = distVectors(geoCenters[lastPos], vets[cluster[i][c] - 1]);

        if (distToFirst < distToMiddle && distToFirst < distToLast)
        {
          cluster[firstPos][first] = cluster[i][c];
          first++;
          vertFinal[countGraphFinal][0] = firstVector;
          vertFinal[countGraphFinal][1] = cluster[i][c];
          countGraphFinal++;
        }
        else if (distToMiddle < distToFirst && distToMiddle < distToLast)
        {
          cluster[middlePos][middle] = cluster[i][c];
          middle++;
          vertFinal[countGraphFinal][0] = middleVector;
          vertFinal[countGraphFinal][1] = cluster[i][c];
          countGraphFinal++;
        }
        else
        {
          cluster[lastPos][last] = cluster[i][c];
          last++;
          vertFinal[countGraphFinal][0] = lastVector;
          vertFinal[countGraphFinal][1] = cluster[i][c];
          countGraphFinal++;
        }
      }
    }
  }

  // FILE *arqGraphFinal;
  // arqGraphFinal = fopen("grafoFinal.txt", "a");
  // if (arqGraphFinal == NULL)
  // {
  //   printf("Erro ao abrir o arquivo4.");
  //   return 1;
  // }
  // fprintf(arqGraphFinal, "%i\n", countGraphFinal);
  // for (int i = 0; i < countGraphFinal; i++)
  // {
  //   fprintf(arqGraphFinal, "%i,%i\n", vertFinal[i][0], vertFinal[i][1]);
  // }
  // fclose(arqGraphFinal);

  int firstClusterType = typeFlower(cluster, firstPos);
  int tpFirst = 0;
  int tnFirst = 0;
  int fpFirst = 0;
  int fnFirst = 0;

  for (int c = 0; cluster[firstPos][c] != NULL; c++)
  {
    if (cluster[firstPos][c] < 51 && firstClusterType == 1)
      tpFirst++;
    else
      fpFirst++;
  }

  int middleClusterType = typeFlower(cluster, middlePos);
  int tpMiddle = 0;
  int tnMiddle = 0;
  int fpMiddle = 0;
  int fnMiddle = 0;

  for (int c = 0; cluster[middlePos][c] != NULL; c++)
  {
    if (cluster[middlePos][c] > 50 && cluster[middlePos][c] < 101 && middleClusterType == 2)
      tpMiddle++;
    else
      fpMiddle++;
  }

  int lastClusterType = typeFlower(cluster, lastPos);
  int tpLast = 0;
  int tnLast = 0;
  int fpLast = 0;
  int fnLast = 0;

  for (int c = 0; cluster[lastPos][c] != NULL; c++)
  {
    if (cluster[lastPos][c] > 100 && lastClusterType == 3)
      tpLast++;
    else
      fpLast++;
  }

  int tp = tpFirst + tpMiddle + tpLast;
  int tn = tnFirst + tnMiddle + tnLast;
  int fp = fpFirst + fpMiddle + fpLast;
  int fn = fnFirst + fnMiddle + fnLast;

  float accurace = (tp + tn) / (float)(tp + tn + fp + fn);
  printf("%f\n", accurace * 100);

  return 0;
}