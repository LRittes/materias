/* Escreva um programa em C que inicialize um vetor global de N inteiros com valores aleatórios
(use a função random()), e na sequência crie nthr threads. Cada thread possui um ID numérico
(de 1 a nthr), e seu comportamento depende do ID: threads com ID par devem encontrar o maior
elemento do vetor, e threads com ID ímpar devem encontrar o menor elemento. Após terminar de 
percorrer o vetor, a thread deve inserir seu ID no fim de uma fila de threads concluídas (dica: 
use um vetor global com nthr elementos, e uma variável para indicar em que posição desse vetor deve
ser inserido o proximo ID), e retornar o maior/menor elemento. O programa principal deve verificar
que o elemento encontrado por uma thread está correto, e imprimir uma mensagem de erro caso contrario. 
Depois que todas as threads tiverem encerrado, o programa principal deve imprimir a ordem em que elas
terminaram (de acordo com a fila), conforme o exemplo abaixo:
ordem: 3 1 2 4
O valor de nthr será o primeiro argumento na linha de comando. Se houver um segundo argumento na 
linha de comando, este será o valor de N; se apenas um argumento for passado na linha de comando, 
N terá o valor default 10. */


