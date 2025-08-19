#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define NUM_THREADS 8

void *PrintHello(void *arg) {
    long tid = (long)arg;
    printf("Alo da thread hello %ld\n",tid);
    pthread_exit(NULL);
}

void *Quadrado(void *arg) {
    long num = (long)arg;
    long quadrado = (long)pow(num, 2);
    printf("O quadrado de %ld eh %ld\n",num, quadrado);
    pthread_exit(NULL);
}

int main (int argc, char *argv[]) {
    pthread_t threads [NUM_THREADS];
    int rc;
    long t;
    for (t=0; t<NUM_THREADS; t++){
        printf("main: criando thread %ld\n", t);
        rc = pthread_create (&threads[t], NULL, PrintHello, (void *)t);
        if(rc) {
            printf ("ERRO - rc=%d\n", rc);
            exit(-1);
        }
        rc = pthread_create (&threads[t], NULL, Quadrado, (void *)t);
        if(rc) {
            printf ("ERRO - rc=%d\n", rc);
            exit(-1);
        }
    }
    /* Última coisa que main() deve fazer */
    pthread_exit(NULL);
}
