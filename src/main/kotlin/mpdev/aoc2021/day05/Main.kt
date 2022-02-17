package mpdev.aoc2021.day05

import java.io.File
import java.lang.Math.abs
import java.lang.StrictMath.max

// the bingo boards list
var canvas = Canvas()
var result = -1

fun isHorizontal(y1: Int, y2: Int) = y1 == y2

fun isVertical(x1: Int, x2: Int) = x1 == x2

fun isDiagonal(x1:Int, y1:Int, x2:Int, y2:Int) = abs(x2-x1) == abs(y2-y1)

/** check coordinates to decide if they apply */
fun checkCoOrds(x1:Int, y1:Int, x2:Int, y2:Int, part2: Boolean) =
            isHorizontal(y1, y2) ||
            isVertical(x1, x2) ||
            (part2 && isDiagonal(x1, y1, x2, y2))

/** process each line of input and apply to canvas */
fun processInput(input: File?, c: Canvas, part2: Boolean) {
    input?.readLines()?.forEach {
        val coOrds = decodeInputLine(it)
        val p1 = coOrds[0]
        val p2 = coOrds[1]
        val maxX = max(p1.x, p2.x)
        val maxY = max(p1.y, p2.y)
        c.extend(maxX+1, maxY+1)
        if (checkCoOrds(p1.x, p1.y, p2.x, p2.y, part2))
            c.applyLine(p1, p2)
    }
}

/** calculate result part 1 */
fun calculateResult(c: Canvas): Int {
    return c.countPoints { d -> d >= 2 }
}

/** main */
fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val input = getInput(args)
    processInput(input, canvas, part1_2==2)
    result = calculateResult(canvas)

    println("$RESULT_STRING: $result")
    println("canvas size: ${canvas.xSize} x ${canvas.ySize}")
    exit("$DAY Part $part1_2 - Completed")
}