.data
	quebra_linha: .asciiz "\n"
	incrementa: .word 0  
	
.text
.globl main

main:
	sw $zero, incrementa

	li $v0, 5  #  $v0 = 5
	syscall  #  leitura de um numero inteiro (n)
	move $a1, $v0  #  $a1 = n
	
	beq $a1, $zero, end  #  encerra programa caso n = 0
	
	li $v0, 5  #  $v0 = 5
	syscall  #  leitura de um numero inteiro (k)
	move $a2, $v0  #  $a2 = k
	
	jal contador  #  chamada para a função contador
	
	lw $t1, incrementa
	move $a0, $t1
	li $v0, 1
	
	syscall
	la $a0, quebra_linha
	li $v0, 4
	syscall
	
	j main

contador:
	#  Pilha
	subu $sp, $sp, 16  #  desloca ponteiro da pilha em 12 bytes para armazenar endereço de retorno e argumentos
	sw $ra, ($sp)  #  armazena endereço de retorno na pilha
	sw $a0, 4($sp)   #  armazena resto da divisão
	sw $a1, 8($sp)  #  armazena divisão inteira
	sw $a2, 12($sp)  #  armazena argumento k na pilha
	
	li $t0, 10  #  auxiliar para dividir numero por 10
	div $a1, $t0  #  $a1 / %t0
	mfhi $a0  #  $a0 = resto da divisão
	mflo $a1  #  $a1 = divisão inteira
			
	#  caso base
	li $v0, 1  # v0 = 1
	beq  $a1, $zero, verificaUltimoAlgarismo  #  se $a1 (divisão inteira) = 0 então finaliza a recursão
		
	jal contador  #  recursão
	
	beq $s0, $s2, soma1  #  se resto da divisão = k vai para somaum
	
	paraCalcular:
		lw $ra, ($sp)  #  le da pilha o o ultimo endereço de retorno
		lw $s0, 4($sp)  #  le da pilha argumento
		lw $s1, 8($sp)  #  le da pilha argumento
		lw $s2, 12($sp)  #  le da pilha argumento
		addu $sp, $sp, 16  #  reajusta ponteiro da pilha
		jr $ra  #  retorna  para a função chamadora

verificaUltimoAlgarismo:  #  função para verificar se o resto da divisão da ultima divisão por 10 de n
	beq $a0, $a2 soma1  #  somaum caso $a0 = k
	j paraCalcular
		
soma1:  #  função para incrementar quantos digitos k tem em n
	lw $t1, incrementa
	addi $t1, $t1, 1
	sw $t1, incrementa
	j paraCalcular
	
end:
	li, $v0, 10
	syscall
