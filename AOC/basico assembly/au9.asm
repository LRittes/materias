.data
	msg1: .asciiz "Qual sua idade: "
	msg2: .ascii "sua idade é: "

.text
	# imprime a msg1
	li $v0, 4
	la $a0, msg1
	syscall
	
	# coleta a entrada do usuário
	li $v0, 5
	syscall
	move $s0, $v0
	
	# imprime a msg2
	li $v0, 4
	la $a0, msg2
	syscall
	
	# printa na saida padrão oq está em a0
	li $v0, 1
	move $a0, $s0
	syscall