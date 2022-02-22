package mpdev.aoc2021.day13

import mpdev.aoc2021.day01.totalDeeper

var flipInstr = mutableListOf<String>()

var part1_2 = 1
var result = -1

/** perform a series of flips */
fun performFlips(matrix: Matrix, count: Int): Int {
    var myResultP1 = 0
    for (i in flipInstr.indices) {
        val s = flipInstr[i].split("=")
        when (s[0]) {
            "y" -> matrix.flipHor(s[1].toInt())
            "x" -> matrix.flipVert(s[1].toInt())
        }
        if (i==0)
            myResultP1 = matrix.countDots()
    }
    return myResultP1
}

/** calculate part 1 */
fun calculateResult(myMatrix: Matrix): Int {
    return performFlips(myMatrix, 1)
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part 1+2 - $AUTHOR - $DATE")

    val inputData = getInput(args)
    println("Initial Grid size: ${inputData.xSize} x ${inputData.ySize}")

    val t1 = System.currentTimeMillis()

    result = calculateResult(inputData)
    println("$RESULT_STRING1 - part 1: $result")

    println("Grid size: ${inputData.xSize} x ${inputData.ySize}")
    println("Resulting Grid:\n${inputData.toStringEnh()}")

    val t2 = System.currentTimeMillis()
    exit("$DAY Part 1+2 - Completed in ${t2-t1} msec")
}