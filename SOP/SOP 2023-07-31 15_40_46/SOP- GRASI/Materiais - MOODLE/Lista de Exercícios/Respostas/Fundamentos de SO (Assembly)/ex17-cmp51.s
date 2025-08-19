#  Le um numero e imprime se ele ' maior que, menor que, ou igual a 51.
#
#  (c) rro, 2019-03-02
#
#  para montar o programa:
#    $ gcc -m32 -o ex17-cmp51 -g ex17-cmp51.s
#
#  para executar o programa:
#    $ ./ex17-cmp51
#
#  para depurar o programa:
#    $ ddd ./ex17-cmp51

.text
	.global main

main:
	push	$pr1		# endereco da string
	call	printf		# invoca printf()
	add     $4, %esp	# limpa pilha

	push    $num		# endereco de num
	push    $sc             # endereco da string
	call	scanf		# le string
	add     $8, %esp        # limpa pilha

	mov	num, %eax	# armazena o numero lido em EAX
        cmp     $51, %eax	# compara EAX com 0
	jg	maior		# se num > 51, desvia para maior
	jl	menor		# se num < 51, desvia para menor
	push	$str_igual	# senao, num == 51
	jmp	imprime
	
maior:
	push	$str_maior	
        jmp     imprime
	
menor: 
	push    $str_menor	
	
imprime:
	push	$pr2		# 1o argumento de printf() e' pr2
	call	printf
	add     $8, %esp	# limpa pilha

encerra:
	mov	$0, %eax	
	ret			# encerra o programa
fim:
	
.section .data
pr1:		.string	"Entre um numero: "
str_maior:	.string	"maior que"
str_menor:	.string	"menor que"
str_igual:	.string "igual a"
pr2:		.string	"O numero eh %s 51\n"
sc:		.string "%d"
num:		.long 0
