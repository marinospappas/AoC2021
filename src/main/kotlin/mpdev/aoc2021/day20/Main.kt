package mpdev.aoc2021.day20

import kotlin.system.measureTimeMillis

lateinit var input: Image
var part1_2 = 1
var result = -1

/** process each line of input and count entries requested */
fun calculateTotalPart1(img: Image): Int {
    var myImg = img
    println("$myImg\n")
    println("initial count of lit pixels: ${myImg.countPixelsOn()}")
    for (i in 1.. if(part1_2 == 1) 2 else 50) {
        myImg = myImg.extend(i%2)
        //println("$myImg\n")
        myImg = myImg.enhance(i%2)
        //println("$myImg\n")
    }
    println(myImg)
    return myImg.countPixelsOn()
}


/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    input = getInput(args)
    elapsedTime = measureTimeMillis { result = calculateTotalPart1(input) }
    println("$RESULT_STRING1: $result")

    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}