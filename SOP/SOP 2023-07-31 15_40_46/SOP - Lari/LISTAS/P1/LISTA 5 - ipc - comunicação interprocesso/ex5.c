/* O Joinville Esporte Clube encomendou um sistema para venda de ingressos para seus jogos através 
de quiosques eletrônicos. As partidas são realizadas na Arena Joinville, que tem capacidade para 
MAX torcedores; os lugares são numerados sequencialmente, de 1 a MAX. O sistema deve funcionar da 
seguinte forma: o cliente escolhe o lugar que deseja (dentre os disponíveis) e efetua o pagamento. 
O pagamento é feito por cartão, e pode ser aprovado pela operadora ou não (neste caso, o cliente 
perde o direito ao lugar escolhido, que volta a ficar disponível). O sistema nao pode vender duas 
vezes o mesmo lugar, nem pode marcar como ocupados lugares escolhidos por clientes que não conseguem
efetuar o pagamento. Apenas um lugar é vendido por vez. Escreva o pseudocódigo das aplicações que 
executam nos quiosques, usando semáforos para evitar condiçõees de disputa. Considere que a 
autorização de pagamento é feita por linha discada, estando sujeita a falhas de conexão e a longos 
tempos de espera (ou seja, um cliente que aguarda a autorização de pagamento não pode prejudicar a 
venda de ingressos em outros quiosques). */


