#include <stdio.h>
#include <stdlib.h>

#define soma 43
#define sub 45 
#define mult 42 
#define div 47 

#define um 49 	
#define dois 50 	
#define tres 51 	
#define quatro 52 	
#define cinco 53 	
#define seis 54 	
#define sete 55 	
#define oito 56 	
#define nove 57

struct Pilha {
	int topo; 
   int capa;
	float *pElem;
};

void criarpilha( struct Pilha *p, int c ){
   p->topo = -1;
   p->capa = c;
   p->pElem = (float*) malloc (c * sizeof(float));
}

int estavazia ( struct Pilha *p ){
   if( p-> topo == -1 )
      return 1;   // true
   else
      return 0;   // false
}

int estacheia ( struct Pilha *p ){
	if (p->topo == p->capa - 1)
		return 1;
	else
		return 0;
}

void empilhar ( struct Pilha *p, float v){
	p->topo++;
	p->pElem [p->topo] = v;
}

void desempilhar ( struct Pilha *p, int c ){
   p->topo = p->topo - c;
}

float retornatopo ( struct Pilha *p ){
   return p->pElem [p->topo];
}

int main(){
   struct Pilha pilha;
   char expre[20];
   printf("Escreva sua expressão: ");
   scanf("%s", expre);
   criarpilha(&pilha,20);

   for(int i = 0; expre[i] != '\0'; i++){
      if(expre[i] == soma) {
         float n1 = pilha.pElem[pilha.topo],n2 = pilha.pElem[pilha.topo-1];
         desempilhar(&pilha,2);
         empilhar(&pilha,n2+n1);
      }
      if(expre[i] == sub){
         float n1 = pilha.pElem[pilha.topo],n2 = pilha.pElem[pilha.topo-1];
         desempilhar(&pilha,2);
         empilhar(&pilha,n2-n1);
      }
      if(expre[i] == div){
         float n1 = pilha.pElem[pilha.topo],n2 = pilha.pElem[pilha.topo-1];
         desempilhar(&pilha,2);
         empilhar(&pilha,n2/n1);
      }
      if(expre[i] == mult){
         float n1 = pilha.pElem[pilha.topo],n2 = pilha.pElem[pilha.topo-1];
         desempilhar(&pilha,2);
         empilhar(&pilha,n2*n1);
      }
      if(expre[i] != sub && expre[i] != soma && expre[i] != div && expre[i] != mult) {
         float n1;
         switch (expre[i]){
            case um:
               n1 = 1;
               break;
            case dois:
               n1 = 2;
               break;
            case tres:
               n1 = 3;
               break;
            case quatro:
               n1 = 4;
               break;
            case cinco:
               n1 = 5;
               break;
            case seis:
               n1 = 6;
               break;
            case sete:
               n1 = 7;
               break;
            case oito:
               n1 = 8;
               break;
            case nove:
               n1 = 9;
               break;
         }
         empilhar(&pilha,n1);
      }
   }
   printf("%f", pilha.pElem[pilha.topo]);
}