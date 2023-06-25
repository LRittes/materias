#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ListaPos.h"

/*************** CRIA ***************/
DescListaPos *criaListPos()
{
	DescListaPos *desc = (DescListaPos *)malloc(sizeof(DescListaPos));

	if (desc != NULL)
	{
		desc->inicio = NULL;
		desc->tamInfo = sizeof(InfoLP);
	}
	return desc;
}

/*************** insere novo primeiro ***************/
int insereNovoPrimeiro(InfoLP *reg, struct DescListaPos *p)
{
	NoLP *temp;
	int ret = FRACASSO;
	temp = (NoLP *)malloc(sizeof(NoLP));

	if (temp != NULL)
	{
		memcpy(&(temp->dados), reg, p->tamInfo);
		temp->prox = p->inicio;
		p->inicio = temp;

		ret = SUCESSO;
	}

	return ret;
}

/*************** insere novo ultimo ***************/
int insereNovoUltimo(InfoLP *reg, struct DescListaPos *p)
{
	int ret = FRACASSO;
	NoLP *aux = NULL, *temp = NULL;

	if (p->inicio == NULL)
	{
		ret = insereNovoPrimeiro(reg, p);
		// printf(">>>>>>>>>> %s\n", p->inicio->dados.nome);
	}
	else
	{
		aux = p->inicio;
		while (aux->prox != NULL)
			aux = aux->prox;
		temp = (NoLP *)malloc(sizeof(NoLP));
		if (temp != NULL)
		{
			memcpy(&(temp->dados), reg, p->tamInfo);
			temp->prox = NULL;
			aux->prox = temp;
			ret = SUCESSO;
		}
		else
			ret = FRACASSO;
	}

	return ret;
}

/*************** INSERE NA posLog alvo ***************/
int insereNaPoslog(int posLog, InfoLP *novo, DescListaPos *p)
{
	int cont = 0;
	NoLP *temp = NULL, *aux1 = NULL, *aux2 = NULL;
	int ret = FRACASSO;

	if (posLog < 0)
	{
		printf("PosLog %i inexistente: PosLog <= 0 ou posLog > tamanho da lista \n", posLog);
		return ret;
	}
	if (p->inicio == NULL && posLog == 0)
	{
		NoLP *temp = (NoLP *)malloc(sizeof(NoLP));
		memcpy(&(temp->dados), novo, p->tamInfo);
		temp->prox = NULL;

		p->inicio = temp;
		ret = SUCESSO;
	}
	else
	{
		if (posLog == 1)
		{

			NoLP *temp = (NoLP *)malloc(sizeof(NoLP));
			memcpy(&(temp->dados), novo, p->tamInfo);
			temp->prox = NULL;

			p->inicio->prox = temp;
			ret = SUCESSO;
		}
		else
		{
			aux1 = p->inicio;
			aux2 = p->inicio->prox;
			cont = 2;
			while (aux2 != NULL && cont < posLog)
			{
				aux1 = aux2;
				aux2 = aux2->prox;
				cont = cont + 1;
			}
			if (aux2 != NULL && posLog == cont)
			/*aux1 faz referência à posição posLog-1 e aux2 à posLog */
			{
				temp = (NoLP *)malloc(sizeof(NoLP));
				memcpy(&(temp->dados), novo, p->tamInfo);
				temp->prox = NULL;
				aux1->prox = temp;
				aux1->prox->prox = aux2;
				ret = SUCESSO;
			}
			else
			{
				fprintf(stderr, "PosLog %i inexistente: PosLog <= 0 ou posLog > tamanho da lista \n", posLog);
				ret = FRACASSO;
			}
		}
	}

	return ret;
}

/*************** Busca na poslog ***************/
int buscaNaPoslog(int posLog, InfoLP *reg, DescListaPos *p)
{
	int cont = 0;
	NoLP *aux = NULL;
	int ret = FRACASSO;

	if (posLog <= 0)
	{
		printf("PosLog %i inexistente: PosLog <= 0 ou posLog > tamanho da lista \n", posLog);
		return ret;
	}
	if (p->inicio == NULL)
	{
		printf("A operação não insere em lista vazia \n");
		return ret;
	}
	else
	{
		if (posLog == 1)
			return (buscaOprimeiro(reg, p));
		else
		{
			aux = p->inicio->prox;
			cont = 2;
			while (aux != NULL && cont < posLog)
			{
				aux = aux->prox;
				cont = cont + 1;
			}
			if (aux != NULL && posLog == cont)
			/*aux1 faz referência à posição posLog-1 e aux2 à posLog */
			{
				memcpy(reg, &(aux->dados), p->tamInfo);
				ret = SUCESSO;
			}
			else
			{
				fprintf(stderr, "PosLog %i inexistente: PosLog <= 0 ou posLog > tamanho da lista \n", posLog);
				ret = FRACASSO;
			}
		}
	}
	return ret;
}

