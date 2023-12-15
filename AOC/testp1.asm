.text
	li $s0, 32
	
	addi $sp, $sp, -4
	sw $s0, 0($sp)
	li $s0, 76
	
	addi $sp, $sp, -4
	sw $s0, 0($sp)
	li $s0, 45
	
	lw $s0, 0($sp)
	lw $s0, 4($sp)
	
	addi $sp, $sp, 8