package mpdev.aoc2021.day22

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.math.BigInteger
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "12.03.22"
const val DAY = "Day22"
const val PUZZLE = "Reactor Reboot"
val RESULT_STRING1 = "Total number of cuboids on"
val RESULT_STRING2 = " "
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
fun getInput(args: Array<String>): List<Cuboid> {
    val filename = getFilename1(args)
    val myCubList = mutableListOf<Cuboid>()
    File(filename).forEachLine { myCubList.add(processLine(it)) }
    return myCubList
}

fun processLine(line: String): Cuboid {
    var split = line.split(" ")
    val state = if (split[0] == "off") 0 else 1
    split = split[1].split(",")
    val startR = Array(3){0}
    val endR = Array(3){0}
    for (i in 0..2) {
        val split1 = split[i].split("..")
        startR[i] = split1[0].substring(2).toInt()
        endR[i] = split1[1].toInt()
    }
    return Cuboid(startR[0], endR[0], startR[1], endR[1], startR[2], endR[2], state)
}
