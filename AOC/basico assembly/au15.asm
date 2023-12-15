.data
	msg: .asciiz "Digite um número: "
	spc: .byte ' '

.text
	li $v0, 4
	la $a0, msg
	syscall
	
	li $v0, 5
	syscall
	move $t0, $v0
	
	move $t1, $zero
	
	while:
		bgt $t1, $t0, saida
		
		li $v0, 1
		move $a0, $t1
		syscall
		
		li $v0, 4
		la $a0, spc
		syscall
		
		addi $t1, $t1, 1
		j while
	
	saida:
		li $v0, 10
		syscall