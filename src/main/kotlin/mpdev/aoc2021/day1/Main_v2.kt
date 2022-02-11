package mpdev.aoc2021.day1

import java.io.File
import java.lang.Integer.MAX_VALUE

/** get puzzle input and process each line */
fun processInputPart1(args: Array<String>): Int {
    var filename = ""
    for (i in args.indices) {
        if (args[0].startsWith("-"))
            continue
        filename = args[i]
        break
    }
    if (filename == "") abort(USAGE)
    println("input file: ${filename}")
    var prevDepth = MAX_VALUE
    var result = 0
    try {
        File(filename).forEachLine {
            val depth = it.toInt()
            if (depth > prevDepth)
                ++result
            prevDepth = depth
        }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return result
}

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    totalDeeper = processInputPart1(args)

    println("Total measurements deeper than previous: $totalDeeper")
    exit("$DAY Part $part1_2 - Completed")
}