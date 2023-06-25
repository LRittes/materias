#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 151

// Estrutura para representar um nó em um grafo
struct Node
{
    int data;
    struct Node *next;
};

// Função para criar um novo nó
struct Node *createNode(int data)
{
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

// Função para adicionar uma aresta entre dois nós do grafo
void addEdge(struct Node *graph[], int src, int dest)
{
    // Adiciona uma aresta do src ao dest
    struct Node *newNode = createNode(dest);
    newNode->next = graph[src];
    graph[src] = newNode;

    // Adiciona uma aresta do dest ao src (grafo não direcionado)
    newNode = createNode(src);
    newNode->next = graph[dest];
    graph[dest] = newNode;
}

// Função auxiliar para realizar a busca em profundidade
void DFS(struct Node *graph[], int vertex, int visited[])
{
    // Marca o vértice atual como visitado
    visited[vertex] = 1;
    // printf("%d ", vertex);

    // Percorre todos os vértices adjacentes ao vértice atual
    struct Node *temp = graph[vertex];

    while (temp != NULL)
    {
        int adjVertex = temp->data;

        if (!visited[adjVertex])
        {
            DFS(graph, adjVertex, visited);
        }
        temp = temp->next;
    }
}

void printGraph(struct Node *graph[], int size, int visited[])
{
    for (int i = 1; i < size; i++)
    {
        if (visited[i] == 0)
        {

            visited[i] = 1;
            printf("%d ", i);
            struct Node *current = graph[i];

            while (current != NULL)
            {
                visited[current->data] = 1;
                printf("%d ", current->data);
                current = current->next;
            }

            printf("\n");
        }
    }
}