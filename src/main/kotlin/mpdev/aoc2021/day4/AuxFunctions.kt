package mpdev.aoc2021.day4

import java.io.File
import java.math.BigInteger
import java.util.regex.Pattern
import kotlin.contracts.contract
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "13.02.21"
const val DAY = "Day4"
const val PUZZLE = "Bingo"
const val RESULT_STRING = "Winning board and total score"
      const val USAGE = "usage: Main -part1|-part2 Boards_File Numbers_File"

/** exit program */
fun exit(msg: String) {
    println(msg)
    exitProcess(0)
}

/** abort */
fun abort(errMsg: String) {
    System.err.println(errMsg)
    exitProcess(1)
}

/** get prt 1 or 2 from args */
fun getPart1or2(args: Array<String>): Int {
    for (i in args.indices)
        if (args[i].startsWith("-"))
            when (args[i]) {
                "-part1" -> return 1
                "-part2" -> return 2
            }
    return 0
}

/** get bingo filename from args */
fun getFilename1(args: Array<String>): String {
    for (i in args.indices) {
        if (args[0].startsWith("-"))
            continue
        val filename = args[i]
        println("boards file: ${filename}")
        return filename
    }
    return abort(USAGE).toString()
}

/** process row of numbers */
fun processRow(line: String, firstRow: Boolean): MutableList<BoardCell> {
    val numbers = line.trim().split(Pattern.compile(" +"))
    if (firstRow)
        boardSize = numbers.size
    // build row and add it to current board
    val row = mutableListOf<BoardCell>()
    for (n in numbers)
        row.add(BoardCell(n.toInt()))
    if (row.size != boardSize)
        abort("bad row: [$line]")
    return row
}

/** get puzzle input */
fun getInput(args: Array<String>): MutableList<BingoBoard> {
    val myBingoData = mutableListOf<BingoBoard>()
    val filename = getFilename1(args)       // Bingo boards
    var firstRow = true
    var currentBoard = BingoBoard()
    try {
        File(filename).forEachLine {
            // ignore empty lines
            if (it == "")
                return@forEachLine
            // build row and add it to current board
            val row = processRow(it, firstRow)
            currentBoard.cells.add(row)
            // when board reaches size then add it to the boards list
            if (currentBoard.cells.size == boardSize) {
                myBingoData.add(currentBoard)
                currentBoard = BingoBoard()
            }
            firstRow = false
        }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    println("board size: $boardSize, number of boards: ${myBingoData.size}")
    return myBingoData
}

/** get numbers filename from args */
fun getFilename2(args: Array<String>): String {
    var firstFile = true
    for (i in args.indices) {
        if (args[i].startsWith("-"))
            continue
        if (!firstFile) {
            val filename = args[i]
            println("numbers file: ${filename}")
            return filename
        }
        firstFile = false
    }
    return abort(USAGE).toString()
}

/** get bingo numbers from input */
fun getInputNumbers(args: Array<String>): List<Int> {
    val fileName = getFilename2(args)
    var input = ""
    val numList = mutableListOf<Int>()
    try {
        input = File(fileName).readText()
        val numbers = input.split(",")
        for (num in numbers)
            numList.add(num.toInt())
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return numList
}

