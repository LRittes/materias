#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <pthread.h>


pthread_cond_t cond1 = PTHREAD_COND_INITIALIZER;
pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond2 = PTHREAD_COND_INITIALIZER;

int n = 1;

void X() {
	int pthread_mutex_lock(pthread_mutex_t *mtx);
	n = n * 16;
	int pthread_cond_signal(pthread_cond_t *cond1);
	int pthread_mutex_unlock(pthread_mutex_t *mtx);
}
void Y() {
	int pthread_mutex_lock(pthread_mutex_t *mtx);
	int pthread_cond_wait(pthread_cond_t *cond2, pthread_mutex_t *mtx);
	n = n / 7 ;
	int pthread_mutex_unlock(pthread_mutex_t *mtx);
}
void Z() {
	int pthread_mutex_lock(pthread_mutex_t *mtx);
	int pthread_cond_wait(pthread_cond_t *cond1, pthread_mutex_t *mtx);
	n = n + 40;
	int pthread_cond_signal(pthread_cond_t *cond2);
	int pthread_mutex_unlock(pthread_mutex_t *mtx);
}

int main(void){
	pthread_t t1, t2, t3;
	int rc;
	rc = pthread_create(&t1, NULL, func, NULL);
	rc = pthread_create(&t2, NULL, func, NULL);
	rc = pthread_create(&t3, NULL, func, NULL);
	rc = pthread_join(t1, NULL);
	rc = pthread_join(t2, NULL);
	rc = pthread_join(t2, NULL);

	printf("%d", n);

	return 0;
}
