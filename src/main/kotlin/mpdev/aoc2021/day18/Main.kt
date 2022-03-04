package mpdev.aoc2021.day18

import mpdev.aoc2021.day18.StringsMaths.magnitude
import kotlin.system.measureTimeMillis

lateinit var input: List<String>
var xSize = 0
var ySize = 0
var part1_2 = 1
var result = 0

fun calculateResultPart1(myInput: List<String>): Int {
    var sum = StringsMaths.tokenise(myInput[0])
    var reduced = sum
    for (i in 1 until myInput.size) {
        sum = StringsMaths.add(reduced, StringsMaths.tokenise(myInput[i]))
        reduced = StringsMaths.reduce(sum)
    }
    val finalMmagnitude = StringsMaths.magnitude(reduced)
    println("final sum: ${StringsMaths.toString(reduced)}")
    return finalMmagnitude
}

fun calculateResultPart2(myInput: List<String>): Int? {
    val magnList = mutableListOf<Int>()
    for(i in myInput.indices)
        for (j in myInput.indices) {
            if (i != j) {
                val s1 = StringsMaths.tokenise(myInput[i])
                val s2 = StringsMaths.tokenise(myInput[j])
                val sum = StringsMaths.add(s1,s2)
                val reduced = StringsMaths.reduce(sum)
                val thisMagnitude = StringsMaths.magnitude(reduced)
                magnList.add(thisMagnitude)
            }
        }
    return magnList.maxOrNull()
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    input = getInput(args)

    val elapsedeTime = measureTimeMillis {
        result = if (part1_2 == 1)
            calculateResultPart1(input)
        else
            calculateResultPart2(input) ?: -1
    }
    println("$RESULT_STRING1 - part $part1_2: $result")
    exit("$DAY Part $part1_2 - Completed in $elapsedeTime msec")
}
