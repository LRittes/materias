/* O problema dos leitores/escritores consiste de um texto que pode ser lido ou escrito por vários 
processos. Considerando o código abaixo, responda justificando: 
(a) E possível vários leitores lerem ao mesmo tempo? 
(b) E possível vários escritores escreverem ao mesmo tempo? 
(c) E possível postergação indefinida de um leitor? (A postergação indefinida de um processo ocorre 
quando ele pode ser impedido de entrar em uma seção crítica por um tempo ilimitado, enquanto outros 
processos continuam entrando e saindo dessa seção crítica.)
(d) E possível postergação indefinida de um escritor? 
(e) E possível leitores e escritores acessarem ao mesmo tempo?


int nl = 0;
semaphore tipo = 1;
semaphore exclusivo = 1;
void leitor(void)
{ ...
down(&exclusivo);
if (nl > 0) ++nl;
else {
down(&tipo);
nl = 1;
}
up(&exclusivo);
acessa_texto();
down(&exclusivo);
--nl;
if (nl == 0) up(&tipo);
up(&exclusivo);
...
}

*/


