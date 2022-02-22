package mpdev.aoc2021.day14

lateinit var template: String

var part1_2 = 1
var result = -1L

/** calculate part 1 */
fun calculateResult(template: String, polymer: Polymer, steps: Int): Long {
    var s = template
    for (i in 1..steps)
        s = polymer.applyRules(s)
    polymer.countElements(s)
    return polymer.countOfMostFreqElement() - polymer.countOfLeastFreqElement()
}

/** calculate part 2 */
fun calculateResultPart2(template: String, polymer: Polymer, steps: Int): Long {
    polymer.applyRulesPart2(template,steps)
    polymer.countElementsFromPairs()
    return polymer.countOfMostFreqElement() - polymer.countOfLeastFreqElement()
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val myPolymer = getInput(args)

    val t1 = System.currentTimeMillis()

    if (part1_2 == 1)
        result = calculateResult(template, myPolymer, 10)
    else
        result = calculateResultPart2(template, myPolymer, 40)

    println("$RESULT_STRING1 - part $part1_2: $result")

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}