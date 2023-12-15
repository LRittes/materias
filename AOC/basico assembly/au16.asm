.data
	msg: .asciiz "Digite um valor: "
	ePar: .asciiz "O número é par"
	eImpar: .asciiz "O número é impar"

.text
	la $a0, msg
	jal imprimeString
	
	jal leInteiro
	
	move $a0, $v0
	
	jal ehPar
	beq $v0, $zero, imprimePar
	
	la $a0, eImpar
	jal imprimeString
	
	j finalizaCod
	
	# função que verifica se o argumento $a0 é par
	# retorna 1  se for par
	# retorna 0 se impar
	ehPar:
		li $t0, 2
		div $a0, $t0
		
		mfhi $v0
		
		jr $ra
	
	# função que imprime String
	imprimeString:
		li $v0, 4
		syscall
		jr $ra
	
	imprimePar:
		la $a0, ePar
		jal imprimeString
		j finalizaCod
	
	
	leInteiro:
		li $v0, 5
		syscall
		jr $ra
	
	finalizaCod:
		li $v0, 10
		syscall