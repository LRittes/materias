/* Considere o problema da aloca��o de arm�rios de a�o na biblioteca da UDESC. Existe um n�mero fixo
NARM de ar�arios. Normalmente, um usu�rio que chega a biblioteca e deseja um arm�rio solicita ao ]
funcion�rio respons�vel a chave de um dos arm�rios desocupados. Caso n�o haja um arm�rio livre, 
o usu�rio espera em uma fila at� que algum arm�rio seja desocupado. Para eliminar a necessidade de 
um funcion�rio para a distribui��o de chaves, um aluno de SOP desenvolveu o seguinte algoritmo, 
baseado em sleep() e wakeup():

int armarios[NARM], ocupados = 0;

 void usuario(int i) {
 	int a; // n�mero do arm�rio ocupado pelo usu�rio 

 	if (ocupados == NARM)
 		sleep(ocupados);
 	ocupados++;
 	
   for (a = 0; (a < NARM) && (armarios[a] != 0); a++); // percorre o vetor at� achar um arm�rio livre.
 	armarios[a] = i;
 	estuda();
 	armarios[a] = 0;
 	ocupados--;
 	
	 if (ocupados == NARM-1)
		 wakeup(ocupados);
 }
 
Esse algoritmo resolve o problema corretamente? Quais erros voc� consegue apontar nessa solu��o, se 
� que eles existem? Demonstre o(s) erro(s) mostrando sequ�ncias de execu�ao que causem problemas. 
Obs.: O uso de ocupados como par�metro de sleep() e wakeup() n�o constitui um erro. Nesse caso, 
ocupados � usado para �casar� um wakeup() com o sleep() correspondente (ou seja, wakeup(ocupados) 
acorda um processo dentre os que executaram sleep(ocupados)). */


