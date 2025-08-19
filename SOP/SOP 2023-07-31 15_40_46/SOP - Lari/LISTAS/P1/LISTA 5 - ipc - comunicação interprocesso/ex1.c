/* Suponha que, em um sistema, existem diversas aplicações que, em determinados pontos,
consomem uma quantidade significativa de memória. Para evitar a exaustão da memória física do 
sistema, definiu-se que essas aplicações devem ser escritas de modo que apenas uma delas pode 
executar suas operações que demandam memória de cada vez. Em outras palavras, é necessário garantir 
a exclusão mútua no uso da memória pelas aplicações. Mostre como as aplicações poderiam ser 
construídas usando semáforos. Sua solução deve eliminar espera ocupada.
Dica: a aplicação pode ser modelada da seguinte forma: */

void aplicacao(){
	while (TRUE){
		faz_algo();
		come_memoria();
	}
}


semaforo s =1;

down(&s);

up(&s);


