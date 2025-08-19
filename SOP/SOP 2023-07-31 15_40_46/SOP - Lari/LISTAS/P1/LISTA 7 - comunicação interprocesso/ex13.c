/* Um dos problemas de sincroniza��o mais conhecidos � o �jantar dos fil�sofos�, proposto por 
Dijkstra em 1965. Nesse problema, cinco fil�sofos sentam-se ao redor de uma mesa circular. Cada 
fil�sofo tem diante de si um prato de macarr�o, e entre cada par de pratos existe um garfo (veja a 
figura abaixo). Um fil�sofo pode estar meditando ou comendo. Quando um fil�sofo deseja comer, ele 
tenta pegar dois garfos (� imposs�vel comer o macarr�o com um �nico garfo), o que est� a sua direita 
e o que est� a sua esquerda, em qualquer ordem, um de cada vez (n�o � poss�vel pegar os dois garfos 
ao mesmo tempo). Se o fil�sofo consegue pegar os garfos, ele come durante um tempo e depois larga os
garfos na mesa. O problema consiste em escrever um programa (diferente do apresentado no livro do 
Tanenbaum) que represente o comportamento dos fil�sofos, e que n�o entre em impasse (deadlock). 
(Dica: considere a possibilidade de limitar o n�mero de fil�sofos que tentam comer ao mesmo tempo.)
*/


