/*Escreva um programa que conte at� 2^31 (1<<31). O programa deve ter N threads, e cada thread 
deve contribuir 2^31/N para o total. A contagem em cada thread deve ser em incrementos
unit�rios (i.e., total_thr++), e o valor final deve ser retornado para main() e somado ao total
global. Caso a divis�o de 2^31 por N seja inexata, o resto deve ser acrescido em main(), tamb�m
em incrementos unit�rios. Verifique se, incrementando N, ha varia��o no tempo de execu��o do programa. */
