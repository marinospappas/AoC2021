package mpdev.aoc2021.day10

var part1_2 = 1
var scores = listOf<Long>()
var result = -1L

/** return error score in a list */
fun calculateErrorScore(args: Array<String>): Int {
    var score = 0
    getInput(args).readLines().forEach {
        val p = Parser(it)
        try {
            p.parseBlock()
        }
        catch (e: ParserException) {
            if (e.error != Error.incomplete)
                score += e.score
        }
    }
    return score
}

/** return autocomplete score in a list */
fun calculateParsingScore(args: Array<String>): List<Long> {
    val scoreList = mutableListOf<Long>()
    getInput(args).readLines().forEach {
        var error = Error.OK
        val p = Parser(it)
        try {
            p.parseBlock(true)     // if part2 then autocomplete
        }
        catch (e: ParserException) {
            error = e.error
        }
        if (error == Error.OK)
            scoreList.add(p.getAutoCompleteScore())
    }
    return scoreList
}

/** calculate auto-correct score */
fun calculateAutoCorrectScore(scores: List<Long>): Long {
    val scoreIndx = scores.size / 2
    return scores.sorted()[scoreIndx]
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val t1 = System.currentTimeMillis()

    if (part1_2 == 1) {
        result = calculateErrorScore(args).toLong()
        println("$RESULT_STRING1: $result")
    }
    else {
        scores = calculateParsingScore(args)
        result = calculateAutoCorrectScore(scores)
        println("$RESULT_STRING2: $result")
    }

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}