package mpdev.aoc2021.day2

import java.io.File
import java.lang.System.err
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
    val tokens = cmdString.split(" ")
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
        if (args[0].startsWith("-"))
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
fun processCommandsPart1(cmdList: List<CmdObj>): Result {
    val result = Result()
    cmdList.forEach {
        when (it.cmd) {
            Cmd.fwd -> result.totalFwd += it.arg
            Cmd.down -> result.totalDepth += it.arg
            Cmd.up -> result.totalDepth -= it.arg
            else -> {}
        }
    }
    return result
}

/** process commands part 2 */
fun processCommandsPart2(cmdList: List<CmdObj>): Result {
    val result = Result()
    cmdList.forEach {
        when (it.cmd) {
            Cmd.fwd -> {
                result.totalFwd += it.arg
                result.totalDepth += (result.aim * it.arg)
            }
            Cmd.down -> result.aim += it.arg
            Cmd.up -> result.aim -= it.arg
            else -> {}
        }
    }
    return result
}

/** produce answer */
fun produceAnswer(result: Result) = result.totalDepth * result.totalFwd

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    commandData = getInput(args)
    if (part1_2 == 1)
        result = processCommandsPart1(commandData)
    else
        result = processCommandsPart2(commandData)
    product = produceAnswer(result)

    println("Product (total fwd) * (total depth): $product")
    exit("$DAY Part $part1_2 - Completed")
}