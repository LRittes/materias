/* Escreva um programa multithread em que N threads usem repetidas vezes uma barreira para
sincroniza��o. Cada thread i repete 10 vezes os seguintes passos:
(1) Incrementa uma vari�vel inteira 109 vezes (se necess�rio, ajuste o n�mero de incrementos
para que cada rodada demore alguns segundos);
(2) Insere o valor i no final de uma fila que armazena a sequ�ncia de threads que terminaram o
la�o interno;
(3) Se for a �ltima thread a terminar, imprime a fila, mostrando assim a ordem em que as threads
conclu�ram os incrementos a cada rodada;
(4) Espera em uma barreira at� que todas as threads tenham conclu�do a rodada antes de iniciar
a pr�xima rodada.
O n�mero N de threads a serem criadas deve ser um par�metro informado via linha de comando
(argv).
Verifique se pode ser identificada alguma tend�ncia na ordem de conclus�o das threads quando
N for menor, igual ou maior do que o n�mero de n�cleos do seu processador. Considere quest�es como:
a ordem � sempre a mesma em todas as rodadas? A primeira ou �ltima thread � sempre a mesma? 
Existe alguma ordem que aparece com muito mais frequ�ncia que as demais? O comportamento se 
repete em m�ltiplas execu��es do programa?

