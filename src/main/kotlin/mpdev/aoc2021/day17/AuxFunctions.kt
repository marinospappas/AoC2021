package mpdev.aoc2021.day17

import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "24.02.21"
const val DAY = "Day17"
const val PUZZLE = "Tricky Shot"
val RESULT_STRING1 = "Max height"
val RESULT_STRING2 = "Number of good velocities"
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

/** get puzzle input */
fun getInput(): Ballistics {
    return Ballistics(277, 318, -92, -53)
    //return Ballistics(10000, 20000, -30000, -20000)
}
