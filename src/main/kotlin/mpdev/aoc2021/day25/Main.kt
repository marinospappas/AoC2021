package mpdev.aoc2021.day25

import kotlin.system.measureTimeMillis

lateinit var input: SeaBed
var part1_2 = 1
var result = -1

/** process each line of input and count entries requested */
fun calculatePart1(seaBed: SeaBed): Int {
    var i = 0
    while (++i > 0) {
        val newMap = seaBed.nextStep()
        if (newMap.isEqual(seaBed.map))
            break
        seaBed.map = newMap
    }
    return i
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE - $AUTHOR - $DATE")

    val elapsedTime: Long
    input = getInput(args)
    elapsedTime = measureTimeMillis { result = calculatePart1(input) }
    println("$RESULT_STRING1: $result")

    exit("$DAY - Completed in $elapsedTime msec")
}