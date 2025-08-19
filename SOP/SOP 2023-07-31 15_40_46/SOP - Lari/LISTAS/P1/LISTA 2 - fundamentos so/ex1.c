/* Considere um processador hipot�tico, semelhante ao usado no exemplo do slide 36, que possua os 
seguintes opcodes:

0 AC <- num
1 AC <- mem
2 mem <- AC
3 AC <- AC + mem
4 AC <- AC - mem
5 desvie para mem
6 se AC=0, desvie para mem
7 l� n�mero do dispositivo de entrada e coloca em AC
8 envia AC para o dispositivo de sa�da

O operando de cada instru��o � um n�mero num (para o opcode 0), o endere�oo de mem�ria mem (para
os opcodes 1�6) ou o n�mero do dispositivo de E/S (para os opcodes 7 e 8), que pode ser 0 para o
teclado, 1 para o v�deo ou 2 para a interface de rede. Seguindo o formato ilustrado no slide 36, 
mostre a execu��o do seguinte programa: 
1: Leia um n�mero do teclado e o coloque no acumulador (AC); 
2: Adicione o conte�do da posi��o de mem�ria 940; 
3: Imprima o conte�do do acumulador no v�deo.
Suponha que o n�mero lido seja 3 e que a posi��o 940 contenha o valor 2.


