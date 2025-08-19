#include <stdio.h>

int main(int argc, char *argv[]){

    int cont;

    printf("Programa: %s\n", argv[0]);
    printf("Parametros:\n");

    for(cont = argc-1; cont > 0; cont--){
        printf("%s\n", argv[cont]);
    }

    return 0;
}