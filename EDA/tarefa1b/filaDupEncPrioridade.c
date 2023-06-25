
#include "arq.h"

struct descF *cria()
{
    struct descF *desc = (struct descF *)malloc(sizeof(struct descF));
    if (desc != NULL)
    {
        desc->cauda = NULL;
        desc->frente = NULL;
        desc->refMovel = NULL;
        desc->tamInfo = sizeof(info);
    }
    return desc;
}

int insereNaFila(info *novo, struct descF *fila)
{
    int result = FRACASSO;
    struct noFila *novoNoFila = NULL, *visitado = NULL;
    if ((novoNoFila = (struct noFila *)malloc(sizeof(struct noFila))) != NULL)
    {
        memcpy(&(novoNoFila->dados), novo, fila->tamInfo); // novoNoFila->dados = *novo;
        if (fila->frente == NULL && fila->cauda == NULL)   /*fila vazia*/
        {
            novoNoFila->atras = novoNoFila->defronte = NULL;
            fila->frente = fila->cauda = fila->refMovel = novoNoFila;
        }
        else
        {
            visitado = fila->frente; /*maior prioridade na frente */
            while (visitado->atras != NULL && (visitado->dados.idade >= novo->idade))
                visitado = visitado->atras;

            if (visitado->dados.idade < novo->idade) /* novo item fica a frente do visitado */
            {
                novoNoFila->atras = visitado;
                if (visitado->defronte != NULL)
                {
                    novoNoFila->defronte = visitado->defronte;
                    visitado->defronte->atras = novoNoFila;
                }
                else
                { // novo item é o de maior prioridade de todos na fila, sendo a nova frente
                    novoNoFila->defronte = NULL;
                    fila->frente = novoNoFila;
                }
                visitado->defronte = novoNoFila;
                novoNoFila->atras = visitado;
                fila->refMovel = novoNoFila;
            }
            else
            { // novo item é o de menor prioridade de todos na fila, sendo a nova cauda
                novoNoFila->defronte = visitado;
                novoNoFila->atras = NULL;
                visitado->atras = novoNoFila;
                fila->cauda = novoNoFila;
            }
        }
        return SUCESSO;
    }

    return FRACASSO;
};

int tamanhoDaFila(struct descF *fila)
{
    int tam = 0;
    struct noFila *aux = fila->cauda;

    while (aux != NULL)
    {
        tam++;
        aux = aux->defronte;
    }

    return tam;
};

int reinicia(struct descF *fila)
{
    int status = 0;
    struct noFila *aux = NULL;

    if (fila->frente != NULL && fila->cauda != NULL)
    {
        aux = fila->cauda->defronte;
        while (aux != NULL)
        {
            free(fila->cauda);
            fila->cauda = aux;
            aux = aux->defronte;
        }

        free(fila->cauda);
        fila->frente = NULL;
        fila->cauda = NULL;
        fila->refMovel = NULL;
        status = 1;
    }
    return status;
};

struct descF *destroi(struct descF *fila)
{
    reinicia(fila);
    free(fila);
    return NULL;
};

int buscaNaCauda(info *ele, struct descF *fila)
{
    int ret = FRACASSO;

    if (fila->cauda != NULL && fila->frente != NULL)
    {
        memcpy(ele, &(fila->cauda->dados), fila->tamInfo);
        ret = SUCESSO;
    }

    return ret;
};

int buscaNaFrente(info *ele, struct descF *fila)
{
    int ret = FRACASSO;

    if (fila->frente != NULL && fila->cauda != NULL)
    {
        memcpy(ele, &(fila->frente->dados), fila->tamInfo);
        ret = SUCESSO;
    }

    return ret;
};

int buscaNoRefMovel(info *ele, struct descF *fila)
{
    int ret = FRACASSO;

    if (fila->cauda != NULL && fila->frente != NULL)
    {
        memcpy(ele, &(fila->refMovel->dados), fila->tamInfo);
        ret = SUCESSO;
    }

    return ret;
};

