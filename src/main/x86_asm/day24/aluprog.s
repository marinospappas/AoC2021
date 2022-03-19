.text

.global alu_start

alu_start:
	xorq %r8,%r8
	xorq %r9,%r9
	xorq %r10,%r10
	xorq %r11,%r11

aluinput_01:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $12,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_000
	xorq %rax,%rax
alulbl_000:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_001
	xorq %rax,%rax
alulbl_001:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $15,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_02:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $14,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_002
	xorq %rax,%rax
alulbl_002:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_003
	xorq %rax,%rax
alulbl_003:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $12,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_03:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $11,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_004
	xorq %rax,%rax
alulbl_004:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_005
	xorq %rax,%rax
alulbl_005:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $15,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_04:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $-9,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_006
	xorq %rax,%rax
alulbl_006:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_007
	xorq %rax,%rax
alulbl_007:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $12,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_05:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $-7,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_008
	xorq %rax,%rax
alulbl_008:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_009
	xorq %rax,%rax
alulbl_009:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $15,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_06:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $11,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_010
	xorq %rax,%rax
alulbl_010:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_011
	xorq %rax,%rax
alulbl_011:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $2,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_07:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $-1,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_012
	xorq %rax,%rax
alulbl_012:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_013
	xorq %rax,%rax
alulbl_013:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $11,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_08:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $-16,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_014
	xorq %rax,%rax
alulbl_014:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_015
	xorq %rax,%rax
alulbl_015:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $15,%r10
	imulq %r9,%r10
	addq %r10,%r11

	movq %r11,%rax
	ret		# end of first part of program (z must be 0 here)

.global aluinput_09		# start of second part of program

aluinput_09:
	xorq %r8,%r8
	xorq %r9,%r9
	xorq %r10,%r10
	xorq %r11,%r11

	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $11,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_016
	xorq %rax,%rax
alulbl_016:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_017
	xorq %rax,%rax
alulbl_017:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $10,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_10:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $-15,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_018
	xorq %rax,%rax
alulbl_018:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_019
	xorq %rax,%rax
alulbl_019:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $2,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_11:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $10,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_020
	xorq %rax,%rax
alulbl_020:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_021
	xorq %rax,%rax
alulbl_021:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $0,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_12:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	addq $12,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_022
	xorq %rax,%rax
alulbl_022:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_023
	xorq %rax,%rax
alulbl_023:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $0,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_13:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $-4,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_024
	xorq %rax,%rax
alulbl_024:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_025
	xorq %rax,%rax
alulbl_025:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $15,%r10
	imulq %r9,%r10
	addq %r10,%r11

aluinput_14:
	xorq %rax,%rax
	movb (%rdi),%al
	movq %rax,%r8
	inc %rdi
	xorq %r9,%r9
	addq %r11,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r9,%rax
	idivq %rbx
	movq %rdx,%r9
	movq $26,%rbx
	xorq %rdx,%rdx
	movq %r11,%rax
	idivq %rbx
	movq %rax,%r11
	addq $0,%r9
	movq $1,%rax
	cmpq %r8,%r9
	je alulbl_026
	xorq %rax,%rax
alulbl_026:
	movq %rax,%r9
	movq $1,%rax
	cmpq $0,%r9
	je alulbl_027
	xorq %rax,%rax
alulbl_027:
	movq %rax,%r9
	xorq %r10,%r10
	addq $25,%r10
	imulq %r9,%r10
	addq $1,%r10
	imulq %r10,%r11
	xorq %r10,%r10
	addq %r8,%r10
	addq $15,%r10
	imulq %r9,%r10
	addq %r10,%r11

	movq %r11,%rax
	ret

