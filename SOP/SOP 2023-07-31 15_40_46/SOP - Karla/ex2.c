#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

    int r, i, cont = 0, a = atoi(argv[1]);

    for(i = 31; i >= 0; i--) {
        // Executa a operação shift right até a última posição da direita para cada bit.
        r = a >> i;
        if(r & 1) {
            cont++;
        }
    }
    printf("O número %d possui %d bits em 1.\n", a, cont);

    return 0;
}