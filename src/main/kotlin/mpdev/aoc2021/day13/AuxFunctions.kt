package mpdev.aoc2021.day13

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "19.02.22"
const val DAY = "Day13"
const val PUZZLE = "Transparent Origami"
val RESULT_STRING1 = "Total Number of Dots"
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
fun getInput(args: Array<String>): Matrix {
    val filename = getFilename1(args)
    val xList = mutableListOf<Int>()
    val yList = mutableListOf<Int>()
    File(filename).readLines().forEach {
        var str = it.split(",")
        if (str.size == 2) {
            xList.add(str[0].toInt())
            yList.add(str[1].toInt())
            return@forEach
        }
        str = it.split(" ")
        if (str[0] == "fold" && str[1] == "along") {
            flipInstr.add(str[2])
            return@forEach
        }
    }
    val xSize = xList.maxOrNull()?:0
    val ySize = yList.maxOrNull()?:0
    val myMatrix = Matrix(xSize+1, ySize+1)
    for (i in xList.indices)
        myMatrix.setDot(xList[i], yList[i])
    return myMatrix
}