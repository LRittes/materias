.data
	msg: .asciiz "Digite um valor: "
	ePar: .asciiz "O número é par"
	eImpar: .asciiz "O número é impar"

.text
	li $v0, 4
	la $a0, msg
	syscall
	
	li $v0, 5
	syscall
	move $s0, $v0
	
	li $t0, 2
	div $s0, $t0
	
	mfhi $t1
	
	beq $t1, $zero, imprimePar
	
	li $v0, 4
	la $a0, eImpar
	syscall
	
	li $v0, 10
	syscall
	
	imprimePar:
		li $v0, 4
		la $a0, ePar
		syscall
	