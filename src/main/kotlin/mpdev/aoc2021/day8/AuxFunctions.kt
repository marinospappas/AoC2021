package mpdev.aoc2021.day8

import java.io.File
import java.math.BigInteger
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "15.02.21"
const val DAY = "Day8"
const val PUZZLE = "7 Segment Displays"
val RESULT_STRING1 = "Total number of 1,4,7,8"
val RESULT_STRING2 = "Sum of Output Values "
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
    return abort(mpdev.aoc2021.day7.USAGE).toString()
}

/** sort the characters in a string */
fun String.sort() =
    toCharArray().sorted().joinToString("")

/** process one line of input */
fun processLine(line: String): MyInput {
    val mappings = mutableListOf<String>()
    val measurements = mutableListOf<String>()
    val inpList = line.split("|")
    if (inpList.size < 2)
        abort("bad input line [$line]")
    val inp1 = inpList[0].trim()
    val inp2 = inpList[1].trim()
    measurements.addAll(inp2.split(" ").toList())
    val mapppingsSorted = mutableListOf<String>()
    inp1.split(" ").forEach { s -> mapppingsSorted.add(s.sort()) }
    mappings.addAll(mapppingsSorted)
    return MyInput(mappings, measurements)
}

/** get puzzle input */
fun getInput(args: Array<String>): File {
    val filename = getFilename1(args)
    return File(filename)
}