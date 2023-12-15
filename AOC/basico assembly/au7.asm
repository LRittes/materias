.text
	li $t0, 6
	sll $t1, $t0, 1 # faz shift left de t1 n casas a esquerda 
			# 6 = 000110 -> 12 = 001100
	li $v0, 1
	move $a0, $t1
	syscall