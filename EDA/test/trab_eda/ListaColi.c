#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ListaColi.h"

/*************** CRIA ***************/
DescLC *criaListaLC(int tamInfoLC)
{
	DescLC *desc = (DescLC *)malloc(sizeof(DescLC));

	if (desc != NULL)
	{
		desc->inicio = NULL;
		desc->tamInfo = tamInfoLC;
	}
	return desc;
}

/*************** insere novo primeiro ***************/
int insereNovoPrimeiroC(InfoLC *reg, DescLC *p)
{
	NoLC *temp;
	int ret = FRACASSO;
	temp = (NoLC *)malloc(sizeof(NoLC));

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
int insereNovoUltimoC(InfoLC *reg, DescLC *p)
{
	int ret = FRACASSO;
	NoLC *aux = NULL, *temp = NULL;

	if (p->inicio == NULL)
	{
		ret = insereNovoPrimeiroC(reg, p);
		// printf(">>>>>>>>>> %s\n", p->inicio->dados.nome);
	}
	else
	{
		aux = p->inicio;
		while (aux->prox != NULL)
			aux = aux->prox;
		temp = (NoLC *)malloc(sizeof(NoLC));
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
int insereNaPoslogC(int posLog, InfoLC *novo, DescLC *p)
{
	int cont = 0;
	NoLC *temp = NULL, *aux1 = NULL, *aux2 = NULL;
	int ret = FRACASSO;

	if (posLog < 0)
	{
		printf("PosLog %i inexistente: PosLog < 0 ou posLog > tamanho da lista \n", posLog);
		return ret;
	}
	if (p->inicio == NULL && posLog == 0)
	{
		NoLC *temp = (NoLC *)malloc(sizeof(NoLC));
		memcpy(&(temp->dados), novo, p->tamInfo);
		temp->prox = NULL;

		p->inicio = temp;
		ret = SUCESSO;
	}
	// else if (p->inicio == NULL)
	// {
	// 	printf("A operação não insere em lista vazia \n");
	// 	return ret;
	// }
	else
	{
		if (posLog == 1)
		{

			NoLC *temp = (NoLC *)malloc(sizeof(NoLC));
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
				temp = (NoLC *)malloc(sizeof(NoLC));
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
int buscaNaPoslogC(int posLog, InfoLC *reg, DescLC *p)
{
	int cont = 0;
	NoLC *aux = NULL;
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
			return (buscaOprimeiroC(reg, p));
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
int removeDaPoslogC(int posLog, InfoLC *reg, DescLC *p)
{
	int cont = 0;
	NoLC *aux1 = NULL, *aux2 = NULL;
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
			return (removeOprimeiroC(reg, p));
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
int removeOprimeiroC(InfoLC *reg, DescLC *p)
{
	NoLC *aux = NULL;
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
int removeOultimoC(InfoLC *reg, DescLC *p)
{
	int ret = FRACASSO;
	NoLC *aux1 = p->inicio, *aux2 = NULL;
	if (p->inicio == NULL)
		return ret;
	if (aux1->prox == NULL)
		return (removeOprimeiroC(reg, p));
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
int buscaOprimeiroC(InfoLC *reg, DescLC *p)
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
int buscaOultimoC(InfoLC *reg, DescLC *p)
{
	int ret = FRACASSO;
	NoLC *aux1 = NULL;
	if (p->inicio == NULL)
		return ret;
	aux1 = p->inicio;
	if (aux1->prox == NULL)
		return (buscaOprimeiroC(reg, p));
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
int testaVaziaC(DescLC *p)
{
	if (p->inicio == NULL)
	{
		return SIM;
	}

	return NAO;
}

/*************** TAMANHO ***************/
int tamanhoDaListaC(DescLC *p)
{
	int tam = 0;
	NoLC *aux = p->inicio;

	while (aux != NULL)
	{
		aux = aux->prox;
		tam++;
	}

	return tam;
}

/*************** PURGA ***************/
int reiniciaC(DescLC *p)
{
	NoLC *aux;

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
DescLC *destroiC(DescLC *p)
{
	reiniciaC(p);
	free(p);
	return NULL; // aterra o ponteiro externo, declarado na aplicação
}

int searchWord(char str[], DescLC *list, InfoLC *destino)
{
	NoLC *aux = list->inicio;

	while (aux != NULL)
	{
		if (compareStr(str, aux->dados.str) == 1)
		{
			// printf("%s %s\n", str, aux->dados.str);
			copyTo(sizeof(InfoLC), destino, &(aux->dados));
			return SUCESSO;
		}
		aux = aux->prox;
	}
	return FRACASSO;
}

void copyTo(int tamInfo, InfoLC *destino, InfoLC *fonte)
{
	memcpy(destino, fonte, tamInfo);
}

int compareStr(char str1[], char str2[])
{
	int n1, n2;
	for (n1 = 0; str1[n1] != '\0'; n1++)
		;
	for (n2 = 0; str1[n2] != '\0'; n2++)
		;
	if (n1 != n2)
		return 0;
	for (int i = 0; i < n1; i++)
	{
		if (str1[i] != str2[i])
			return 0;
	}
	return 1;
}