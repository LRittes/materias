# Le dois numeros n1 e n2 e calcula a soma e a diferenca (em modulo)
# desses numeros.
#
#  (c) rro, 2019-03-02
#
#  para montar o programa:
#    $ gcc -m32 -o ex20-dif-2num -g ex20-dif-2num.s
#
#  para executar o programa:
#    $ ./ex20-dif-2num
#
#  para depurar o programa:
#    $ ddd ./ex20-dif-2num

.text
	.global main

main:
	# printf(pr1, 1)
	push	$1		
	push	$pr1		# endereco da string
	call	printf		# invoca printf()
	add     $8, %esp	# limpa pilha

	# scanf(sc, &n1)
	push    $n1		# endereco de n1
	push    $sc             # endereco da string
	call	scanf		# le string
	add     $8, %esp        # limpa pilha

	# printf(pr1, 2)
	push	$2		
	push	$pr1		# endereco da string
	call	printf		# invoca printf()
	add     $8, %esp	# limpa pilha

	# scanf(sc, &n2)
	push    $n2		# endereco de n2
	push    $sc             # endereco da string
	call	scanf		# le string
	add     $8, %esp        # limpa pilha

	# calcula e imprime a soma
	mov	n1, %eax	# armazena n1 em EAX
	mov	n2, %ebx	# armazena n2 em EBX
	add     %ebx, %eax	# EAX <- n1+n2

	# printf(pr2, n1, n2, %eax)
	push	%eax		# EAX e' a media
	push	n2
	push	n1		# 2o argumento de printf()
	push	$pr2		# 1o argumento de printf() e' pr2
	call	printf
	add     $16, %esp	# limpa pilha

	# calcula e imprime a diference
	mov	n1, %eax	# armazena n1 em EAX
	sub     %ebx, %eax	# EAX <- n1+n2
	cmp	$0, %eax	# se diferenca >= 0, mostra resultado
	jge	imprime
	neg	%eax		# se diferenca < 0, inverte sinal
	
imprime:	
	# printf(pr2, n1, n2, %eax)
	push	%eax		# EAX e' a diferenca
	push	n2
	push	n1		# 2o argumento de printf()
	push	$pr3		# 1o argumento de printf() e' pr3
	call	printf
	add     $16, %esp	# limpa pilha

encerra:
	mov	$0, %eax	
	ret			# encerra o programa
fim:
	
.section .data
pr1:		.string	"Entre o %do numero: "
pr2:		.string	"%d + %d = %d\n"
pr3:		.string	"|%d - %d| = %d\n"
sc:		.string "%d"
n1:		.long 0
n2:		.long 0
