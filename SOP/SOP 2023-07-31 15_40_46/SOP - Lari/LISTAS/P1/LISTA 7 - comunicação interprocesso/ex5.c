/* Suponha que a biblioteca do seu compilador C possui uma instru��o swap(&v1, &v2), que troca 
atomicamente o conte�do de duas vari�veis v1 e v2. Mostre como implementar fun��es enter_region() e 
leave_region() para garantir exclus�o m�tua em regi�es cr�ticas usando a fun��o swap() e uma vari�vel
compartilhada lock. N�o se preocupe em eliminar espera ocupada da sua solu��o. 
DICA: voc� pode se basear na solu��o usando TSL para resolver este problema. */
