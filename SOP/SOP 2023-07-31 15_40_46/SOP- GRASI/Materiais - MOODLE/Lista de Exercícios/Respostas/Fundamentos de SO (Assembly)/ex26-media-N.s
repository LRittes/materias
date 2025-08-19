# sub-rotina que calcula a media de N numeros (N e' o primeiro argumento)
#
#  (c) rro, 2019-03-02

.text
	.global main

main:
	# invoca media(5, 10, 20, 30, 40, 50)
	push	$50
	push	$40
	push	$30
	push	$20
	push	$10
        push	$5
	call	media
	add	$24, %esp

	# printf(pr, %eax)
	push	%eax
	push	$pr
	call	printf
	add	$8, %esp
	
	# return 0
	mov	$0, %eax
	ret
	
media:
	push	%ebp		# prologo da funcao
	mov	%esp, %ebp
	push	%ebx		# salva EBX na pilha

	lea	12(%ebp), %ebx	# EBX=endereco do 1o numero
	mov	8(%ebp), %ecx	# ECX=quantidade de numeros
	mov	$0, %eax	# EAX=soma (e depois a media)
	
soma:	
	add	(%ebx), %eax	# soma numero
	add	$4, %ebx	# pega proximo numero
	loop	soma		# repete ate' (--ECX)==0

	mov 	$0, %edx	# EDX=parte alta do dividendo
	mov	8(%ebp), %ecx	# ECX=N
	idiv	%ecx		# divide soma por N	
fim:	
	pop	%ebx		# restaura EBX
	mov	%ebp, %esp	# epilogo da funcao
	pop	%ebp
	ret			# retorna
	
.section .data
pr:		.string	"A media e' %d\n"
