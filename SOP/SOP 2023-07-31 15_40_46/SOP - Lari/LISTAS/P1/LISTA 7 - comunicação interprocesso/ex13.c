/* Um dos problemas de sincronização mais conhecidos é o “jantar dos filósofos”, proposto por 
Dijkstra em 1965. Nesse problema, cinco filósofos sentam-se ao redor de uma mesa circular. Cada 
filósofo tem diante de si um prato de macarrão, e entre cada par de pratos existe um garfo (veja a 
figura abaixo). Um filósofo pode estar meditando ou comendo. Quando um filósofo deseja comer, ele 
tenta pegar dois garfos (é impossível comer o macarrão com um único garfo), o que está a sua direita 
e o que está a sua esquerda, em qualquer ordem, um de cada vez (não é possível pegar os dois garfos 
ao mesmo tempo). Se o filósofo consegue pegar os garfos, ele come durante um tempo e depois larga os
garfos na mesa. O problema consiste em escrever um programa (diferente do apresentado no livro do 
Tanenbaum) que represente o comportamento dos filósofos, e que não entre em impasse (deadlock). 
(Dica: considere a possibilidade de limitar o número de filósofos que tentam comer ao mesmo tempo.)
*/


