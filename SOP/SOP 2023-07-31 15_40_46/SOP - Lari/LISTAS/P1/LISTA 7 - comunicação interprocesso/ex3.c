/* Considere um programa concorrente com duas threads, T1 e T2, mostradas no c�digo abaixo. 
F, A, J, Q e K s�o instru��es at�micas (indivis�veis) quaisquer. 

void T1() {
F;
A;
}

void T2() {
J;
Q;
K;
}

Determine todas as poss�veis sequ�ncias de execu��o das threads acima (mostre as diferentes 
sequ�ncias de instru��es at�micas que podem ocorrer), considerando que essas threads s�o disparadas
simultaneamente (ou seja, n�o �  poss�vel prever qual delas inicia come�a a executar primeiro).*/
