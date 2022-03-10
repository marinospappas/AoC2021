package mpdev.aoc2021.day21

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.math.BigInteger
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "08.03.22"
const val DAY = "Day21"
const val PUZZLE = "Roll the Dice"
val RESULT_STRING1 = "Least Score * number of rolls"
val RESULT_STRING2 = "Winner's number of Universes "
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

/** get input filename from args */
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

/** get puzzle input */
fun getInput(args: Array<String>) {
    val filename = getFilename1(args)
    val inputLines = File(filename).readLines() as MutableList<String>
}
