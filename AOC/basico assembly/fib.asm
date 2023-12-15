.text

main:
    li $s1, 1       # $s1 é auxiliar
    
    li $v0, 5       
    syscall         # $v0 recebe n (é contador para chegar no e-nésimo número de fibonacci)

    jal	fib	# jump to target and save position to $ra
    
    li $v0, 1
    syscall
    
    li $v0, 10
    syscall

fib:
    add $a0, $s0, $s1 
    add $s0, $zero, $s1
    add $s1, $zero, $a0
    addi $v0, $v0, -1
    bgt	$v0, $zero, fib	# if $t0 > $t1 then target
    jr $ra
    