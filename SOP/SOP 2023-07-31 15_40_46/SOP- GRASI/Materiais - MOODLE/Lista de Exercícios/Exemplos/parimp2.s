#  parimp em Assembly x86-32
#  versao usando printf() e scanf()
#
#  (c) rro, 2019-02-28
#
#  para montar o programa:
#    $ gcc -m32 -o parimp2 -g parimp2.s
#
#  para executar o programa:
#    $ ./parimp2
#
#  para depurar o programa:
#    $ ddd ./parimp2

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
	mov	$0, %edx	# dividendo=EDX:EAX - EDX=0, EAX=num
        mov     $2, %ecx        # divisor em ECX
        idiv    %ecx            # quociente em EAX, resto em EDX
        cmp     $0, %edx	# compara DX com 0
        je      imp_par         # se for igual, e' par

imp_impar:
	push	$str_impar	# 2o argumento de printf() e' "impar"
        jmp     imprime
	
imp_par: 
	push    $str_par	# 2o argumento de printf() e' "par"
	
imprime:
	push	$pr2		# 1o argumento de printf() e' pr2
	call	printf
	add     $8, %esp	# limpa pilha

encerra:
	mov	$0, %eax	
	ret			# encerra o programa
fim:
	
.section .data
pr1:		.string	"\nEntre um numero: "
str_impar:	.string	"impar"
str_par:	.string	"par"
pr2:		.string	"O numero eh %s\n"
sc:		.string "%d"
num:		.long 0
