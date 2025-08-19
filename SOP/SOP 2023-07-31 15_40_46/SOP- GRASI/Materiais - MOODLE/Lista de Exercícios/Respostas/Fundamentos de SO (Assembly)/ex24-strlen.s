# sub-rotina que implementa strlen(char *s)
#
#  (c) rro, 2019-03-02

.text
	.global main

main:
	# invoca strlen(prteste)
	push	$prteste
	call	strlen
	add	$4, %esp

	# printf(pr, prteste, %eax)
	push	%eax
	push	$prteste
	push	$pr
	call	printf
	add	$12, %esp
	
	# return 0
	mov	$0, %eax
	ret
	
strlen:
	push	%ebp		# prologo da funcao
	mov	%esp, %ebp
	push	%ebx		# salva EBX na pilha
	
	mov	8(%ebp), %ebx	# EBX=ponteiro para string
	mov	$0, %eax	# EAX=tamanho da string
	
teste:	
	cmpb	$0, (%ebx)	# *EBX==0?
	je	fim
	inc	%eax		# caso contrario, incrementa EAX e EBX
	inc	%ebx
	jmp	teste
	
fim:	
	pop	%ebx		# restaura EBX
	mov	%ebp, %esp	# epilogo da funcao
	pop	%ebp
	ret			# retorna
	
.section .data
pr:		.string	"\"%s\" tem %d caracteres\n"
prteste:	.string "Hello, world!"
