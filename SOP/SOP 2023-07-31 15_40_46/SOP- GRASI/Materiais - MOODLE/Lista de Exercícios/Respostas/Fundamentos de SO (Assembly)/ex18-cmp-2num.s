#  Le dois numeros n1 e n2 e imprime se n1 e' maior que, menor que, ou
#  igual a n2.
#
#  (c) rro, 2019-03-02
#
#  para montar o programa:
#    $ gcc -m32 -o ex18-cmp-2num -g ex18-cmp-2num.s
#
#  para executar o programa:
#    $ ./ex18-cmp-2num
#
#  para depurar o programa:
#    $ ddd ./ex18-cmp-2num

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

	push	n2		# 4o argumento do printf()
	mov	n1, %eax	# armazena n1 em EAX
	mov	n2, %ebx	# armazena n2 em EBX
        cmp     %ebx, %eax	# compara EAX (n1) com EBX (n2)
	jg	maior		# se n1 > n2, desvia para maior
	jl	menor		# se n1 < n2, desvia para menor
	push	$str_igual	# senao, n1 == n2
	jmp	imprime
	
maior:
	push	$str_maior	
        jmp     imprime
	
menor: 
	push    $str_menor	
	
imprime:
	push	n1		# 2o argumento de printf()
	push	$pr2		# 1o argumento de printf() e' pr2
	call	printf
	add     $16, %esp	# limpa pilha

encerra:
	mov	$0, %eax	
	ret			# encerra o programa
fim:
	
.section .data
pr1:		.string	"Entre o %do numero: "
str_maior:	.string	"maior que"
str_menor:	.string	"menor que"
str_igual:	.string "igual a"
pr2:		.string	"%d eh %s %d\n"
sc:		.string "%d"
n1:		.long 0
n2:		.long 0
