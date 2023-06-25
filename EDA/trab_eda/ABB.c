#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "arq_privado.h"

ABB *criaABB(int tamInfo)
{

    ABB *desc = (ABB *)malloc(sizeof(ABB));

    if (desc != NULL)
    {
        desc->raiz = NULL;
        desc->tamInfo = tamInfo;
        return desc;
    }

    return NULL;
}

ABB *destroiABB(ABB *p)
{
    reiniciaABB(p);
    free(p);
    return NULL;
}

void reiniciaABB(ABB *p)
{
    apagaNoABB(p->raiz);
    p->raiz = NULL;
}

void apagaNoABB(NoABB *p)
{
    if (p != NULL)
    {
        apagaNoABB(p->esq);
        apagaNoABB(p->dir);
        free(p);
    }
}

int buscaABB(ABB *pa, InfoMain *destino, tipoChave chaveDeBusca)
{
    NoABB *aux = pa->raiz;
    unsigned int ret = FRACASSO;

    while (aux != NULL && ((aux->dados).identificador != chaveDeBusca))
    {
        if (chaveDeBusca < (aux->dados).identificador)
            aux = aux->esq;
        else
            aux = aux->dir;
    }

    if (aux != NULL)
    {
        memcpy(destino, &(aux->dados), pa->tamInfo);
        ret = SUCESSO;
    }

    return ret;
}

int insereABB(ABB *pa, InfoMain *novoReg)
{
    NoABB *p, *q, *novoNoABB;

    p = q = pa->raiz;

    while (p != NULL)
    {
        if (novoReg->identificador == (p->dados.identificador))
        {
            return FRACASSO; /* registro j� inserido previamente */
        }
        else
        {
            q = p;
            if (novoReg->identificador < (p->dados.identificador))
                p = p->esq;
            else
                p = p->dir;
        }
    }

    if ((novoNoABB = (NoABB *)malloc(sizeof(NoABB))) != NULL)
    {
        if (novoNoABB != NULL)
        {

            memcpy(&(novoNoABB->dados), novoReg, pa->tamInfo);
            novoNoABB->altura = 0;
            novoNoABB->dir = novoNoABB->esq = NULL; /* insere nova folha */

            if (q != NULL)
            {
                if ((novoNoABB->dados.identificador) < (q->dados.identificador)) /*ou (*cmp)(novoReg,q->dados)*/
                    q->esq = novoNoABB;
                else
                    q->dir = novoNoABB;
                // printf("inserir - antes altura\n");
                q->altura = maior(alturaNo(q->esq), alturaNo(q->dir)) + 1;
                // printf("inserir - antes balancear\n");

                q = balancear(q);
            }
            else
                pa->raiz = novoNoABB; /* �rvore com um �nico n� */

            return SUCESSO;
        }
    }

    return FRACASSO; /* n�o conseguiu alocar mem�ria */
}

int removeABB(ABB *pa, tipoChave chaveDeBusca, InfoMain *copia)
{

    NoABB *subst, *paiSubst, *alvo, *paiDoAlvo, *avante;

    paiDoAlvo = NULL;
    alvo = pa->raiz;

    while (alvo != NULL && (chaveDeBusca != (alvo->dados.identificador)))
    {
        paiDoAlvo = alvo;
        if (chaveDeBusca < (alvo->dados.identificador))
            alvo = alvo->esq;
        else
            alvo = alvo->dir;
    }

    if (alvo == NULL) /*alvo n�o encontrado */
        return FRACASSO;

    if (alvo->esq == NULL)
    {
        if (alvo->dir == NULL) // alvo � uma folha
            subst = NULL;
        else
            subst = alvo->dir; /*alvo possui apenas um filho � dir*/
    }

    else
    {
        if (alvo->dir == NULL)
            subst = alvo->esq; /*alvo possui apenas um filho � esq*/

        else /* alvo possui os dois filhos*/
        {    /* determinando o substituto pelo sucessor em ordem: */
            paiSubst = alvo;
            subst = alvo->dir;
            // avante = alvo->esq; // ERRO!!
            avante = subst->esq; // CORRE��O!!
            while (avante != NULL)
            {
                paiSubst = subst;
                subst = avante;
                avante = avante->esq;
            }

            if (paiSubst != alvo)
            {
                paiSubst->esq = subst->dir;
                subst->dir = alvo->dir;
            }
            subst->esq = alvo->esq; /*o pai do substituto eh o proprio alvo */
        }
    }

    if (pa->raiz == alvo) // ou seja se "paiDoAlvo = NULL"
        pa->raiz = subst; /*alvo era a raiz*/
    else
        alvo == paiDoAlvo->esq ? (paiDoAlvo->esq = subst) : (paiDoAlvo->dir = subst);

    memcpy(copia, &(alvo->dados), pa->tamInfo);
    free(alvo);

    return SUCESSO;
}

