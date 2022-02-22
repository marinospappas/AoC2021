package mpdev.aoc2021.day15

lateinit var inputData: Network
var xSize = 0
var ySize = 0
var part1_2 = 1
var result = -1

/** calculate part 1 */
fun calculateResultPart1(myNetworkMap: Network): Int {
    return myNetworkMap.findMinCostforAllPaths()
}

/** calculate part 2 */

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    inputData = getInput(args)

    println("grid size: $xSize x $ySize, number of nodes ${inputData.grid.size}")
    val t1 = System.currentTimeMillis()

    result = calculateResultPart1(inputData)

    println("$RESULT_STRING1 - part $part1_2: $result")

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}
