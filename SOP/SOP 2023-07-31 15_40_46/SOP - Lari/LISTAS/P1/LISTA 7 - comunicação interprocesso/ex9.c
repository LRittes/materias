/* O programa abaixo gerencia um estacionamento de automóveis. O estacionamento possui ANDARES 
andares com MAXVAGAS vagas em cada um. Existem múltiplas cancelas para entrada e saída, e cada 
cancela executa o programa abaixo, sendo que as informações referentes às vagas de estacionamento 
disponíveis são compartilhadas por todas as cancelas. Um carro que chegue ao estacionamento provoca a
execução da função libera ticket(), que localiza uma vaga no estacionamento e fornece um tiquete ao 
usuário informando o andar e a vaga em que ele deverá estacionar (caso não haja vagas disponíveis, 
é aberta uma cancela de escape para que o carro possa sair). Quando um carro sai do estacionamento, 
o motorista insere seu tiquete na máquina, o que causa a execução da função retorna ticket(); esta 
função deve então liberar no sistema a vaga que estava sendo ocupada por esse carro e abrir a cancela
de saída.
(a) Identifique as regiões críticas no código (use os números de linhas). 
(b) Mostre ao menos uma sequência de execução que pode provocar um comportamento indesejado. 
(c) Use semáforos para garantir exclusão mútua nas regiões críticas.


typedef struct { int andar, vaga; } ticket_t;

carro_t vagas[ANDARES][MAXVAGAS]; // matriz de ocupacao de vagas

 int ocupadas = 0; // no. de vagas ocupadas 

 ticket_t libera_ticket(carro_t carro)
 {
 int a, v;
 ticket_t ticket;
 if (ocupadas < ANDARES*MAXVAGAS) {// existem vagas disponiveis? 
 for (a = 0; a < ANDARES; a++) { // percorre o mapa de vagas 
 for (v = 0; v < MAXVAGAS; v++) {
 if (vagas[a][v] == LIVRE) { // se encontrou uma vaga livre... 
 ocupadas++; // contabiliza a ocupacao da vaga 
 vagas[a][v] = carro; // ajusta o mapa de vagas 
 ticket.andar = a;
 ticket.vaga = v;
 imprime_ticket(ticket); // imprime o ticket para o motorista 
 libera_entrada(); // abre a cancela de entrada 
 return ticket;
 }
 }
 }
 }
 libera_escape(); // estacionamento cheio, abre cancela de escape 
 return ticket;
 }

 void retorna_ticket(ticket_t ticket)
 {
 ocupadas--; // contabiliza a liberacao da vaga 
 vagas[ticket.andar][ticket.vaga] = LIVRE; // ajusta o mapa de vagas 
 libera_saida(); // abre a cancela de sa´ida 
 }
 /*
 
