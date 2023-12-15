	.text
	.globl main

main:
	li $v0, 5
	li $s0, 1
	syscall
	move $s1, $v0

while:
	mul $s0, $s0, $s1
	addi $s1, $s1, -1
	bnez $s1, while

end:
	li $v0, 1
	move $a0, $s0
	syscall
	li $v0, 10
	syscall