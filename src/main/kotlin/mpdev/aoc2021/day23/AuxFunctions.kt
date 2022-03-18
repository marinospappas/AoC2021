package mpdev.aoc2021.day23

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.math.BigInteger
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "17.03.22"
const val DAY = "Day23"
const val PUZZLE = "Reactor Reboot"
val RESULT_STRING1 = "Minimum Energy"
val RESULT_STRING2 = " "
const val USAGE = "usage: Main -part1|-part2 Input_File"

/** own power fun */
fun power(n: Long, exp: Int): ULong {
    return BigInteger.valueOf(n).pow(exp) as ULong
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
fun getInput(args: Array<String>): String {
    val filename = getFilename1(args)
    val myInput = File(filename).readLines()
    return processInput(myInput)
}

fun processInput(inp: List<String>): String {
    var s = inp[_h].substring(1,inp[_h].length-1)     // hall
    for (i in 1..4) {       // rooms
        val roomIndx = 3 + 2 * (i - 1)
        s += inp[_1].substring(roomIndx, roomIndx+1)
        s += inp[_2].substring(roomIndx, roomIndx+1)
        if (part1_2 == 2) {
            s += inp[_3].substring(roomIndx, roomIndx+1)
            s += inp[_4].substring(roomIndx, roomIndx+1)
        }
    }
    return s
}


fun String.toStateU(): ULong {
    val s = this.replace('.', '0').replace('A', '1').replace('B', '2')
        .replace('C', '3').replace('D', '4')
    return s.toULong()
}

fun ULong.toStateString(): String {
    val s = this.toString().replace('0', '.').replace('1', 'A').replace('2', 'B')
        .replace('3', 'C').replace('4', 'D')
    return "............".substring(0,(19 + 8 * (part1_2-1)) - s.length) + s
}

