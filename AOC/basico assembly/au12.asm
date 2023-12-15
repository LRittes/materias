.data 
	msg: .asciiz "Forneça um numero float: "
	zeroF: .float 0.0

.text
	li $v0, 4
	la $a0, msg
	syscall
	
	# ler valor float
	li $v0, 6
	syscall # valor float lido estará em f0
	
	lwc1 $f1, zeroF # f1 = zeroF
	add.s $f12, $f0, $f1 # f12 = f0 + f1
	
	li $v0, 2
	syscall