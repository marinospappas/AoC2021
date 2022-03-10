package mpdev.aoc2021.day16

import java.io.File
import java.util.BitSet
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "23.02.22"
const val DAY = "Day16"
const val PUZZLE = "Decoding Packets"
val RESULT_STRING1 = "Packet Processing"
val RESULT_STRING2 = ""
const val USAGE = "usage: MainKt -part1|-part2 Input_File"

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
fun getInput(args: Array<String>): BitSet {
    val filename = getFilename1(args)
    return processInput(File(filename).readText())
}

/** convert input string to bitset */
fun processInput(inputStr: String): BitSet {
    var input = inputStr
    if (input.length % 2 == 1)
        input += "0"
    val myBitsSet = BitSet()
    var bitIndx = 0
    input.forEach { c ->
        val hexDigit = "%4s".format(c.digitToInt(16).toString(2)).replace(' ', '0')
        hexDigit.forEach { b ->
            myBitsSet.set(bitIndx++, b == '1')
        }
    }
    return myBitsSet
}
