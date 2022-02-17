package mpdev.aoc2021.day8

class MyInput(var mappings: List<String>, var measurements: List<String>)
lateinit var input: MyInput
var part1_2 = 1
var result = -1

/** process each line of input and count entries requested */
fun calculateTotalPart1(args: Array<String>, criteria: (Int)->Boolean): Int {
    var total = 0
    getInput(args).readLines().forEach {
        val measurements = processLine(it).measurements
        total += measurements.count { m -> criteria(m.length) }
    }
    return total
}

/** process each line of input, decode displays and calculate sum of measurements */
fun calculateResultPart2(args: Array<String>): Int {
    var output = 0
    getInput(args).readLines().forEach {
        val myInput = processLine(it)
        val seg7decoder = SevenSegmentDisplay(myInput.mappings)
        val measurements = myInput.measurements
        var measValue = 0
        measurements.forEach { m -> measValue = measValue*10 + seg7decoder.convertMappedSegmentsToNumber(m) }
        output += measValue
    }
    return output
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val t1 = System.currentTimeMillis()

    if (part1_2 == 1) {
        result = calculateTotalPart1(args) { l -> l <= 4 || l == 7 }
        println("$RESULT_STRING1: $result")
    }
    else {
        result = calculateResultPart2(args)
        println("$RESULT_STRING2: $result")
    }

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}