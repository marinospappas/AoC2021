package mpdev.aoc2021.day5

import java.io.File
import java.math.BigInteger
import java.util.regex.Pattern
import kotlin.contracts.contract
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "13.02.21"
const val DAY = "Day5"
const val PUZZLE = "Hydrothermal Venture"
const val RESULT_STRING = "Number of points where 2 or more lines overlap"
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

/** process one line */
fun decodeInputLine(s: String): List<Point> {
    val points = s.trim().split(" -> ")
    val pointsList = mutableListOf<Point>()
    if (points.size < 2)
        abort("invalid input line [s]")
    pointsList.add(Point(points[0]))
    pointsList.add(Point(points[1]))
    return pointsList
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

/** get puzzle input */
fun getInput(args: Array<String>): File? {
    val filename = getFilename1(args)
    try {
        return File(filename)
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return null
}


