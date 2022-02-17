package mpdev.aoc2021.day9

lateinit var input: HeightMap
var part1_2 = 1
var result = -1

/** process each line of input and count entries requested */
fun calculateRiskLevelPart1(input: HeightMap): Int {
    input.getLowPoints()
    return input.riskLevel
}

fun calculateResultPart2(input: HeightMap): Int {
    input.getBasins()
    return input.basins[0].points.size * input.basins[1].points.size * input.basins[2].points.size
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    input = getInput(args)
    println("HeightMap size: ${input.xSize} x ${input.ySize}")
    val t1 = System.currentTimeMillis()

    if (part1_2 == 1) {
        result = calculateRiskLevelPart1(input)
        println("$RESULT_STRING1: $result")
    }
    else {
        result = calculateResultPart2(input)
        println("$RESULT_STRING2: $result")
    }

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}