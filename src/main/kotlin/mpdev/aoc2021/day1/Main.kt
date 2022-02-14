package mpdev.aoc2021.day1

import java.io.File
import java.lang.System.err
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "11.02.21"
const val DAY = "Day1"
const val PUZZLE = "Sonar Sweep"
const val USAGE = "usage: Main -part1|-part2 Input_File"


// our data set
class DataObj(var depth: Int, var isDeeper: Boolean = false)
lateinit var depthData: MutableList<DataObj>
var totalDeeper = 0

// part 2 - the running sums (3-measurements sum)
lateinit var depthRunSum: MutableList<DataObj>

/** exit program */
fun exit(msg: String) {
    println(msg)
    exitProcess(0)
}

/** abort */
fun abort(errMsg: String) {
    err.println(errMsg)
    exitProcess(1)
}

/** aux function for testing - get list of depths */
fun getDepths(dataPoints: List<DataObj>): List<Int> {
    val depths = mutableListOf<Int>()
    dataPoints.forEach { depths.add(it.depth) }
    return depths
}
/** aux function for testing - get list of isdeeper */
fun getIsDeeper(dataPoints: List<DataObj>): List<String> {
    val deeper = mutableListOf<String>()
    dataPoints.forEach { deeper.add( if (it.isDeeper) "increased" else "decreased" ) }
    if (deeper.isNotEmpty())
        deeper[0] = "N/A"
    return deeper
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
fun getInput(args: Array<String>): MutableList<DataObj> {
    val dataPoints = mutableListOf<DataObj>()
    var filename = ""
    for (i in args.indices) {
        if (args[i].startsWith("-"))
            continue
        filename = args[i]
        break
    }
    if (filename == "") abort(USAGE)
    println("input file: ${filename}")
    try {
        File(filename).forEachLine { dataPoints.add(DataObj(it.toInt())) }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return dataPoints
}

/** part 2 only - process running sum */
fun processRunSum(dataPoints: List<DataObj>): MutableList<DataObj> {
    val runSum = mutableListOf<DataObj>()
    for (i in dataPoints.indices) {
        if (i+2 < dataPoints.size)
            runSum.add(DataObj(dataPoints[i].depth + dataPoints[i+1].depth + dataPoints[i+2].depth))
    }
    return runSum
}

/** process input to produce additional data */
fun processDepths(dataPoints: MutableList<DataObj>) {
    for (i in dataPoints.indices)
        if (i > 0 && dataPoints[i].depth > dataPoints[i-1].depth)
            dataPoints[i].isDeeper = true
}

/** produce answer */
fun produceAnswer(dataPoints: List<DataObj>): Int = dataPoints.filter { it.isDeeper == true } .count()

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    depthData = getInput(args)
    if (part1_2 == 2)   // data transformation for part 2 only
        depthData = processRunSum(depthData)
    processDepths(depthData)
    totalDeeper = produceAnswer(depthData)

    println("Total measurements deeper than previous: $totalDeeper")
    exit("$DAY Part $part1_2 - Completed")
}