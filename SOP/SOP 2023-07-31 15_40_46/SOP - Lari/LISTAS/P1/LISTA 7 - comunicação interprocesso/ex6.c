/* Considere um programa concorrente com dois processos, X e Y, mostrados abaixo. A vari�vel S � um 
sem�foro compartilhado pelos dois processos, que e inicializado em 0.

semaphore S = 0; // sem�foro compartilhado entre X e Y 
void X() {
int i;
for (i = 0; i < 4; i++) {
printf("a");
down(&S);
}
}


void Y() {
int j;
for (j = 0; j < 4; j++) {
printf("b");
up(&S);
}
}

Determine quais das strings abaixo podem ser geradas pelo programa acima e quais delas n�o podem: 
(a) abbabbaa
(b) abbabaab
(c) aabbabab
(d) baaabbab
(e) bbbaaaab
(f) aaabbbba

*/


