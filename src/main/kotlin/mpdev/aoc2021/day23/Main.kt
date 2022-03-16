package mpdev.aoc2021.day23

import kotlin.system.measureTimeMillis

lateinit var input: List<String>
var part1_2 = 1
var result = -1L

/** process each line of input and count entries requested */
fun calculateTotal(listOfCuboids: List<String>): Long {
    return 0L
}


/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    input = getInput(args)
    elapsedTime = measureTimeMillis { result = calculateTotal(input) }
    println("$RESULT_STRING1: $result")

    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}