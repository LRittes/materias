/* O problema dos leitores/escritores consiste de um texto que pode ser lido ou escrito por v�rios 
processos. Considerando o c�digo abaixo, responda justificando: 
(a) E poss�vel v�rios leitores lerem ao mesmo tempo? 
(b) E poss�vel v�rios escritores escreverem ao mesmo tempo? 
(c) E poss�vel posterga��o indefinida de um leitor? (A posterga��o indefinida de um processo ocorre 
quando ele pode ser impedido de entrar em uma se��o cr�tica por um tempo ilimitado, enquanto outros 
processos continuam entrando e saindo dessa se��o cr�tica.)
(d) E poss�vel posterga��o indefinida de um escritor? 
(e) E poss�vel leitores e escritores acessarem ao mesmo tempo?


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