/*************** Remove da poslog ***************/
int removeDaPoslog(int posLog, InfoLP *reg, DescListaPos *p)
{
	int cont = 0;
	NoLP *aux1 = NULL, *aux2 = NULL;
	int ret = FRACASSO;

	if (posLog <= 0)
	{
		printf("PosLog %i inexistente: PosLog <= 0 ou posLog > tamanho da lista \n", posLog);
		return ret;
	}
	if (p->inicio == NULL)
	{
		printf("A operação não insere em lista vazia \n");
		return ret;
	}
	else
	{
		if (posLog == 1)
			return (removeOprimeiro(reg, p));
		else
		{
			aux1 = p->inicio;
			aux2 = p->inicio->prox;
			cont = 2;
			while (aux2 != NULL && cont < posLog)
			{
				aux1 = aux2;
				aux2 = aux2->prox;
				cont = cont + 1;
			}
			if (aux2 != NULL && posLog == cont)
			/*aux1 faz referência à posição posLog-1 e aux2 à posLog */
			{
				memcpy(reg, &(aux2->dados), p->tamInfo);
				aux1->prox = aux2->prox;
				free(aux2);
				ret = SUCESSO;
			}
			else
			{
				fprintf(stderr, "PosLog %i inexistente: PosLog <= 0 ou posLog > tamanho da lista \n", posLog);
				ret = FRACASSO;
			}
		}
	}

	return ret;
}

/*************** Remove o primeiro ***************/
int removeOprimeiro(InfoLP *reg, DescListaPos *p)
{
	NoLP *aux = NULL;
	int ret = FRACASSO;

	if (p->inicio)
	{
		aux = p->inicio->prox;
		memcpy(reg, &(p->inicio->dados), p->tamInfo);
		free(p->inicio);
		p->inicio = aux;
		ret = SUCESSO;
	}

	return ret;
}

/*************** Remove no final ***************/
int removeOultimo(InfoLP *reg, DescListaPos *p)
{
	int ret = FRACASSO;
	NoLP *aux1 = p->inicio, *aux2 = NULL;
	if (p->inicio == NULL)
		return ret;
	if (aux1->prox == NULL)
		return (removeOprimeiro(reg, p));
	else
	{

		aux2 = p->inicio->prox;
		while (aux2->prox != NULL)
		{
			aux1 = aux2;
			aux2 = aux2->prox;
		}
		memcpy(reg, &(aux2->dados), p->tamInfo);
		free(aux2);
		aux1->prox = NULL;
		ret = SUCESSO;
	}

	return ret;
}

/*************** Busca o primeiro ***************/
int buscaOprimeiro(InfoLP *reg, DescListaPos *p)
{
	int ret = FRACASSO;

	if (p->inicio)
	{
		memcpy(reg, &(p->inicio->dados), p->tamInfo);
		ret = SUCESSO;
	}

	return ret;
}

/*************** BUSCA No final ***************/
int buscaOultimo(InfoLP *reg, struct DescListaPos *p)
{
	int ret = FRACASSO;
	NoLP *aux1 = NULL;
	if (p->inicio == NULL)
		return ret;
	aux1 = p->inicio;
	if (aux1->prox == NULL)
		return (buscaOprimeiro(reg, p));
	else
	{
		while (aux1->prox != NULL)
			aux1 = aux1->prox;
		memcpy(reg, &(aux1->dados), p->tamInfo);
		ret = SUCESSO;
	}

	return ret;
}

/*************** VAZIA? ***************/
int testaVazia(struct DescListaPos *p)
{
	if (p->inicio == NULL)
	{
		return SIM;
	}

	return NAO;
}

/*************** TAMANHO ***************/
int tamanhoDaLista(struct DescListaPos *p)
{
	int tam = 0;
	NoLP *aux = p->inicio;

	while (aux != NULL)
	{
		aux = aux->prox;
		tam++;
	}

	return tam;
}

/*************** PURGA ***************/
int reinicia(DescListaPos *p)
{
	NoLP *aux;

	if (p->inicio != NULL)
	{
		aux = p->inicio->prox;
		while (aux != NULL)
		{
			free(p->inicio);
			p->inicio = aux;
			aux = aux->prox;
		}

		free(p->inicio);
		p->inicio = NULL;
		return SUCESSO;
	}
	else
		return FRACASSO;
}

/*************** DESTROI ***************/
struct DescListaPos *destroi(DescListaPos *p)
{
	reinicia(p);
	free(p);
	return NULL; // aterra o ponteiro externo, declarado na aplicação
}
