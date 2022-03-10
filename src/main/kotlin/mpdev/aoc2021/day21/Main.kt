package mpdev.aoc2021.day21

import java.lang.Long.max
import kotlin.system.measureTimeMillis

var part1_2 = 1
var result = -1L

/** process each line of input and count entries requested */
fun calculateTotalPart1(posn: Array<Int>): Int {
    return play1(posn)
}

/** process each line of input and count entries requested */
fun calculateTotalPart2(posn: Array<Int>): Long {
    val wins = play2(posn[0],21,posn[1],21)
    println ("${wins[0]}\n${wins[1]}")
    return max(wins[0], wins[1])
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    //input = getInput(args)
    if (part1_2 == 1) {
        elapsedTime = measureTimeMillis { result = calculateTotalPart1(arrayOf(4 - 1, 6 - 1)).toLong() }
        println("$RESULT_STRING1: $result")
    }
    else {
        elapsedTime = measureTimeMillis { result = calculateTotalPart2(arrayOf(4 - 1, 6 - 1)) }
        println("$RESULT_STRING2: $result")
    }

    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}