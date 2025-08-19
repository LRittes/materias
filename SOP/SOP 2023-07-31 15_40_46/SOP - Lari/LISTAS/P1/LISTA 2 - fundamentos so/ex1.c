/* Considere um processador hipotético, semelhante ao usado no exemplo do slide 36, que possua os 
seguintes opcodes:

0 AC <- num
1 AC <- mem
2 mem <- AC
3 AC <- AC + mem
4 AC <- AC - mem
5 desvie para mem
6 se AC=0, desvie para mem
7 lê número do dispositivo de entrada e coloca em AC
8 envia AC para o dispositivo de saída

O operando de cada instrução é um número num (para o opcode 0), o endereçoo de memória mem (para
os opcodes 1–6) ou o número do dispositivo de E/S (para os opcodes 7 e 8), que pode ser 0 para o
teclado, 1 para o vídeo ou 2 para a interface de rede. Seguindo o formato ilustrado no slide 36, 
mostre a execução do seguinte programa: 
1: Leia um número do teclado e o coloque no acumulador (AC); 
2: Adicione o conteúdo da posição de memória 940; 
3: Imprima o conteúdo do acumulador no vídeo.
Suponha que o número lido seja 3 e que a posição 940 contenha o valor 2.


