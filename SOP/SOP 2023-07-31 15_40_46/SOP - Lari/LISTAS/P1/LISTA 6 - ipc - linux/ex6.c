/* Escreva um programa multithread para encontrar o menor elemento em uma matriz N × N de
inteiros sem sinal. O número T de threads a serem criadas deve ser um parâmetro informado
via linha de comando (argv). A matriz deve ser logicamente particionada entre as threads (você
pode supor que N é divisível por T): cada thread deve primeiro encontrar o menor elemento em
sua partição, e depois reportar quantas threads encontraram elementos menores que o seu. O
programa principal (main()) deve reportar o menor elemento da matriz.

A matriz pode ser preenchida com valores aleatórios. Lembre-se que, para que uma thread
possa determinar quantas threads encontraram elementos menores que o seu mínimo, 
é necessário que todas as threads tenham encontrado o seu menor (use uma barreira para garantir isso)
