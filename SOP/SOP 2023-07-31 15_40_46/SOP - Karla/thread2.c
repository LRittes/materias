#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NELEM 40000000
#define find 0

long v[NELEM];

void * busca_dec(void *arg) {
     long i, ret = -1, n = (long) arg;

     for (i = NELEM-1; i >= 0; i--) {
	  if (v[i] == n) {
	       ret = i;
	       break;
	  }
     }
     
     if(find == 0)
           find = (long) 2;

     pthread_exit((void *) ret);
}

void * busca_inc(void *arg) {
     long i, ret = -1, n = (long) arg;

     for (i = 0; i < NELEM; i++) {
	  if (v[i] == n) {
	       ret = i;
	       break;
	  }
     }

     if(find == 0)
           find = (long) 1;

     pthread_exit((void *) ret);
}

int main(int argc, char *argv[])
{
     void *status;
     long i, n, ret, ret2;
     pthread_t thr, thr2; 

     if (argc < 2) {
	  fprintf(stderr, "e o n, querido?\n");
	  exit(1);
     }
     n = atoi(argv[1]);
     for (i = 0; i < NELEM; i++) 
	  v[i] = i+1;
     pthread_create(&thr, NULL, busca_inc, (void *) n);
     pthread_create(&thr2, NULL, busca_dec, (void *) n);

     pthread_join(thr, &status);
     ret = (long) status;
     pthread_join(thr2, &status);
     ret2 = (long) status;

     printf("A thread que achou primeiro foi a de ID %d\n", find);

     if (ret >= 0 || ret2 >= 0)
	  printf("O numero %ld esta na posicao %ld do vetor\n", n, ret);
     else
	  printf("O numero %ld nao foi encontrado no vetor\n", n);
    
     /* Ultima coisa que main() deve fazer */
     pthread_exit(NULL);
}

