/* O Joinville Esporte Clube encomendou um sistema para venda de ingressos para seus jogos atrav�s 
de quiosques eletr�nicos. As partidas s�o realizadas na Arena Joinville, que tem capacidade para 
MAX torcedores; os lugares s�o numerados sequencialmente, de 1 a MAX. O sistema deve funcionar da 
seguinte forma: o cliente escolhe o lugar que deseja (dentre os dispon�veis) e efetua o pagamento. 
O pagamento � feito por cart�o, e pode ser aprovado pela operadora ou n�o (neste caso, o cliente 
perde o direito ao lugar escolhido, que volta a ficar dispon�vel). O sistema nao pode vender duas 
vezes o mesmo lugar, nem pode marcar como ocupados lugares escolhidos por clientes que n�o conseguem
efetuar o pagamento. Apenas um lugar � vendido por vez. Escreva o pseudoc�digo das aplica��es que 
executam nos quiosques, usando sem�foros para evitar condi��ees de disputa. Considere que a 
autoriza��o de pagamento � feita por linha discada, estando sujeita a falhas de conex�o e a longos 
tempos de espera (ou seja, um cliente que aguarda a autoriza��o de pagamento n�o pode prejudicar a 
venda de ingressos em outros quiosques). */


