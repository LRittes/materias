#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct par Par;
struct par{
    char nome[30];
    int prioridade;
};

int main(){

    int cont = 0;
    Par *vect;
    vect = (Par *)(malloc(sizeof(Par) * 1));


    while(1){

        scanf("%s %d", vect[cont].nome, &vect[cont].prioridade);

        if(strcmp("FIM", vect[cont].nome) == 0){
            break;
        }else if(strcmp("IGUAL", vect[cont].nome) == 0){
            printf("Prioridade %d: ", vect[cont].prioridade);
            for(int i = 0; i <= cont ; i++){
                if (vect[i].prioridade == vect[cont].prioridade && i != cont){
                    printf("%s ",vect[i].nome);
                }
            }
            printf("\n");
            cont--;
        }else if(strcmp("MENIG", vect[cont].nome) == 0){
            printf("Menor igual prioridade %d: ", vect[cont].prioridade);
            for(int i = 0; i <= cont; i++){
                if (vect[i].prioridade <= vect[cont].prioridade && i != cont){
                    printf("%s ",vect[i].nome);
                }
            }
            printf("\n");
            cont--;
        }else{ 
            cont++;
            vect = (Par *)(realloc(vect, sizeof(Par)*(cont+1)));
        }
    }

    return 0;
}