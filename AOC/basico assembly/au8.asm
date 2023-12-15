.text
	li $t0, 32
	li $t1, 5
	
	srl $s2, $t0, 2 # faz shift right de t0 n casas a direita
			# 2^2 = 4 
			# 32/4 = 8 -> 32 = 100000 -> 8 = 001000
	
	div $t0, $t1
	
	#parte inteira da mult e alocar em s0
	mflo $s0 # mflo = move from lo

	#resto da mult e alocar em s1
	mfhi $s1 # mfhi = move from hi	