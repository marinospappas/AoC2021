package mpdev.aoc2021.day11

lateinit var input: DataMap
var part1_2 = 1
var result = -1

/** calculate result part 1 */
fun calculateResultPart1(myMap: DataMap): Int {
    var flagsCount = 0
    for (i in 1..100) {
        myMap.processDataMapCycle()
        flagsCount += myMap.getFlagsCount()
    }
    return flagsCount
}

/** calculate result part 2 */
fun calculateResultPart2(myMap: DataMap): Int {
    var step = 0
    do {
        myMap.processDataMapCycle()
        ++step
    } while(! myMap.allFLagsAreOn())
    return step
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    input = getInput(args)
    println("DataMap size: ${input.xSize} x ${input.ySize}")
    val t1 = System.currentTimeMillis()

    if (part1_2 == 1) {
        result = calculateResultPart1(input)
        println("$RESULT_STRING1: $result")
    }
    else {
        result = calculateResultPart2(input)
        println("$RESULT_STRING2: $result")
    }

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}