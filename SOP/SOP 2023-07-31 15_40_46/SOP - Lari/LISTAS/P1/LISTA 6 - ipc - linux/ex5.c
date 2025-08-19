/* Escreva um programa multithread em que N threads usem repetidas vezes uma barreira para
sincronização. Cada thread i repete 10 vezes os seguintes passos:
(1) Incrementa uma variável inteira 109 vezes (se necessário, ajuste o número de incrementos
para que cada rodada demore alguns segundos);
(2) Insere o valor i no final de uma fila que armazena a sequência de threads que terminaram o
laço interno;
(3) Se for a última thread a terminar, imprime a fila, mostrando assim a ordem em que as threads
concluíram os incrementos a cada rodada;
(4) Espera em uma barreira até que todas as threads tenham concluído a rodada antes de iniciar
a próxima rodada.
O número N de threads a serem criadas deve ser um parâmetro informado via linha de comando
(argv).
Verifique se pode ser identificada alguma tendência na ordem de conclusão das threads quando
N for menor, igual ou maior do que o número de núcleos do seu processador. Considere questões como:
a ordem é sempre a mesma em todas as rodadas? A primeira ou última thread é sempre a mesma? 
Existe alguma ordem que aparece com muito mais frequência que as demais? O comportamento se 
repete em múltiplas execuções do programa?

