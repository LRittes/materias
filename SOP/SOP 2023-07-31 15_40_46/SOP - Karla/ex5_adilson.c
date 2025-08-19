# include <stdio.h>
# include <stdlib.h>

typedef struct Listas Listas;


struct Listas
{
    char name[30];
    int prioridade;
};



int main(void){
    Listas *p;
    int quant =0;
    

    p = (Listas *)(malloc(sizeof(Listas)*quant+1));

    while(1){
        scanf(" %s",p[quant].name);

        if(p[quant].name[0] == 'F' && p[quant].name[1]=='I'  && p[quant].name[2] == 'M'){
            quant --;
            break;
        }

        scanf("%i",&p[quant].prioridade);
         
        if(p[quant].name[0] == 'I' && p[quant].name[1]=='G'  && p[quant].name[2] == 'U' && p[quant].name[3] == 'A' && p[quant].name[4] == 'L'){
            for(int i=0;i<=quant;i++){
                if (p[i].prioridade == p[quant].prioridade){
                    printf("%s ",p[i].name);
                }
            }
            quant --;

        }
        if(p[quant].name[0] == 'M' && p[quant].name[1]=='E'  && p[quant].name[2] == 'N' && p[quant].name[3] == 'I' && p[quant].name[4] == 'N' && p[quant].name[5] == 'G'){
             for(int i=0;i<=quant;i++){
                if (p[i].prioridade <= p[quant].prioridade){
                    printf("%s ",p[i].name);
                }
            }
            quant --;
        }

        for(int i=0; i<=quant;i++){
            printf("%s ",p[i].name);
            printf("%i \n",p[i].prioridade);
        }

        quant++;
        p=(Listas *)(realloc(p,sizeof(Listas)*(quant+1)));

    }

    return 0;
}