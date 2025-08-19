/*Modifique o programa simples.c para criar, al�m das threads que executam PrintHello(),
outras NUM_THREADS+3 threads que imprimam o quadrado do n�mero recebido como par�metro. 
(No total, portanto, o programa deve criar 2*NUM_THREADS+3 threads.) */


#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS    5

void *PrintHello(void *arg) {
   long tid = (long)arg;
   printf("Alo da thread %ld\n", 
          tid);
   pthread_exit(NULL);
}

void*NUM_THREADS+3(void * arg){
	
}


int main (int argc, char *argv[]) {
   pthread_t threads[NUM_THREADS];
   int rc;
   long t;
   for (t=0; t<NUM_THREADS; t++){
      printf("main: criando thread %ld\n", t);
      rc = pthread_create(&threads[t], 
                          NULL, 
                          PrintHello, 
                          (void *)t);
      if (rc) {
         printf("ERRO - rc=%d\n", rc);
         exit(-1);
      }
   }
   /* Ultima coisa que main() deve fazer */
   pthread_exit(NULL); 
}
