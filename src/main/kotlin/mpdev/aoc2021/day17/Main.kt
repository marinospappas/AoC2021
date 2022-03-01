package mpdev.aoc2021.day17

import kotlin.math.max
import kotlin.math.sqrt

lateinit var inputData: Ballistics
var xSize = 0
var ySize = 0
var part1_2 = 1
var result = 0

/** calculate part 1 */
fun calculateResultPart1(bal: Ballistics): Int {
    val maxVy = -bal.y1 - 1
    val maxHeight = maxVy * (maxVy+1) / 2
    return maxHeight
}

/** calculate part 1 */
fun calculateResultPart2(bal: Ballistics): Int {
    val vYmin = bal.y1
    val vYmax = -bal.y1-1
    val vXmin = ((-1.0 + sqrt(1.0 + 8 * bal.x1)) / 2.0).toInt() + 1
    val vXmax = bal.x2
    println("vx: $vXmin .. $vXmax  vy: $vYmin .. $vYmax")
    val goodVList = mutableListOf<Velocity>()

    // loop through all good vx
    for (vx in vXmin..vXmax) {
        val t = bal.calcTForXandVx(bal.x1, vx)
        if (t < 0) {
            continue    // can't reach the target due to rounding errors
        }
        for (vy in vYmin..vYmax) {  // for each x loop through all good vy
            var t1 = t
            var y = bal.calcYforTandVy(t1, vy)
            var x: Int
            do {
                if (bal.y1 <= y && y <= bal.y2) {   // found a good pair vx,vy
                    goodVList.add(Velocity(vx, vy))
                    if (goodVList.size % 1000000 == 0)
                        println("vlist size: ${goodVList.size}")
                    break
                }
                // if this vy is not good in t steps, try one more step just in case
                ++t1
                y = bal.calcYforTandVy(t1, vy)
                x = bal.calcXforTandVx(t1, vx)
            } while (x <= bal.x2 && y >= bal.y1)    // until we are off target
        }
    }
    println(goodVList)
    return goodVList.count()
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    inputData = getInput()
    val t1 = System.currentTimeMillis()

    if (part1_2 == 1) {
        result = calculateResultPart1(inputData)
        println("$RESULT_STRING1 - part $part1_2: $result")
    }
    else {
        result = calculateResultPart2(inputData)
        println("$RESULT_STRING2 - part $part1_2: $result")
    }

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}
