package mpdev.aoc2021.day14

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "20.02.21"
const val DAY = "Day14"
const val PUZZLE = "Extended Polymerization"
val RESULT_STRING1 = "Result"
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
fun getInput(args: Array<String>): Polymer {
    val filename = getFilename1(args)
    val myMap = mutableMapOf<String,String>()
    var linecount = 0
    File(filename).readLines().forEach {
        if (++linecount == 1) {
            template = it
            return@forEach
        }
        if (it == "")
            return@forEach
        var str = it.split("->")
        myMap[str[0].trim()] = str[1].trim()
    }
    return Polymer(myMap)
}