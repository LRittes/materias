#include "arq.h"
#include "math.h"
#include <stdio.h>

int main(void)
{
	int tamData, i;
	info data[] = {{23, "Pedro"}, {32, "Maria"}, {18, "Ana"}, {81, "Vania"}}, tmp;
	struct descF *p = NULL;

	tamData = sizeof(data) / sizeof(info);
	printf("\ntamanho do conjunto de dados %i\n", tamData);

	p = cria(sizeof(info));

	printf("tamanho da fila %i\n\n", tamanhoDaFila(p));

	for (i = 0; i < tamData; i++)
	{
		if (insereNaFila(&data[i], p) == SUCESSO)
		{
			printf("inserido na fila: %i %s \n", data[i].idade, data[i].nome);
			printf("tamanho da fila %i\n", tamanhoDaFila(p));
		}
		else
			printf("\n Erro na insercao \n");
	}

	buscaNaCauda(&tmp, p);
	printf("\nfinal da fila: %i %s \n", tmp.idade, tmp.nome);

	buscaNaFrente(&tmp, p);
	printf("frente da fila: %i %s \n", tmp.idade, tmp.nome);
	printf("Referencial movel: %s\n\n", p->refMovel->dados.nome);

	for (i = 0; i < round(tamData / 2); i++)
		if (retiraDaFila(&tmp, p) == FRACASSO)
			printf("erro na remocao de %s\n", tmp.nome);
		else
		{
			printf("Removido da fila %i %s \n", tmp.idade, tmp.nome);
			printf("tamanho da fila %i\n\n", tamanhoDaFila(p));
			buscaNaFrente(&tmp, p);
		}

	buscaNaCauda(&tmp, p);
	printf("\nfinal da fila %i %s \n", tmp.idade, tmp.nome);

	buscaNaFrente(&tmp, p);
	printf("frente da fila %i %s \n\n", tmp.idade, tmp.nome);

	printf("tamanho da fila %i\n", tamanhoDaFila(p));

	tmp.idade = 10;
	strcpy(tmp.nome, "Wellington");
	if (insereNaFila(&tmp, p) == FRACASSO)
		printf("Erro na tentativa de insercao");
	else
		printf("Inserido na fila: %i %s \n", tmp.idade, tmp.nome);

	printf("tamanho da fila %i\n", tamanhoDaFila(p));
	buscaNaCauda(&tmp, p);
	printf("\nfinal da fila %i %s \n", tmp.idade, tmp.nome);
	buscaNaFrente(&tmp, p);
	printf("frente da fila %i %s \n\n", tmp.idade, tmp.nome);

	tmp.idade = 90;
	strcpy(tmp.nome, "Zeca");
	if (insereNaFila(&tmp, p) == FRACASSO)
		printf("Erro na tentativa de insercao");
	else
		printf("Inserido na fila: %i %s \n", tmp.idade, tmp.nome);

	printf("tamanho da fila %i\n", tamanhoDaFila(p));
	buscaNaCauda(&tmp, p);
	printf("\nfinal da fila %i %s \n", tmp.idade, tmp.nome);
	buscaNaFrente(&tmp, p);
	printf("frente da fila %i %s \n\n", tmp.idade, tmp.nome);

	puts("Tecle!");
	getchar();
	p = destroi(p);
	return 1;
}