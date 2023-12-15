.text
.globl main

main:    
    li $v0, 5
    syscall
    
    move $a0, $v0
    jal fib

    move $a0, $v1    
    li $v0, 1 		# imprimir int
    syscall

end:
    ori $v0, $zero, 10
    syscall
    
fib:
    beq $a0, 0, fib_parada1
    beq $a0, 1, fib_parada1
    beq $a0, 2, fib_parada2

    # salvar contexto
    addi $sp, $sp, -12
    sw $s0, 0($sp)
    sw $s1, 4($sp)
    sw $ra, 8($sp)
    
    # codigo principal com recursão
    move $s0, $a0
    add $a0, $a0, -1
    jal fib
    move $s1, $v1
    li $v1, 0
    
    add $s0, $s0, -2
    add $a0, $s0, 0
    jal fib
    move $s0, $v1
    add $v1, $s1, $s0
    
    # restaurar contexto
    lw $s0, 0($sp)
    lw $s1, 4($sp)
    lw $ra, 8($sp)
    addi $sp, $sp, 12
    
    jr $ra
    
fib_parada1:
    ori $v1, 1
    jr $ra
    
fib_parada2:
    ori $v1, 2
    jr $ra