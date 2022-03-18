package mpdev.aoc2021.day24

import org.junit.jupiter.api.*
import kotlin.system.measureNanoTime
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 22 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay24 {

    @Test
    @Order(1)
    fun `Test Sub 1,2,3`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i1 in 1..9)
            for (i2 in 1..9)
                for (i3 in 1..9) {
                    prog.inp = listOf(-1, i1,i2,i3)
                    val z2 = prog.sub2(prog.sub1())
                    val z3 = prog.sub3(z2)
                    alu.inputValues = "$i1$i2${i3}45678912345"
                    val z_ = alu.runProgram(0, 53)
                    assertEquals(z3, z_)
                }
    }

    @Test
    @Order(2)
    fun `Test Sub 4`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        val goodI3I4 = mutableListOf<List<Int>>()
        for (i1_2 in 11..99)
            for (i3 in 1..9)
                for (i4 in 1..9) {
                    prog.inp = listOf(-1, i1_2/10,i1_2%10,i3,i4)
                    val z2 = prog.sub2(prog.sub1())
                    val z3 = prog.sub3(z2)
                    val z4 = prog.sub4(z3)
                    alu.inputValues = "$i1_2$i3${i4}5678912345"
                    val z_ = alu.runProgram(0, 71)
                    assertEquals(z4, z_)
                    if (prog.inp[4] == prog.inp[3]+6) {
                        assertEquals(z2, z4)
                        goodI3I4.add(listOf(i3,i4))
                    }
                    else
                        assertEquals(true, z4 > z2*26)
                }
        println("input 3,4 good pairs: ${goodI3I4.distinct()}")
    }

    val goodInput = mutableListOf<List<Int>>()

    @Test
    @Order(3)
    fun `Test Sub 5`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i1_2 in 99 downTo 11) {
            if (i1_2 % 10 == 0) continue
            for (i3_4 in listOf(39, 28, 17))
                for (i5 in 9 downTo 1) {
                    prog.inp = listOf(-1, i1_2 / 10, i1_2 % 10, i3_4 / 10, i3_4 % 10, i5)
                    val z1 = prog.sub1()
                    val z2 = prog.sub2(z1)
                    val z3 = prog.sub3(z2)
                    val z4 = prog.sub4(z3)
                    val z5 = prog.sub5(z4)
                    alu.inputValues = "$i1_2$i3_4${i5}678912345"
                    val z_ = alu.runProgram(0, 89)
                    assertEquals(z5, z_)
                    if (prog.inp[5] == z4 % 26 - 7) {
                        assertEquals(z1, z5)
                        goodInput.add(prog.inp.subList(1, 6))
                    } else
                        assertEquals(true, z5 >= z1 * 26)
                }
        }
        println("input good values (${goodInput.size} combinations): ${goodInput.distinct()}")
    }

    @Test
    @Order(4)
    fun `Test Sub 6`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i1_5 in goodInput)
            for (i6 in 1..9) {
                val x = mutableListOf(-1)
                x.addAll(i1_5)
                x.add(i6)
                prog.inp = x
                val z1 = prog.sub1()
                val z2 = prog.sub2(z1)
                val z3 = prog.sub3(z2)
                val z4 = prog.sub4(z3)
                val z5 = prog.sub5(z4)
                val z6 = prog.sub6(z5)
                alu.inputValues = "$i1_5${i6}78912345"
                val z_ = alu.runProgram(0, 107)
                assertEquals(z2, z4, "z2=z4")
                assertEquals(z1, z5, "z1=z5")
                assertEquals(z6, z_)
                val z6_ = prog.sub6(z1)
                assertEquals(z6, z6_, "$i1_5$i6")
            }
    }

    @Test
    @Order(5)
    fun `Test Sub 7`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i6 in 1..9)
            for (i7 in 1..9) {
                prog.inp = listOf(-1, 9, 4, 3, 9, 9, i6, i7)
                val z1 = prog.sub1()
                val z2 = prog.sub2(z1)
                val z3 = prog.sub3(z2)
                val z4 = prog.sub4(z3)
                val z5 = prog.sub5(z4)
                val z6 = prog.sub6(z5)
                val z7 = prog.sub7(z6)
                alu.inputValues = "94399${i6}${i7}8912345"
                val z_ = alu.runProgram(0, 125)
                assertEquals(z7, z_)
                if (i7 == z6 % 26 - 1) {
                    assertEquals(z5, z7, "z5=z7")
                    assertEquals(z1, z7, "z1=z7")
                    goodInput.add(prog.inp.subList(1, 8))
                } else
                    assertEquals(true, z7 >= z5 * 26, "failed for $i6,$i7")
            }
        println("input good values (${goodInput.size} combinations): ${goodInput.distinct()}")
    }

    @Test
    @Order(6)
    fun `Test Sub 8`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i6_7 in listOf(89,78,67,56,45,34,23,12))
            for (i8 in 9 downTo 1) {
                prog.inp = listOf(-1, 9, 4, 3, 9, 9, i6_7/10, i6_7%10, i8)
                val z1 = prog.sub1()
                val z2 = prog.sub2(z1)
                val z3 = prog.sub3(z2)
                val z4 = prog.sub4(z3)
                val z5 = prog.sub5(z4)
                val z6 = prog.sub6(z5)
                val z7 = prog.sub7(z6)
                val z8 = prog.sub8(z7)
                alu.inputValues = "94399${i6_7/10}${i6_7%10}${i8}912345"
                val z_ = alu.runProgram(0, 143)
                assertEquals(z8, z_)
                //println("z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 ")
                if (i8 == z7 % 26 - 16) {
                    assertEquals(z5, z7, "z5=z7")
                    assertEquals(z1, z7, "z1=z7")
                    assertEquals(0, z8, "z8=0")
                    goodInput.add(prog.inp.subList(1, 9))
                } else
                    assertEquals(true, z8 > 0, "failed for $i6_7,$i8")
            }
        println("input good values (${goodInput.size} combinations): ${goodInput.distinct()}")
    }

    @Test
    @Order(7)
    fun `Test Sub 9,10`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i6_7 in listOf(89,78,67,56,45,34,23,12))
            for (i9 in 9 downTo 1)
                for (i10 in 9 downTo 1) {
                    prog.inp = listOf(-1, 9, 4, 3, 9, 9, i6_7/10, i6_7%10, 8, i9, i10)
                    val z1 = prog.sub1()
                    val z2 = prog.sub2(z1)
                    val z3 = prog.sub3(z2)
                    val z4 = prog.sub4(z3)
                    val z5 = prog.sub5(z4)
                    val z6 = prog.sub6(z5)
                    val z7 = prog.sub7(z6)
                    val z8 = prog.sub8(z7)
                    val z9 = prog.sub9(z8)
                    val z10 = prog.sub10(z9)
                    alu.inputValues = "94399${i6_7/10}${i6_7%10}8${i9}${i10}2345"
                    val z_ = alu.runProgram(0, 179)
                    assertEquals(z10, z_)
                    //println("i9,i10: $i9,$i10 -  z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 z9 $z9 z10 $z10")
                    if (i10 == i9 - 5) {
                        assertEquals(z5, z7, "z5=z7")
                        assertEquals(z1, z7, "z1=z7")
                        assertEquals(0, z8, "z8=0")
                        assertEquals(0, z10, "z10=0")
                        goodInput.add(prog.inp.subList(1, 11))
                    } else
                    assertEquals(true, z10 > 0, "failed for $i9,$i10")
                }
        println("input good values (${goodInput.size} combinations): ${goodInput.distinct()}")
    }

    @Test
    @Order(8)
    fun `Test Sub 11,12`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i6_7 in listOf(89,78,67,56,45,34,23,12))
            for (i9_10 in listOf(94,83,72,61))
                for (i11 in 9 downTo 1)
                    for (i12 in 9 downTo 1) {
                        prog.inp = listOf(-1, 9, 4, 3, 9, 9, i6_7 / 10, i6_7 % 10, 8, i9_10 / 10, i9_10 % 10, i11, i12)
                        val z1 = prog.sub1()
                        val z2 = prog.sub2(z1)
                        val z3 = prog.sub3(z2)
                        val z4 = prog.sub4(z3)
                        val z5 = prog.sub5(z4)
                        val z6 = prog.sub6(z5)
                        val z7 = prog.sub7(z6)
                        val z8 = prog.sub8(z7)
                        val z9 = prog.sub9(z8)
                        val z10 = prog.sub10(z9)
                        val z11 = prog.sub11(z10)
                        val z12 = prog.sub12(z11)
                        alu.inputValues = "94399${i6_7}8${i9_10}${i11}${i12}45"
                        val z_ = alu.runProgram(0, 215)
                        //println("i11,i12: $i11$i12 -  z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 z9 $z9 z10 $z10 z11 $z11 z12 $z12")
                        assertEquals(z11, i11)
                        assertEquals(z12, z_)
                    }
    }


    @Test
    @Order(9)
    fun `Test Sub Part 2`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i1 in 1..9)
            for (i2 in 1..9)
                for (i5 in 1..9)
                    for (i8 in 1..9)
                        for (i11 in 1..9)
                for (i14 in 9 downTo 1) {
                    prog.inp = listOf(-1, i1, i2, 1, 7, i5, 1, 2, i8, 6, 1, i11, 5, 1, i14)
                    val z1 = prog.sub1()
                    val z2 = prog.sub2(z1)
                    val z3 = prog.sub3(z2)
                    val z4 = prog.sub4(z3)
                    val z5 = prog.sub5(z4)
                    val z6 = prog.sub6(z5)
                    val z7 = prog.sub7(z6)
                    val z8 = prog.sub8(z7)
                    val z9 = prog.sub9(z8)
                    val z10 = prog.sub10(z9)
                    val z11 = prog.sub11(z10)
                    val z12 = prog.sub12(z11)
                    val z13 = prog.sub13(z12)
                    val z14 = prog.sub14(z13)
                    alu.inputValues = "${i1}${i2}17${i5}12${i8}61${i11}51$i14"
                    val z_ = alu.runProgram()
                    println("z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 z9 $z9 z10 $z10 z11 $z11 z12 $z12 z13 $z13 z14 $z14")
                    assertEquals(z14, z_, "failed for input ${alu.inputValues}")
                    if (z14 ==0) {
                        goodInput.add(prog.inp.subList(1,15))
                        println("z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 z9 $z9 z10 $z10 z11 $z11 z12 $z12 z13 $z13 z14 $z14")
                    }
                }
        println("input good values (${goodInput.size} combinations): ${goodInput.distinct()}")
    }

    @Test
    @Order(9)
    fun `Test Sub 13,14`() {
        val prog = ALUProgram()
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        for (i6_7 in listOf(89,78,67,56,45,34,23,12))
            for (i9_10 in listOf(94,83,72,61))
                for (i11 in 9 downTo 1)
                    for (i12 in 9 downTo 1)
                        for (i13 in 9 downTo 1)
                            for (i14 in 9 downTo 1) {
                                prog.inp = listOf(-1, 9, 4, 3, 9, 9, i6_7 / 10, i6_7 % 10, 8, i9_10 / 10, i9_10 % 10, i11, i12, i13, i14)
                                val z1 = prog.sub1()
                                val z2 = prog.sub2(z1)
                                val z3 = prog.sub3(z2)
                                val z4 = prog.sub4(z3)
                                val z5 = prog.sub5(z4)
                                val z6 = prog.sub6(z5)
                                val z7 = prog.sub7(z6)
                                val z8 = prog.sub8(z7)
                                val z9 = prog.sub9(z8)
                                val z10 = prog.sub10(z9)
                                val z11 = prog.sub11(z10)
                                val z12 = prog.sub12(z11)
                                val z13 = prog.sub13(z12)
                                val z14 = prog.sub14(z13)
                                alu.inputValues = "94399${i6_7}8${i9_10}${i11}${i12}$i13$i14"
                                val z_ = alu.runProgram()
                                assertEquals(z14, z_, "failed for input ${alu.inputValues}")
                                if (z13 < z12 && z14 ==0) {
                                    goodInput.add(prog.inp.subList(1,15))
                                    println("z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 z9 $z9 z10 $z10 z11 $z11 z12 $z12 z13 $z13 z14 $z14")
                                }
                            }
        println("input good values (${goodInput.size} combinations): ${goodInput.distinct()}")
    }

    @Test
    @Order(9)
    fun `Perf Test Interpreter`() {
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUInterpreter(inpProg)
        var z = 0
        val numberOfTimes = 100000
        alu.inputValues = "94399898949959"
        val elapsed = measureNanoTime {
            for (counter in 1..numberOfTimes)
                z = alu.runProgram()
        }
        println("output (z) = $z")
        println("interpreter execution time = ${elapsed/numberOfTimes/1000} micro-sec")
    }

    @Test
    @Order(9)
    fun `Perf Test Compiler`() {
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        val alu = ALUCompiler(inpProg)
        alu.compileProgram()
        var z = 0
        val numberOfTimes = 100000
        alu.inputValues = "94399898949959"
        val elapsed = measureNanoTime {
            for (counter in 1..numberOfTimes)
                z = alu.runProgram()
        }
        println("output (z) = $z")
        println("compiler execution time = ${elapsed/numberOfTimes/1000} micro-sec")
    }

    @Test
    @Order(9)
    fun `Test Result Part1`() {
        val inpProg = getInput(arrayOf("src/test/resources/day24/input2.txt"))
        println("result part1: ${calculateResultPart1(inpProg)}")
    }
}



