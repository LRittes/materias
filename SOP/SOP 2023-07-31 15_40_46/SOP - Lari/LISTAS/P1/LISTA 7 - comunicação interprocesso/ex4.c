/* ] Descreva o erro na implementação do produtor-consumidor mostrada abaixo. Crie uma 
sequência de eventos que termina em algum comportamento indesejado para o programa.

struct tipo_dado buffer[N];
int proxima_insercao = 0;
int proxima_remocao = 0;
...
semaphore exclusao_mutua = 1;
semaphore espera_vaga = N;
semaphore espera_dado = 0;
...
void produtor(void)
{ ...
down(&exclusao_mutua);
down(&espera_vaga);
buffer[proxima_insercao] = dado_produzido;
proxima_insercao = (proxima_insercao + 1) % N;
up(&exclusao_mutua);
up(&espera_dado);
...
}
...
void consumidor(void)
{ ...
down(&exclusao_mutua);
down(&espera_dado);
dado_a_consumir = buffer[proxima_remocao];
proxima_remocao = (proxima_remocao + 1) % N;
up(&exclusao_mutua);
up(&espera_vaga);
...
}

*/


