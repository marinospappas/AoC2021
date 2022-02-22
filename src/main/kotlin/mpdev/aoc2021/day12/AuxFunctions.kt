package mpdev.aoc2021.day12

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "18.02.21"
const val DAY = "Day12"
const val PUZZLE = "Passage Planning"
val RESULT_STRING1 = "Total Number of Paths"
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

/** check if it's a minor node */
fun isMinorNode(id: String) = id[0].isLowerCase()

/** get puzzle input */
fun getInput(args: Array<String>, loopCheck: Boolean = false): Network {
    val filename = getFilename1(args)
    val myNet = Network(detectLoop = loopCheck)
    File(filename).readLines().forEach {
        val nodeIds = it.split("-")
        if (nodeIds.size != 2)
            abort("bad line [$it]")
        // add connections both ways
        myNet.addConnection(nodeIds[0], nodeIds[1])
        myNet.addConnection(nodeIds[1], nodeIds[0])
        nodeIds.forEach { id ->
            if (isMinorNode(id)) {
                myNet.netMap.map[id]?.maxTimesAllowed = 1
                myNet.netMap.map[id]?.minor = true
            }
        }
    }
    return myNet
}