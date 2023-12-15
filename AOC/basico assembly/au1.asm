.data #lida com os dados da memoria principal
	msg: .asciiz "Hello World!"


.text # local das instruções do programa
	li $v0, 4 # comando para imprimir string/char
	la $a0, msg # indica o endereço onde está a mensagem
	syscall #faça/imprima