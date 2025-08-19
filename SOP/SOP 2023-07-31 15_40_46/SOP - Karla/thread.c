#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NELEM 40000000

long v[NELEM];

void * busca_inc(void *arg) {
     long i, ret = -1, n = (long) arg;

     for (i = 0; i < NELEM; i++) {
	  if (v[i] == n) {
	       ret = i;
	       break;
	  }
     }

     pthread_exit((void *) ret);
}

int main(int argc, char *argv[])
{
     void *status;
     long i, n, ret;
     pthread_t thr; 

     if (argc < 2) {
	  fprintf(stderr, "e o n, querido?\n");
	  exit(1);
     }
     n = atoi(argv[1]);
     for (i = 0; i < NELEM; i++) 
	  v[i] = i+1;
     pthread_create(&thr, NULL, busca_inc, (void *) n);

     pthread_join(thr, &status);
     ret = (long) status;

     if (ret >= 0)
	  printf("O numero %ld esta na posicao %ld do vetor\n", n, ret);
     else
	  printf("O numero %ld nao foi encontrado no vetor\n", n);
    
     /* Ultima coisa que main() deve fazer */
     pthread_exit(NULL);
}

