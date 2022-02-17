package mpdev.aoc2021.day09

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "16.02.21"
const val DAY = "Day9"
const val PUZZLE = "Smoke Basin"
val RESULT_STRING1 = "Total Risk Level"
val RESULT_STRING2 = "Size product of 3 larger basins"
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
fun getInput(args: Array<String>): HeightMap {
    val filename = getFilename1(args)
    var firstLine = true
    val inputMatrix: MutableList<List<Int>> = mutableListOf()
    var xSize = 0
    File(filename).readLines().forEach {
        if (firstLine) {
            xSize = it.length
            firstLine = false
        }
        if (it.length != xSize)
            abort("bad line [$it]")
        val line = mutableListOf<Int>()
        it.forEach { n -> line.add(n.digitToInt()) }
        inputMatrix.add(line)
    }
    val ySize = inputMatrix.size
    return HeightMap(inputMatrix as List<List<Int>>, xSize, ySize)
}