int retiraDaFila(info *ele, struct descF *fila)
{
    int status = FRACASSO;
    struct noFila *cauda = fila->cauda, *frente = fila->frente, *ref = fila->refMovel;

    if (fila->cauda != NULL && fila->frente != NULL)
    {
        int nC = cauda->dados.idade, nF = frente->dados.idade, nRM = ref->dados.idade;
        nC = (nC - ele->idade) < 0 ? (nC - ele->idade) * (-1) : (nC - ele->idade);
        nF = (nF - ele->idade) < 0 ? (nF - ele->idade) * (-1) : (nF - ele->idade);
        nRM = (nRM - ele->idade) < 0 ? (nRM - ele->idade) * (-1) : (nRM - ele->idade);

        if (cauda == fila->frente)
        { // caso tenha 1 elemento apenas
            free(fila->frente);
            fila->frente = fila->cauda = fila->refMovel = NULL;
        }
        else
        {

            // O elemento a ser removido está mais perto da cauda
            if (nC <= nRM && nC <= nF)
            {
                printf("Perto Cauda\n");
                struct noFila *aux = fila->cauda, *rEle = NULL;

                while (aux->dados.idade <= ele->idade && aux != NULL)
                {
                    if (aux->dados.idade == ele->idade && strcmp(aux->dados.nome, ele->nome) == 0)
                    {
                        rEle = aux;
                        break;
                    }
                    aux = aux->defronte;
                }

                if (rEle == NULL)
                    return FRACASSO;

                aux = rEle->defronte;
                if (rEle == fila->cauda)
                {
                    fila->cauda = aux;
                    rEle->defronte->atras = NULL;
                }
                else
                {
                    rEle->defronte->atras = rEle->atras;
                }
                rEle->defronte->atras = fila->refMovel = aux;
                free(rEle);
            }

            // O elemento a ser removido está mais perto da frente
            else if (nF <= nC && nF <= nRM)
            {
                printf("Perto frente\n");
                struct noFila *auxi1 = fila->frente, *rEle = NULL;

                while (auxi1->dados.idade >= ele->idade && auxi1 != NULL)
                {
                    if (auxi1->dados.idade == ele->idade && strcmp(auxi1->dados.nome, ele->nome) == 0)
                    {
                        rEle = auxi1;
                        break;
                    }
                    auxi1 = auxi1->atras;
                }

                if (rEle == NULL)
                    return FRACASSO;

                auxi1 = rEle->atras;
                if (rEle == fila->frente)
                {
                    fila->frente = auxi1;
                    rEle = fila->frente->defronte = NULL;
                    fila->refMovel = fila->frente->atras != NULL ? fila->frente->atras : fila->frente;
                }
                else
                {
                    rEle->atras->defronte = rEle->defronte;
                    rEle->defronte->atras = fila->refMovel = auxi1;
                    rEle = NULL;
                }
                free(rEle);
            }

            // O elemento a ser removido está mais perto do referencial
            else if (nRM <= nF && nRM <= nC)
            {
                printf("Perto Referencial\n");
                struct noFila *auxi1 = fila->refMovel, *rEle = NULL;
                // if (ele->idade == ref->dados.idade)
                //     return FRACASSO;
                // Elementos está a frente do referencial
                if (ref->dados.idade <= ele->idade)
                {

                    while (auxi1->dados.idade <= ele->idade && auxi1 != NULL)
                    {
                        if (auxi1->dados.idade == ele->idade && strcmp(auxi1->dados.nome, ele->nome) == 0)
                        {
                            rEle = auxi1;
                            break;
                        }
                        auxi1 = auxi1->defronte;
                    }

                    auxi1 = rEle->atras;
                    if (rEle->defronte == fila->frente)
                    {
                        fila->frente = auxi1;
                        fila->frente->atras = fila->refMovel = auxi1;
                        rEle = NULL;
                    }
                    else
                    {
                        rEle->atras->defronte = rEle->defronte;

                        rEle->defronte->atras = fila->refMovel = auxi1;
                    }
                }
                else
                {
                    while (auxi1->dados.idade >= ele->idade && auxi1 != NULL)
                    {
                        if (auxi1->dados.idade == ele->idade && strcmp(auxi1->dados.nome, ele->nome) == 0)
                        {
                            rEle = auxi1;
                            break;
                        }
                        auxi1 = auxi1->atras;
                    }

                    auxi1 = rEle->atras;
                    if (rEle == fila->frente)
                    {
                        fila->frente = auxi1;
                        rEle->atras->defronte = NULL;
                        fila->frente->atras = fila->refMovel = auxi1;
                    }
                    else if (rEle == fila->cauda)
                    {
                        rEle->atras->defronte = rEle->defronte;

                        rEle->defronte->atras = fila->refMovel = auxi1;
                    }
                    else
                    {
                    }
                }

                if (rEle == NULL)
                    return FRACASSO;

                free(rEle);
            }
        }
        status = SUCESSO;
    }

    return status;
};

int testaVazia(struct descF *fila)
{
    if (fila->frente == NULL && fila->cauda == NULL)
    {
        return 1;
    }

    return 0;
};

int inverte(struct descF *fila);