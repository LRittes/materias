.data
	quebra_linha: .asciiz "\n"
	incrementa: .word 0  
	
.text
.globl main

main:
	sw $zero, incrementa

	li $v0, 5  
	syscall  
	move $a1, $v0
	
	beq $a1, $zero, end  
	
	li $v0, 5
	syscall  
	move $a2, $v0
	
	jal contador
	
	lw $t1, incrementa
	move $a0, $t1
	li $v0, 1
	
	syscall
	la $a0, quebra_linha
	li $v0, 4
	syscall
	
	j main

contador:

	subu $sp, $sp, 16
	sw $ra, ($sp)
	sw $a0, 4($sp)   
	sw $a1, 8($sp)  
	sw $a2, 12($sp) 
	
	li $t0, 10  
	div $a1, $t0
	mfhi $a0
	mflo $a1
			

	li $v0, 1  
	beq  $a1, $zero, verificaUltimoAlgarismo  
		
	jal contador
	
	beq $s0, $s2, soma1  
	
	paraCalcular:
		lw $ra, ($sp)  
		lw $s0, 4($sp) 
		lw $s1, 8($sp) 
		lw $s2, 12($sp) 
		addu $sp, $sp, 16  
		jr $ra  

verificaUltimoAlgarismo: 
	beq $a0, $a2 soma1 
	j paraCalcular
		
soma1:  
	lw $t1, incrementa
	addi $t1, $t1, 1
	sw $t1, incrementa
	j paraCalcular
	
end:
	li, $v0, 10
	syscall
