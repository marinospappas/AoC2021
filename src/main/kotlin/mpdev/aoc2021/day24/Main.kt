package mpdev.aoc2021.day24

import kotlin.system.measureTimeMillis

lateinit var input: List<String>
var part1_2 = 1
var result = -1L

fun calculateResultPart1(input: List<String>): Long {
    val alu = ALUCompiler(input)
    alu.compileProgram()
    val res1 = calculateInp_1_8_max(alu)
    val res2 = calculateInp_9_14_max(alu, res1)
    return res2.toLong()
}

fun calculateInp_1_8_max(alu: ALU): String {
    mainloop@for (i1 in 9 downTo 1)
        for (i2 in 9 downTo 1)
            for (i3_4 in listOf(39, 28, 17))
                for (i5 in 1..9)
                    for (i6_7 in listOf(89,78,67,56,45,34,23,12))
                        for (i8 in 1..9) {
                            alu.inputValues = "$i1$i2$i3_4$i5$i6_7$i8"
                            val z = Array(9) {0}
                            for (i in 1..8)
                                z[i] = alu.runProgram(startSub(i), endSub(i))
                            if (z[7] == z[5] && z[8] == 0) {
                                print("z1:${z[1]} z2:${z[2]} z3:${z[3]} z4:${z[4]} z5:${z[5]} z6:${z[6]} z7:${z[7]} z8:${z[8]} ")
                                break@mainloop
                            }
                        }
    return alu.inputValues
}

fun calculateInp_9_14_max(alu: ALU, prevResult: String): String {
    mainloop@for (i9_10 in listOf(94, 83, 72, 61))
        for (i11 in 9 downTo 1)
            for (i12_13 in listOf(95, 84, 73, 62, 51))
                for (i14 in 9 downTo 1) {
                    alu.inputValues = "$prevResult$i9_10$i11$i12_13$i14"
                    val z = Array(15) {0}
                    for (i in 9..14)
                        z[i] = alu.runProgram(startSub(i), endSub(i))
                    if (z[14] == 0) {
                        println("z9:${z[9]} z10:${z[10]} z11:${z[11]} z12:${z[12]} z13:${z[13]} z14:${z[14]}")
                        break@mainloop
                    }
                }
    return alu.inputValues
}

fun calculateResultPart2(input: List<String>): Long {
    val alu = ALUCompiler(input)
    alu.compileProgram()
    val res1 = calculateInp_1_8_min(alu)
    val res2 = calculateInp_9_14_min(alu, res1)
    return res2.toLong()
}

fun calculateInp_1_8_min(alu: ALU): String {
    mainloop@for (i1 in 1..9)
        for (i2 in 1..9)
            for (i3_4 in listOf(17, 28, 39))
                for (i5 in 1..9)
                    for (i6_7 in listOf(12,23,34,45,56,67,78,89))
                        for (i8 in 1..9) {
                            alu.inputValues = "$i1$i2$i3_4$i5$i6_7$i8"
                            val z = Array(9) {0}
                            for (i in 1..8)
                                z[i] = alu.runProgram(startSub(i), endSub(i))
                            //println("z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 ")
                            if (z[7] == z[5] && z[8] == 0)
                                break@mainloop
                        }
    return alu.inputValues
}

fun calculateInp_9_14_min(alu: ALU, prevResult: String): String {
    mainloop@for (i9_10 in listOf(61, 72, 83, 94))
        for (i11 in 1..9)
            for (i12_13 in listOf(51, 62, 73, 84, 95))
                for (i14 in 1..9) {
                    alu.inputValues = "$prevResult$i9_10$i11$i12_13$i14"
                    val z = Array(15) {0}
                    for (i in 9..14)
                        z[i] = alu.runProgram(startSub(i), endSub(i))
                    //println("z1 $z1 z2 $z2 z3 $z3 z4 $z4 z5 $z5 z6 $z6 z7 $z7 z8 $z8 ")
                    if (z[14] == 0)
                        break@mainloop
                }
    return alu.inputValues
}


/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    input = getInput(args)
    if (part1_2 == 1) {
        elapsedTime = measureTimeMillis { result = calculateResultPart1(input) }
        println("$RESULT_STRING1: $result")
    }
    else {
        elapsedTime = measureTimeMillis { result = calculateResultPart2(input) }
        println("$RESULT_STRING2: $result")
    }
    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}