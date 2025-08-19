/* Considere um programa concorrente com duas threads, T1 e T2, mostradas no código abaixo. 
F, A, J, Q e K são instruções atômicas (indivisíveis) quaisquer. 

void T1() {
F;
A;
}

void T2() {
J;
Q;
K;
}

Determine todas as possíveis sequências de execução das threads acima (mostre as diferentes 
sequências de instruções atômicas que podem ocorrer), considerando que essas threads são disparadas
simultaneamente (ou seja, não é  possível prever qual delas inicia começa a executar primeiro).*/
