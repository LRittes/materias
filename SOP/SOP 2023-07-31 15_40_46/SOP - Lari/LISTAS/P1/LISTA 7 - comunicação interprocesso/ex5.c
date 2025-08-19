/* Suponha que a biblioteca do seu compilador C possui uma instrução swap(&v1, &v2), que troca 
atomicamente o conteúdo de duas variáveis v1 e v2. Mostre como implementar funções enter_region() e 
leave_region() para garantir exclusão mútua em regiões críticas usando a função swap() e uma variável
compartilhada lock. Não se preocupe em eliminar espera ocupada da sua solução. 
DICA: você pode se basear na solução usando TSL para resolver este problema. */
