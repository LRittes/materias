#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

    int a = atoi(argv[1]), v[a], n = 0, aux = 0, i;

    while (n != -1)
    {
        scanf("%d", &n);
        if(n == -2){
            printf("Fila: ");
            for(i = 0; i < a && i < aux; i++){
                printf("%d ", v[i]);
            }
            printf("\n");
        }
        else{
            aux++;
            if(aux > a){
                for(i = 0; i < a-1; i++){
                    v[i] = v[i+1];
                }
                v[a-1] = n;
            }else{
                v[aux-1] = n;
            }
        }
    }
    

    return 0;
}