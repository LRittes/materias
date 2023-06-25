#include <stdlib.h>
#include <stdio.h>

typedef struct no
{
    struct no *pai;
    struct no *esquerda;
    struct no *direita;
    int valor;
    int altura;
} No;

typedef struct arvore
{
    struct no *raiz;
} Arvore;

No *criarNo(struct no *pai, int valor);
void balanceamento(Arvore *, No *, int *esforço);
int altura(No *, int *);
int fb(No *, int *);
No *rsd(Arvore *, No *, int *);
No *rse(Arvore *, No *, int *);
No *rdd(Arvore *, No *, int *);
No *rde(Arvore *, No *, int *);
No *encontrarSucessor(No *, int *);
void *remover(Arvore *, int, int *);

Arvore *criar()
{
    Arvore *arvore = malloc(sizeof(Arvore));
    arvore->raiz = NULL;

    return arvore;
}

No *criarNo(struct no *pai, int valor)
{
    No *novo = malloc(sizeof(No));
    novo->valor = valor;
    novo->pai = pai;
    novo->esquerda = NULL;
    novo->direita = NULL;
    novo->altura = 0;
    return novo;
}

int vazia(Arvore *arvore)
{
    return arvore->raiz == NULL;
}

No *adicionarNo(No *no, int valor, int *esforço)
{
    (*esforço)++;
    if (valor > no->valor)
    {
        (*esforço)++;
        if (no->direita == NULL)
        {
            // printf("Adicionando %d\n", valor);
            No *novo = criarNo(no, valor);
            no->direita = novo;

            return novo;
        }
        else
        {
            return adicionarNo(no->direita, valor, esforço);
        }
    }
    else
    {
        (*esforço)++;
        if (no->esquerda == NULL)
        {
            // printf("Adicionando %d\n", valor);
            No *novo = criarNo(no, valor);

            no->esquerda = novo;

            return novo;
        }
        else
        {
            return adicionarNo(no->esquerda, valor, esforço);
        }
    }
}

No *adicionar(Arvore *arvore, int valor, int *esforço)
{
    (*esforço)++;
    if (vazia(arvore))
    {
        // printf("Adicionando %d\n", valor);
        No *novo = criarNo(NULL, valor);

        arvore->raiz = novo;

        return novo;
    }
    else
    {
        No *no = adicionarNo(arvore->raiz, valor, esforço);
        balanceamento(arvore, no, esforço);
        return no;
    }
}

No *localizar(No *no, int valor)
{
    if (no->valor == valor)
    {
        return no;
    }
    else
    {
        if (valor < no->valor)
        {
            if (no->esquerda != NULL)
            {
                return localizar(no->esquerda, valor);
            }
        }
        else
        {
            if (no->direita != NULL)
            {
                return localizar(no->direita, valor);
            }
        }
    }

    return NULL;
}

void percorrer(No *no, void (*callback)(int))
{
    if (no != NULL)
    {
        percorrer(no->esquerda, callback);
        callback(no->valor);
        percorrer(no->direita, callback);
    }
}

void visitar(int valor)
{
    printf("%d ", valor);
}

void balanceamento(Arvore *arvore, No *no, int *esforço)
{
    for (No *atual = no; atual != NULL; atual = atual->pai,
            (*esforço)++)
    {
        int alturaEsquerda = altura(atual->esquerda, esforço);
        int alturaDireita = altura(atual->direita, esforço);
        int fator = alturaEsquerda - alturaDireita;

        (*esforço)++;
        if (fator > 1)
        {
            (*esforço)++;
            if (altura(atual->esquerda->esquerda, esforço) >= altura(atual->esquerda->direita, esforço))
            {
                atual = rsd(arvore, atual, esforço);
            }
            else
            {
                atual = rdd(arvore, atual, esforço);
            }
            (*esforço) = (*esforço) + 2;
        }
        else if (fator < -1)
        {
            (*esforço)++;
            if (altura(atual->direita->direita, esforço) >= altura(atual->direita->esquerda, esforço))
            {
                atual = rse(arvore, atual, esforço);
            }
            else
            {
                atual = rde(arvore, atual, esforço);
            }
            (*esforço) = (*esforço) + 2;
        }
        (*esforço)++;
        atual->altura = 1 + (alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita);
    }
    // pela ultima verificação do for
    (*esforço)++;
}

int altura(No *no, int *esforço)
{
    (*esforço)++;
    if (no == NULL)
    {
        return -1;
    }

    return no->altura;
}

int fb(No *no, int *esforço)
{
    (*esforço)++;
    if (no == NULL)
    {
        return 0;
    }

    return altura(no->esquerda, esforço) - altura(no->direita, esforço);
}

