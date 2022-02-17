package mpdev.aoc2021.day06

// the fish ages list
const val MAX_AGE = 8
const val REBORN_AGE = 6
var fishAges = Array(MAX_AGE+1) {0L}
var days = 80
var result = -1L

/** process initial ages (day 0) */
fun processDay0(input: Array<Int>, ages: Array<Long>) {
    input.forEach { ages[it]++ }
}

/** process each line of input and apply to canvas */
fun processInput(input: Array<Int>, ages: Array<Long>, days: Int) {
    processDay0(input, ages)
    var counter = 0
    while (++counter <= days) {
        val age0 = ages[0]   // keep number at 0 age
        for (i in 1..MAX_AGE) {
            ages[i-1] = ages[i] // shift ages 1-8 to the left (reduce age by 1)
        }
        // apply 0 ages rule
        ages[REBORN_AGE] += age0
        ages[MAX_AGE] = age0
    }
}

/** calculate result part 1 */
fun calculateResult(ages: Array<Long>): Long {
    return ages.sum()
}

/** main */
fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val input = getInput(args)
    if (part1_2 == 2)
        days = 256
    processInput(input, fishAges, days)
    result = calculateResult(fishAges)

    println("$RESULT_STRING after $days days: $result")
    exit("$DAY Part $part1_2 - Completed")
}