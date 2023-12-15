#int sum(int x) {
 #   if (x == 0)
  #      return 0;
   # return x + sum(x-1);
#}

#int main() {
 #   int a;
  #  scanf("%i", &a);
   # a = sum(a);
    #printf("%i\n", a);
   # return 0;
#}

.text
	li $v0, 5
	syscall
	
	move $s0, $v0
	
	jal sum	
	
	move $a0, $v0
	
	li $v0, 1
	syscall
	
	j end
	
	sum:	
		add $a0, $a0, $s0
		addi $s0, $s0, -1 
		bnez $s0, sum
		
		move $v0, $a0
		
		jr $ra
	
	end:
		li $v0, 10
		syscall