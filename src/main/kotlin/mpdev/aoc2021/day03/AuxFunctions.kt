package mpdev.aoc2021.day03

import java.io.File
import java.math.BigInteger
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "11.02.21"
const val DAY = "Day3"
const val PUZZLE = "Binary Diagnostic"
const val RESULT_STRING = "Power consumption (epsilon x gamma)"
const val RESULT_STRING2 = "Oxygen rating x CO2 rating"
const val USAGE = "usage: Main -part1|-part2 Input_File"

/** own power fun */
fun power(n: Long, exp: Int): Int {
    return BigInteger.valueOf(n).pow(exp).toInt()
}

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

/** get puzzle input */
fun getInput(args: Array<String>): MutableList<String> {
    var filename = ""
    val inputData = mutableListOf<String>()
    for (i in args.indices) {
        if (args[i].startsWith("-"))
            continue
        filename = args[i]
        break
    }
    if (filename == "") abort(USAGE)
    println("input file: ${filename}")
    var firstLine = true
    try {
        File(filename).forEachLine {
            if (firstLine) {
                dataLength = it.length
                firstLine = false
            }
            if (it.length != dataLength)
                abort("bad data: $it")
            inputData.add(it)
        }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return inputData
}
