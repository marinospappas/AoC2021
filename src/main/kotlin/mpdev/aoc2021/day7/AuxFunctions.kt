package mpdev.aoc2021.day7

import java.io.File
import java.util.Collections.max
import java.util.Collections.min
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "14.02.21"
const val DAY = "Day7"
const val PUZZLE = "The Treachery of ****"
val RESULT_STRING = "Minimum cost"
const val USAGE = "usage: Main -part1|-part2 Input_File"

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
        println("input file: $filename")
        return filename
    }
    return abort(USAGE).toString()
}

/** input to string for testing */
fun arrayToString(a: Array<Int>): String {
    var s = ""
    a.forEach { s += "$it," }
    return s.substring(0,s.length-1)
}

/** get puzzle input */
fun getInput(args: Array<String>): Array<Int> {
    val filename = getFilename1(args)
    var positionsList = arrayOf<Int>()
    try {
        val input = File(filename).readText()
        val inpList = input.split(",")
        positionsList = Array(inpList.size) {0}
        for (i in inpList.indices)
            positionsList[i] = inpList[i].toInt()
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    basePosition = min(positionsList.asList())
    maxPosition = max(positionsList.asList())
    return positionsList
}


