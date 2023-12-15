.text
	li $t0, 12
	li $t1, 10
	mul $s0, $t0, $t1
	li $v0, 1
	move $a0, $s0 #mover valor de um registrador a outro
	syscall