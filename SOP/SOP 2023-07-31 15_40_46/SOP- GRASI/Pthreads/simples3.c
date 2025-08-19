#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define NUM_THREADS 5

void *Quadrado(void *arg) {
    long num = (long)arg;
    long quadrado = (long)pow(num, 2);
    printf("O quadrado de %ld eh %ld\n",num, quadrado);
    pthread_exit((void *)quadrado);
}

int main (int argc, char *argv[]) {
    pthread_t threads [NUM_THREADS];
    int rc;
    long t;
    long sum = 0;
    void *status;
    for (t=0; t<NUM_THREADS; t++){
        printf("main: criando thread %ld\n", t);
        rc = pthread_create (&threads[t], NULL, Quadrado, (void *)t);
        if(rc) {
            printf ("ERRO - rc=%d\n", rc);
            exit(-1);
        }
        pthread_join(threads[t], &status);
        sum += (long)status;
    }
    /* Última coisa que main() deve fazer */
    printf("A SOMA TOTAL EH %ld\n", sum);
    pthread_exit(NULL);
}
