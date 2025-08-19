# subrotina para imprimir 3 numeros passados na pilha
#   void print3(int x, int y, int z) {
#      printf("x=%d, y=%d, z=%d\n", x, y, z);
#   }
#
# parametros estao em 8(%ebp), 12(%ebp), 16(%ebp)
#
#  (c) rro, 2019-03-02

.text
	.global main

main:
	# invoca print3(10, 20, 30)
	push	$30
	push	$20
	push	$10
	call	print3
	add	$12, %esp
	
	# return 0
	mov	$0, %eax
	ret
	
print3:
	push	%ebp		# prologo da funcao
	mov	%esp, %ebp
	push	16(%ebp)	# 16(%ebp)=z
	push	12(%ebp)	# 12(%ebp)=y
	push	8(%ebp)		#  8(%ebp)=x
	push	$pr
	call	printf

	mov	%ebp, %esp	# epilogo da funcao
	pop	%ebp
	ret			# retorna
	
.section .data
pr:		.string	"x=%d, y=%d, z=%d\n"
