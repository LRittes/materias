/*O programa conta.c, disponível nos exemplos de Pthreads, conta o números de elementos 
pares e de elementos ímpares em um vetor de inteiros. Adapte esse programa conta.c para
criar duas threads, uma para contar os pares e outra para contar os ímpares. Ambas as threads
devem executar a função conta(). O vetor v pode ser definido como uma variável global, sendo 
o único parâmetro da função o tipo de número a ser contado. Execute o programa algumas 
vezes e veja se há diferença no tempo de execução das duas versões (sequencial e multithread).*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>

#define T_PAR 1
#define T_IMPAR 2

#define NELEM 2000000

int conta(int *v, char tipo) 
{
     int i, total = 0;
     
     for (i = 0; i < NELEM; i++) {
	  if ((tipo == T_PAR) && ((v[i] % 2) == 0))
	      total++;
	  else if ((tipo == T_IMPAR) && ((v[i] % 2) != 0))
	       total++;
     }
     return total;
}

int main(int argc, char *argv[])
{
     int vetor[NELEM], i, pares, impares, rc;
     struct timeval tv_ini, tv_fim;
     unsigned long time_diff, sec_diff, usec_diff, msec_diff;

     srandom(time(NULL));
     for (i = 0; i < NELEM; i++) {
	  vetor[i] = (int)random();
/*	  vetor[i] = i*2;*/
     }
     
     /* marca o tempo de inicio */
     rc = gettimeofday(&tv_ini, NULL);
     if (rc != 0) {
	  perror("erro em gettimeofday()");
	  exit(1);
     }

     /* faz o processamento de interesse */
     pares = conta(vetor, T_PAR);
     impares = conta(vetor, T_IMPAR);

     /* marca o tempo de final */
     rc = gettimeofday(&tv_fim, NULL);
     if (rc != 0) {
	  perror("erro em gettimeofday()");
	  exit(1);
     }
     /* calcula a diferenca entre os tempos, em usec */
     time_diff = (1000000L*tv_fim.tv_sec + tv_fim.tv_usec) - 
  	         (1000000L*tv_ini.tv_sec + tv_ini.tv_usec);
     /* converte para segundos + microsegundos (parte fracionária) */
     sec_diff = time_diff / 1000000L;
     usec_diff = time_diff % 1000000L;
     
     /* converte para msec */
     msec_diff = time_diff / 1000;
     
     printf("O vetor tem %d numeros pares e %d numeros impares.\n", pares,
	    impares);
/*     printf("Tempo de execucao: %lu.%06lu seg\n", sec_diff, usec_diff);*/
     printf("Tempo de execucao: %lu.%03lu mseg\n", msec_diff, usec_diff%1000);
     return 0;
}
