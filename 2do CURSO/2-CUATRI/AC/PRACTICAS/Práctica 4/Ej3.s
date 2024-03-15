	.data
	.align 2
	
a:	.word 5,4,3,2,1
b:	.word 1,2,3,4,5
c:	.space 20
cont:	.word 20

.text
.global main
	
main:

	add r1,r0,r0
	lw r6, cont
		
bucle:
	lw r2, a(r1)
	lw r3, b(r1)
	sub r4,r2,r3
	sw c(r1), r4
	
	addi r1,r1,#4
	subi r6,r6,#4
	bnez r6, bucle
		
fin:	trap 0