package mpdev.aoc2021.day4

// the bingo boards list
lateinit var bingoData: MutableList<BingoBoard>
lateinit var numbers: List<Int>
var winnerBoard = -1
var lastNumberToWin = -1
var score = 0

/** convert the bingo boards to string */
fun boardsToString(bingoBoards: List<BingoBoard>): String {
    var s = ""
    for (i in bingoBoards.indices) {
        if (i > 0)
            s += "\n"
        s += bingoBoards[i].toString()
    }
    return s
}

/** convert the list of numbers to string */
fun numbersToString(numbers: List<Int>): String {
    var s = ""
    for (i in numbers.indices) {
        if (i > 0)
            s += ","
        s += numbers[i].toString()
    }
    return s
}

/** solve Bingo - return first or last winner */
fun findWinningBoard(boardList: List<BingoBoard>, first: Boolean = false) {
    for (n in numbers)
        for (i in boardList.indices) {
            val b = boardList[i]
            if (b.isWinner)     // skip a board that has won already
                continue
            b.markNumber(n)
            if (b.boardWins()) {
                winnerBoard = i
                lastNumberToWin = n
                if (first)
                    return
            }
        }
}

/** calculate winning board score */
fun calculateScore(): Int {
    return if (winnerBoard >= 0)
        bingoData[winnerBoard].calcTotal() * lastNumberToWin
    else
        0
}

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    bingoData = getInput(args)
    numbers = getInputNumbers(args)
    findWinningBoard(bingoData, part1_2 == 1)
    score = calculateScore()
    println("$RESULT_STRING: ${winnerBoard+1}, $score")
    if (winnerBoard >=0)
        println(bingoData[winnerBoard])
    exit("$DAY Part $part1_2 - Completed")
}