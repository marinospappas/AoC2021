package mpdev.aoc2021.day15

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "20.02.21"
const val DAY = "Day15"
const val PUZZLE = "Chiton"
val RESULT_STRING1 = "Lowest Total Risk Level"
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

/** build network from input matrix */
fun buildNetwork(input: List<String>): Network {
    val myNetwork = Network()
    for (y in input.indices) {
        for (x in input[y].indices) {
            val cost = input[y][x].digitToInt()
            myNetwork.addNode("($x,$y)", cost, myNetwork.getConnectedNodes(x, y), listOf(), myNetwork.getNodeIdFromCoords(x, y))
        }
    }
    // updated costs to connections
    myNetwork.grid.forEach { it.costToConnections = myNetwork.getCostsToConnections(it.thisId) }
    if (myNetwork.grid.size != xSize * ySize)       // check size of grid
        abort("error in processing input - grid size was ${myNetwork.grid.size}")
    for (i in myNetwork.grid.indices)
        if (myNetwork.grid[i].thisId != i)          // check generated ids
            abort("error in grid Ids - node $i has wrong id ${myNetwork.grid[i].thisId}")
    return myNetwork
}

/** get puzzle input */
fun getInput(args: Array<String>): Network {
    val filename = getFilename1(args)
    var myInput: MutableList<String> = mutableListOf()
    var lineNumber = 0
    File(filename).readLines().forEach {
        if (lineNumber++ == 0)
            xSize = it.length
        if (it.length != xSize)
            abort("bad line [$it]")
        if (part1_2 == 2) {
            val newRow = convertRowToPart2(it)
            myInput.add(newRow)
        }
        else
            myInput.add(it)
    }
    ySize = myInput.size
    if (part1_2 == 2) {
        xSize *= 5
        ySize *= 5
        myInput = convertBlockToPart2(myInput)
    }
    return buildNetwork(myInput)
}

/** convert a block to part 2 */
fun convertBlockToPart2(block: List<String>): MutableList<String> {
    val part2Block = mutableListOf<String>()
    part2Block.addAll(block)
    var myBlock = block
    for (i in 1..4) {
        val tempBlock = mutableListOf<String>()
        myBlock.forEach { s -> tempBlock.add(s.convert()) }
        part2Block.addAll(tempBlock)
        myBlock = tempBlock
    }
    return part2Block
}

/** convert an input row to part 2 */
fun convertRowToPart2(row: String): String {
    var myRow = row
    var part2row = String(row.toCharArray())
    for (i in 1..4) {
        myRow = myRow.convert()
        part2row += myRow
    }
    return part2row
}

/** convert a string as per part 2 spec */
fun String.convert(): String {
    var s = ""
    forEach { c -> s += c.convert() }
    return s
}

/** convert a character as per part 2 spec */
fun Char.convert(): Char {
    var newChar = digitToInt() + 1
    if (newChar >= 10)
        newChar = 1
    return newChar.toString()[0]
}
