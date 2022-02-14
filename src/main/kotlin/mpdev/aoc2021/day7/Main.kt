package mpdev.aoc2021.day7

import java.lang.StrictMath.abs
import java.util.Collections.min

var part1_2 = 1

// the fish ages list
var positions = Array(1) {0}
var basePosition = 0
var maxPosition = 0
var minCost = -1
var cheapestPosition = -1
var part2Costs = Array(1) {0}

/** calculate upfront costs - part 2 */
fun calculateUpfrontCosts() {
    part2Costs = Array(maxPosition - basePosition + 1) {0}
    for (i in part2Costs.indices) {
        var cost = 0
        for (j in 0..i)
            cost += j
        part2Costs[i] = cost
    }
}

/** process each line of input and apply to canvas */
fun calulcateCosts(input: Array<Int>, cost: (Int,Int) -> Int): Array<Int> {
    val differences = Array(maxPosition - basePosition + 1) {0}
    for (i in basePosition..maxPosition) {
        input.forEach {
            differences[i- basePosition] += cost(it, i)
        }
    }
    return differences
}

/** calculate result part 1 */
fun calculateResult(costs: Array<Int>): Int {
    val costList = costs.asList()
    val minCost = min(costList)
    cheapestPosition = costList.indexOf(minCost) + basePosition
    return minCost
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val t1 = System.currentTimeMillis()

    positions = getInput(args)
    if (part1_2 == 1) {
        val costs = calulcateCosts(positions) { a, b -> abs(a - b) }
        minCost = calculateResult(costs)
    }
    else {
        calculateUpfrontCosts()
        val costs = calulcateCosts(positions) { a, b -> part2Costs[abs(a - b)] }
        minCost = calculateResult(costs)
    }

    val t2 = System.currentTimeMillis()

    println("positions range: $basePosition - $maxPosition")
    println("$RESULT_STRING: $minCost position $cheapestPosition")
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}