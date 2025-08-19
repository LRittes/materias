/* Considere o problema da alocação de armários de aço na biblioteca da UDESC. Existe um número fixo
NARM de aráarios. Normalmente, um usuário que chega a biblioteca e deseja um armário solicita ao ]
funcionário responsável a chave de um dos armários desocupados. Caso não haja um armário livre, 
o usuário espera em uma fila até que algum armário seja desocupado. Para eliminar a necessidade de 
um funcionário para a distribuição de chaves, um aluno de SOP desenvolveu o seguinte algoritmo, 
baseado em sleep() e wakeup():

int armarios[NARM], ocupados = 0;

 void usuario(int i) {
 	int a; // número do armário ocupado pelo usuário 

 	if (ocupados == NARM)
 		sleep(ocupados);
 	ocupados++;
 	
   for (a = 0; (a < NARM) && (armarios[a] != 0); a++); // percorre o vetor até achar um armário livre.
 	armarios[a] = i;
 	estuda();
 	armarios[a] = 0;
 	ocupados--;
 	
	 if (ocupados == NARM-1)
		 wakeup(ocupados);
 }
 
Esse algoritmo resolve o problema corretamente? Quais erros você consegue apontar nessa solução, se 
é que eles existem? Demonstre o(s) erro(s) mostrando sequências de execuçao que causem problemas. 
Obs.: O uso de ocupados como parâmetro de sleep() e wakeup() não constitui um erro. Nesse caso, 
ocupados é usado para “casar” um wakeup() com o sleep() correspondente (ou seja, wakeup(ocupados) 
acorda um processo dentre os que executaram sleep(ocupados)). */


