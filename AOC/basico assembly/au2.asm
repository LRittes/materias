.data
	caractere: .byte 'A' #caracter a ser imprimido

.text
	li $v0, 4
	la $a0, caractere
	syscall