int testaVaziaABB(ABB *p)
{
    return (p->raiz == NULL ? SIM : NAO);
}

int numFolhas(ABB *p)
{
    if (p != NULL)
    {
        return calcNumFolhas(p->raiz);
    }

    return 0;
}

int calcNumFolhas(NoABB *p)
{
    if (!p)
    {
        return 0;
    }

    if (!(p->esq) && !(p->dir))
    {
        return 1;
    }

    return calcNumFolhas(p->esq) + calcNumFolhas(p->dir);
}

int maior(int a, int b)
{
    // printf("maior\n");

    return a > b ? a : b;
}

int alturaNo(NoABB *no)
{
    // printf("altura no - if\n");

    if (no == NULL)
        return -1;

    else
    {
        // printf("altura no - depois else\n");

        return no->altura;
    }
}

int fatorBalanceamento(NoABB *no)
{
    // printf("fatorBalanceamento - if\n");

    if (no)
        return (no->esq - no->dir);
    else
    {
        // printf("fatorBalanceamento - else\n");

        return 0;
    }
}

NoABB *rotaEsq(NoABB *no)
{
    printf("rotaEsq - inicio\n");
    printf("Aqui");

    NoABB *n1, *n2;

    n1 = no->dir;
    n2 = n1->esq;

    n1->esq = no;
    no->dir = n2;

    no->altura = maior(alturaNo(no->esq), alturaNo(no->dir)) + 1;
    n1->altura = maior(alturaNo(n1->esq), alturaNo(n1->dir)) + 1;

    // printf("rotaEsq - fim\n");

    return n1;
}

NoABB *rotaDir(NoABB *no)
{
    // printf("rotaDir - fim\n");

    NoABB *n1, *n2;

    n1 = no->esq;
    n2 = n1->dir;

    n1->dir = no;
    no->esq = n2;

    no->altura = maior(alturaNo(no->esq), alturaNo(no->dir)) + 1;
    n1->altura = maior(alturaNo(n1->esq), alturaNo(n1->dir)) + 1;

    // printf("rotaDir - inicio\n");

    return n1;
}

NoABB *rotaDirEsq(NoABB *no)
{
    no->dir = rotaDir(no->dir);
    return rotaEsq(no);
}

NoABB *rotaEsqDir(NoABB *no)
{
    no->esq = rotaEsq(no->esq);
    return rotaDir(no);
}

NoABB *balancear(NoABB *no)
{
    // printf("balancear - inicio\n");

    int fb = fatorBalanceamento(no);

    // rotação esquerda
    if (fb < -1 && fatorBalanceamento(no->dir) <= 0)
        no = rotaEsq(no);

    // rotação direita
    else if (fb > 1 && fatorBalanceamento(no->esq) >= 0)
        no = rotaDir(no);

    // rotação esquerda direita
    else if (fb > 1 && fatorBalanceamento(no->esq) < 0)
        no = rotaEsqDir(no);

    // rotação direita esquerda
    else if (fb < -1 && fatorBalanceamento(no->dir) > 0)
        no = rotaDirEsq(no);

    // printf("balancear - fim\n");

    return no;
}