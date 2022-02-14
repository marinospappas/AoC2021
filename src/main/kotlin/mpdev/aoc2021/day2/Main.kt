package mpdev.aoc2021.day2

import java.io.File
import java.lang.System.err
import java.util.regex.Pattern
import kotlin.system.exitProcess

const val AOC = "AoC 2021"
const val AUTHOR = "Marinos Pappas"
const val DATE = "11.02.21"
const val DAY = "Day2"
const val PUZZLE = "Dive!"
const val USAGE = "usage: Main -part1|-part2 Input_File"

// the commands enum
enum class Cmd { fwd, down, up, none }

// our data set
class CmdObj(var cmd: Cmd = Cmd.none, var arg: Int = 0)
lateinit var commandData: MutableList<CmdObj>
class Result(var totalDepth: Int = 0, var totalFwd: Int = 0, var aim: Int = 0)
var result = Result()
var product = 0

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

/** aux function for testing - get list of commands */
fun getCmds(dataPoints: List<CmdObj>): List<String> {
    val cmds = mutableListOf<String>()
    dataPoints.forEach { cmds.add(enum2str(it.cmd)) }
    return cmds
}
/** aux function for testing - get list of cmd args */
fun getCmdArgs(dataPoints: List<CmdObj>): List<Int> {
    val cmdargs = mutableListOf<Int>()
    dataPoints.forEach { cmdargs.add(it.arg) }
    return cmdargs
}

/** translate cmd string to enum */
fun str2enum(s: String): Cmd {
    when (s) {
        "forward" -> return Cmd.fwd
        "down" -> return Cmd.down
        "up" -> return Cmd.up
        else -> return Cmd.none
    }
}
/** translate cmd enum to string */
fun enum2str(c: Cmd): String {
    when (c) {
        Cmd.fwd -> return "forward"
        Cmd.down -> return "down"
        Cmd.up -> return "up"
        else -> return "invalid"
    }
}

/** encode command */
fun encodeCmd(cmdString: String): CmdObj {
    val tokens = cmdString.trim().split(Pattern.compile(" +"))
    val cmdObj = CmdObj()
    if (tokens.size >= 2) {
        cmdObj.cmd = str2enum(tokens[0])
        cmdObj.arg = tokens[1].toInt()
    }
    if (cmdObj.cmd == Cmd.none)
        abort("invalid command: $cmdString")
    return cmdObj
}

/** get puzzle input */
fun getInput(args: Array<String>): MutableList<CmdObj> {
    val dataPoints = mutableListOf<CmdObj>()
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
        File(filename).forEachLine { dataPoints.add(encodeCmd(it)) }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return dataPoints
}

/** process commands part 1 */
fun processCommands(cmdList: List<CmdObj>,
                    depth: (Result, Int) -> Unit,
                    forward: (Result, Int) -> Unit): Result {
    val myResult = Result()
    cmdList.forEach {
        when (it.cmd) {
            Cmd.fwd -> forward(myResult, it.arg)
            Cmd.down -> depth(myResult, it.arg)
            Cmd.up -> depth(myResult, -it.arg)
            else -> {}
        }
    }
    return myResult
}

/** produce answer */
fun produceAnswer(result: Result) = result.totalDepth * result.totalFwd

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    commandData = getInput(args)
    if (part1_2 == 1)
        result = processCommands(commandData, { r, n -> r.totalDepth += n }, { r, n -> r.totalFwd += n } )
    else
        result = processCommands(commandData, { r, n -> r.aim += n }, { r, n -> r.totalFwd += n; r.totalDepth += r.aim*n })
    product = produceAnswer(result)

    println("Product (total fwd) * (total depth): $product")
    exit("$DAY Part $part1_2 - Completed")
}