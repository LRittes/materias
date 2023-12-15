.data 
	ask: .asciiz "Qual o seu nome: "
	answer: .asciiz "Olá, "
	nome: .space 25 # space = espaço a ser armazenado
	
.text
	li $v0, 4
	la $a0, ask
	syscall
	
	li $v0, 8 # código 8 = ler string do usuário
	la $a0, nome # passa para a0 a entrada do usuário fornecida q está em nome
	la $a1, 25 # informa o tamanho máximo que queremos ler
	syscall
	
	li $v0, 4
	la $a0, answer
	syscall
	
	li $v0, 4
	la $a0, nome
	syscall