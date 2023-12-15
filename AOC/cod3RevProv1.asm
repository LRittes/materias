.text
	li $v0, 5
	syscall
	
	move $s0, $v0
	
	jal sum	
	
	move $a0, $v0
	
	li $v0, 1
	syscall
	
	j end
	
	sum:	
		add $a0, $a0, $s0
		addi $s0, $s0, -1 
		bnez $s0, sum
		
		move $v0, $a0
		
		jr $ra
	
	end:
		li $v0, 10
		syscall