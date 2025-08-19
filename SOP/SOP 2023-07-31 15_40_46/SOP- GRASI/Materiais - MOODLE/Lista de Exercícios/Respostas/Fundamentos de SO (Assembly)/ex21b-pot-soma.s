# Le dois numeros x e y e calcula x^y usando apenas somas.
#
#  (c) rro, 2019-03-02
#
#  para montar o programa:
#    $ gcc -m32 -o ex21b-pot-soma -g ex21b-pot-soma.s
#
#  para executar o programa:
#    $ ./ex21b-pot-soma
#
#  para depurar o programa:
#    $ ddd ./ex21b-pot-soma

.text
	.global main

main:
	# printf(pr1, 1)
	push	$1		
	push	$pr1		# endereco da string
	call	printf		# invoca printf()
	add     $8, %esp	# limpa pilha

	# scanf(sc, &x)
	push    $x		# endereco de x
	push    $sc             # endereco da string
	call	scanf		# le string
	add     $8, %esp        # limpa pilha

	# printf(pr1, 2)
	push	$2		
	push	$pr1		# endereco da string
	call	printf		# invoca printf()
	add     $8, %esp	# limpa pilha

	# scanf(sc, &y)
	push    $y		# endereco de y
	push    $sc             # endereco da string
	call	scanf		# le string
	add     $8, %esp        # limpa pilha

	# inicializacao do calculo
	mov	$1, %edi	# EDI=produto
	mov	x, %ebx		# EBX=base
	mov	y, %ecx		# ECX=numero de multiplicacoes restantes
	cmp	$0, %ecx	# se expoente=0, nao precisa multiplicar
	je	imprime

	mov	%ebx, %edi	# EDI=produto
	cmp	$1, %ecx	# x^1 == x
	je	imprime
	dec	%ecx		# loop e' de y-1 a 1
	
multiplica:
	mov	%ebx, %edx	# DX=contador do loop de soma
	mov	$0, %eax	# AX=acumulador da soma
	mov	%edi, %esi	# ESI=parcela a somar

loop_soma:
	add	%esi, %eax
	dec	%edx
	jnz	loop_soma

fim_soma:
	mov	%eax, %edi
	loop	multiplica	# if (--CX == 0) goto multiplica
	
imprime:	
	# printf(pr2, x, y, %edi)
	push	%edi		# EDI e' o resultado
	push	y
	push	x		# 2o argumento de printf()
	push	$pr2		# 1o argumento de printf() e' pr2
	call	printf
	add     $16, %esp	# limpa pilha

encerra:
	mov	$0, %eax	
	ret			# encerra o programa
fim:
	
.section .data
pr1:		.string	"Entre o %do numero: "
pr2:		.string	"%d^%d = %d\n"
sc:		.string "%d"
x:		.long 0
y:		.long 0

