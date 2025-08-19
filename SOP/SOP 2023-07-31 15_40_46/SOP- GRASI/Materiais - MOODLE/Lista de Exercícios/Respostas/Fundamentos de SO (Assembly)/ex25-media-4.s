# sub-rotina que calcula a media de 4 numeros
#
#  (c) rro, 2019-03-02

.text
	.global main

main:
	# invoca media4(10, 20, 40, 50)
	push	$50
	push	$40
	push	$20
	push	$10
	call	media4
	add	$16, %esp

	# printf(pr, %eax)
	push	%eax
	push	$pr
	call	printf
	add	$8, %esp
	
	# return 0
	mov	$0, %eax
	ret
	
media4:
	push	%ebp		# prologo da funcao
	mov	%esp, %ebp
	push	%ebx		# salva EBX na pilha

	lea	8(%ebp), %ebx	# EBX=endereco do 1o parametro
	mov	$4, %ecx	# ECX=nro de parametros (4)
	mov	$0, %eax	# EAX=soma (e depois a media)
	
soma:	
	add	(%ebx), %eax	# soma parametro
	add	$4, %ebx	# pega proximo parametro
	loop	soma		# repete ate' (--ECX)==0
	
	shr	$2, %eax	# divide soma por por 4
fim:	
	pop	%ebx		# restaura EBX
	mov	%ebp, %esp	# epilogo da funcao
	pop	%ebp
	ret			# retorna
	
.section .data
pr:		.string	"A media e' %d\n"
prteste:	.string "Hello, world!"
