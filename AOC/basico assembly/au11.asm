.data
	PI: .float 3.141592
	
.text
	li $v0, 2 # imprimir numero float ( 3 para double ), será imprimido sempre oq estiver no registrador f12
	lwc1 $f12, PI # lwcl = load word coprocessor 1, carregar da memória para numeros float e double
	syscall