No *rse(Arvore *arvore, No *no, int *esforço)
{
    No *pai = no->pai;
    No *direita = no->direita;

    (*esforço)++;
    if (direita->esquerda != NULL)
    {
        direita->esquerda->pai = no;
    }

    no->direita = direita->esquerda;
    no->pai = direita;

    direita->esquerda = no;
    direita->pai = pai;

    (*esforço)++;
    if (pai == NULL)
    {
        arvore->raiz = direita;
    }
    else
    {
        (*esforço)++;
        if (pai->esquerda == no)
        {
            pai->esquerda = direita;
        }
        else
        {
            pai->direita = direita;
        }
    }

    (*esforço)++;
    // Atualiza a altura dos nós envolvidos
    no->altura = 1 + (altura(no->esquerda, esforço) > altura(no->direita, esforço) ? altura(no->esquerda, esforço) : altura(no->direita, esforço));

    (*esforço)++;
    direita->altura = 1 + (altura(direita->esquerda, esforço) > altura(direita->direita, esforço) ? altura(direita->esquerda, esforço) : altura(direita->direita, esforço));

    return direita;
}

No *rsd(Arvore *arvore, No *no, int *esforço)
{
    No *pai = no->pai;
    No *esquerda = no->esquerda;

    (*esforço)++;
    if (esquerda->direita != NULL)
    {
        esquerda->direita->pai = no;
    }

    no->esquerda = esquerda->direita;
    no->pai = esquerda;

    esquerda->direita = no;
    esquerda->pai = pai;

    (*esforço)++;
    if (pai == NULL)
    {
        arvore->raiz = esquerda;
    }
    else
    {
        (*esforço)++;
        if (pai->esquerda == no)
        {
            pai->esquerda = esquerda;
        }
        else
        {
            pai->direita = esquerda;
        }
    }

    (*esforço)++;
    // Atualiza a altura dos nós envolvidos
    no->altura = 1 + (altura(no->esquerda, esforço) > altura(no->direita, esforço) ? altura(no->esquerda, esforço) : altura(no->direita, esforço));
    (*esforço)++;
    esquerda->altura = 1 + (altura(esquerda->esquerda, esforço) > altura(esquerda->direita, esforço) ? altura(esquerda->esquerda, esforço) : altura(esquerda->direita, esforço));

    return esquerda;
}

No *rde(Arvore *arvore, No *no, int *esforço)
{
    (*esforço)++;
    no->direita = rsd(arvore, no->direita, esforço);
    return rse(arvore, no, esforço);
}

No *rdd(Arvore *arvore, No *no, int *esforço)
{
    (*esforço)++;
    no->esquerda = rse(arvore, no->esquerda, esforço);
    return rsd(arvore, no, esforço);
}

void *remover(Arvore *arvore, int valor, int *esforço)
{

    if (arvore == NULL || arvore->raiz == NULL)
    {
        return NULL; // Árvore vazia, não há nada para remover.
    }

    No *no = localizar(arvore->raiz, valor);
    No *pai = NULL;
    No *temp = NULL;

    if (no == NULL)
    {
        return NULL; // O nó não existe na árvore, retorna NULL.
    }
    pai = no->pai;

    // Caso 1: O nó não tem um ou nenhum filho.
    if (no->esquerda == NULL || no->direita == NULL)
    {
        temp = (no->esquerda != NULL) ? no->esquerda : no->direita;

        if (temp == NULL)
        {
            // Caso especial: o nó não tem filhos.
            if (pai != NULL)
            {
                if (pai->esquerda == no)
                {
                    pai->esquerda = NULL;
                }
                else
                {
                    pai->direita = NULL;
                }
                balanceamento(arvore, pai, esforço); // Atualiza alturas e faz balanceamento.
            }
            else
            {
                arvore->raiz = NULL; // Nó a ser removido é a raiz da árvore.
            }
        }
        else
        {
            // Caso especial: o nó tem um filho.
            temp->pai = pai;

            if (pai != NULL)
            {
                if (pai->esquerda == no)
                {
                    pai->esquerda = temp;
                }
                else
                {
                    pai->direita = temp;
                }
                balanceamento(arvore, pai, esforço); // Atualiza alturas e faz balanceamento.
            }
            else
            {
                arvore->raiz = temp; // Nó a ser removido é a raiz da árvore.
            }
        }

        free(no);
        no = NULL;
    }
    // Caso 2: O nó tem dois filhos.
    else
    {
        No *sucessor = encontrarSucessor(no->direita, esforço);
        no->valor = sucessor->valor;
        temp = sucessor->direita;

        if (sucessor->pai == no)
        {
            no->direita = temp;
        }
        else
        {
            sucessor->pai->esquerda = temp;
        }

        if (temp != NULL)
        {
            temp->pai = sucessor->pai;
        }

        free(sucessor);
        sucessor = NULL;

        balanceamento(arvore, no, esforço); // Atualiza alturas e faz balanceamento.
    }

    return no;
}

No *encontrarSucessor(No *no, int *esforço)
{
    No *atual = no;

    (*esforço)++;
    while (atual->esquerda != NULL)
    {
        (*esforço)++;
        atual = atual->esquerda;
    }

    return atual;
}
