package mpdev.aoc2021.day19

import kotlin.system.measureTimeMillis

lateinit var input: List<Scanner>
var part1_2 = 1
var result = listOf<Int>()

fun <T> MutableList<T>.put(o: T) = this.add(0,o)

fun <T> MutableList<T>.get(): T {
    val temp = this.last()
    this.removeLast()
    return temp
}

/** process each line of input and count entries requested */
fun calculatePart1And2(listOfScanners: List<Scanner>): List<Int> {
    var total = 0
    val myList = mutableListOf<Scanner>()
    val fifo = mutableListOf<Scanner>()
    myList.addAll(listOfScanners)
    val scanner0 = myList[0]
    fifo.put(scanner0)
    scanner0.updBcnCoord()
    scanner0.inPosition = true
    println("scanner ${scanner0.id} position: ${scanner0.position}")
    while (fifo.isNotEmpty() && myList.count { it.inPosition } < myList.size) {
        val refScanner = fifo.get()
        for (scanner in myList.filter { !it.inPosition }) {
            //println("trying scanner ${refScanner.id} with scanner ${scanner.id}")
            if (refScanner.overlaps(scanner)) {
                //print("scanner       ")
                //myList.forEach { print("%3d".format(it.id)) }
                //println()
                //print("overlaps with ")
                //myList.forEach { print("%3s".format(if (it.overlappedBy>=0) it.overlappedBy else "")) }
                //println()
                fifo.put(scanner)
                scanner.inPosition = true
            }
        }
    }
    if (myList.count { it.inPosition } < myList.size) {
        println("NO solution")
        println("unsolved scanners:")
        myList.filter { !it.inPosition } .forEach { print("${it.id} ") }
        println()
    }
    else
        total = myList.beaconCount()
    return listOf(total,myList.maxManhDist())
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    elapsedTime = measureTimeMillis {
        input = getInput(args)
        result = calculatePart1And2(input)
    }

    println("$RESULT_STRING1: ${result[0]} $RESULT_STRING2 ${result[1]}")
    exit("$DAY Part Completed in $elapsedTime msec")
}