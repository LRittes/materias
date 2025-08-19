/*Escreva um programa que conte até 2^31 (1<<31). O programa deve ter N threads, e cada thread 
deve contribuir 2^31/N para o total. A contagem em cada thread deve ser em incrementos
unitários (i.e., total_thr++), e o valor final deve ser retornado para main() e somado ao total
global. Caso a divisão de 2^31 por N seja inexata, o resto deve ser acrescido em main(), também
em incrementos unitários. Verifique se, incrementando N, ha variação no tempo de execução do programa. */
