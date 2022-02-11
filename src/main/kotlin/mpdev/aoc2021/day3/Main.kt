package mpdev.aoc2021.day3

// the diagnostics input
lateinit var diagnosticData: MutableList<String>
var dataLength: Int = 0
class Result(var gamma: Int = 0, var epsilon: Int = 0)
var result = Result()
class Result2(var oxygenRating: Int = 0, var co2Rating: Int = 0)
var result2 = Result2()
var powerConsum = 0
var survivalRate = 0


/** find out the prevailing value (0 or 1) in the datalist for a specific position */
fun getPrevailingValue(dataList: List<String>, pos: Int): Char {
    val count1 = dataList.filter { it[pos] == '1' } .count()
    return if (count1 >= dataList.size - count1) '1' else '0'
}

/** process diagnostics epsilon - gama */
fun processDiagPart1(diagnData: List<String>): Result {
    var epsilon = 0
    val res = Result()
    for (i in 0 until dataLength)
        epsilon = 2 * epsilon + getPrevailingValue(diagnData, i).digitToInt()
    res.epsilon = epsilon
    res.gamma = power(2, dataLength) - 1 - res.epsilon
    return res
}

/** get a number from the data set by applying specific criteria (passed as parameter) */
fun getNumberPart2(diagnData: List<String>, criteria: (data: List<String>, pos: Int) -> Char ): Int {
    var dataSet = diagnData
    for (i in 0 until dataLength) {
        dataSet = dataSet.filter { it[i] == criteria(dataSet, i) }
        if (dataSet.size == 1) {
            return Integer.parseInt(dataSet[0], 2)
        }
    }
    return 0
}

/** process diagnostics oxygen - co2 */
fun processDiagPart2(diagnData: List<String>): Result2 {
    val res = Result2()
    // oxygen - prevailing value as a criterion
    res.oxygenRating = getNumberPart2(diagnData, ::getPrevailingValue )
    // co2 - lambda that reverses the prevailing value as a criterion
    res.co2Rating = getNumberPart2(diagnData) { l, i -> if (getPrevailingValue(l, i) == '1') '0' else '1' }
    return res
}

/** produce answer part 1 */
fun produceAnswerPart1(res: Result) = res.epsilon * res.gamma

/** produce answer part 2 */
fun produceAnswerPart2(res: Result2) = res.oxygenRating * res.co2Rating

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    diagnosticData = getInput(args)
    if (part1_2 == 1) {
        result = processDiagPart1(diagnosticData)
        powerConsum = produceAnswerPart1(result)
        println("$RESULT_STRING: $powerConsum")
    }
    else {
        result2 = processDiagPart2(diagnosticData)
        survivalRate = produceAnswerPart2(result2)
        println("$RESULT_STRING2: $survivalRate")
    }
    exit("$DAY Part $part1_2 - Completed")
}