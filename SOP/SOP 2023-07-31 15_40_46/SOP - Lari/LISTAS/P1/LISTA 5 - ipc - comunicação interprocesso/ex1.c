/* Suponha que, em um sistema, existem diversas aplica��es que, em determinados pontos,
consomem uma quantidade significativa de mem�ria. Para evitar a exaust�o da mem�ria f�sica do 
sistema, definiu-se que essas aplica��es devem ser escritas de modo que apenas uma delas pode 
executar suas opera��es que demandam mem�ria de cada vez. Em outras palavras, � necess�rio garantir 
a exclus�o m�tua no uso da mem�ria pelas aplica��es. Mostre como as aplica��es poderiam ser 
constru�das usando sem�foros. Sua solu��o deve eliminar espera ocupada.
Dica: a aplica��o pode ser modelada da seguinte forma: */

void aplicacao(){
	while (TRUE){
		faz_algo();
		come_memoria();
	}
}


semaforo s =1;

down(&s);

up(&s);


