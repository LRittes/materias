.data 
	msg: .asciiz "Forneça um numero double: "
	zeroD: .double 0.0

.text
	li $v0, 4
	la $a0, msg
	syscall
	
	# ler valor double
	li $v0, 7
	syscall # valor float lido estará em f0
	
	ldc1 $f2, zeroD # lsc1 = load double coprocessor 1,  f1 = zeroF 
	add.d $f12, $f0, $f2 # f12 = f0 + f2
	
	li $v0, 3
	syscall
