	.data
		erro: .asciiz "ERRO!" 
	
	.text
	.globl main

main:
	li $s1, -1 # s1 = -1
	while:
		add $s0, $s0, $s2 # s0 = s0 + s2
		add $s1, $s1, 1 # s1 = s1 + 1
		li $v0, 5 # input do usuário
		syscall
		move $s2, $v0 # s2 = v0
		bne $s2, -1, while # if( s2 != -1) while
	
	li $v0, 1      # saida padrão do sistema
	move $a0, $s0  # a0 = s0
	syscall
	
	li $v0, 11       # Carregando o código da syscall para imprimir caractere
	li $a0, 10       # Carregando o valor ASCII para nova linha
	syscall          # Chamando a syscall para imprimir o caractere de nova linha

	
	beq $s1, $zero, imprimeErro # if(s1 == 0) imprimeErro()
	
	# else
	divu $s3, $s0, $s1 # s3 = s0 / s1
	li $v0, 1
	move $a0, $s3
	syscall

end:
	li $v0, 10
	syscall

imprimeErro:
	li $v0, 4 # saida padrão do sistema
	la $a0, erro # a0 = erro
	syscall
	


