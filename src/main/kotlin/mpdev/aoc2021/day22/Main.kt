package mpdev.aoc2021.day22

import kotlin.system.measureTimeMillis

lateinit var input: List<Cuboid>
var part1_2 = 1
var result = -1L

/** process each line of input and count entries requested */
fun calculateTotal(listOfCuboids: List<Cuboid>): Long {
    is3D = true
    val reactor = Reactor()
    for (cube in listOfCuboids) {
        if (part1_2 == 1 &&
            (cube.r.x1 < -50 || cube.r.x2 > 50 || cube.r.y1 < -50 || cube.r.y2 > 50 || cube.r.z1 < -50 || cube.r.z2 > 50)
        ) {
            println("executed ${listOfCuboids.indexOf(cube)} steps")
            break
        }
        reactor.processCuboid(cube)
    }
    println("total number of steps: ${listOfCuboids.size}")
    return reactor.numberOfCubes()
}


/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    input = getInput(args)
    elapsedTime = measureTimeMillis { result = calculateTotal(input) }
    println("$RESULT_STRING1: $result")

    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}