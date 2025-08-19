/* Algumas linguagens de programaçao oferecem suporte a concorrência por meio de um par 
de comandos parbegin. . . parend. Seja, por exemplo, o trecho de codigo abaixo: 
parbegin
P1;
P2;
P3;
parend

O uso de parbegin e parend indica que P1, P2 e P3 executam em paralelo, e podem ser escalonados em 
qualquer ordem (ou seja, P3 pode comec¸ar a executar antes de P1 e/ou de P2).
Com base nisso, considere o programa abaixo:

#define N 50
int soma;
void total(void) {
int cont;
for (cont = 1; cont <= N; cont++)
soma++;
}
main() {
soma = 0;
parbegin
total(); total();
parend
printf("A soma eh %d\n", soma);
}

(a) Determine os limites inferior e superior para o valor final da variável compartilhada soma 
exibido por esse programa concorrente. Suponha que os processos podem executar em velocidades 
relativas quaisquer e que uma operação de incremento X++ é implementada por três instruções de 
máquina: 
MOVE X, ACC ; copia X para o acumulador
INC ACC ; incrementa o acumulador
MOVE ACC, X ; copia o acumulador para X

(b) Suponha que um numero arbitrário desses processos podem executar segundo as premissas da parte (a). ´
Qual o efeito de tal modificação na faixa de valores finais de soma?

