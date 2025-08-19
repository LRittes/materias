/* Escreva um programa multithread para encontrar o menor elemento em uma matriz N � N de
inteiros sem sinal. O n�mero T de threads a serem criadas deve ser um par�metro informado
via linha de comando (argv). A matriz deve ser logicamente particionada entre as threads (voc�
pode supor que N � divis�vel por T): cada thread deve primeiro encontrar o menor elemento em
sua parti��o, e depois reportar quantas threads encontraram elementos menores que o seu. O
programa principal (main()) deve reportar o menor elemento da matriz.

A matriz pode ser preenchida com valores aleat�rios. Lembre-se que, para que uma thread
possa determinar quantas threads encontraram elementos menores que o seu m�nimo, 
� necess�rio que todas as threads tenham encontrado o seu menor (use uma barreira para garantir isso)
