package mpdev.aoc2021.day23

import kotlin.system.measureTimeMillis

lateinit var input: String
var part1_2 = 1
var result = -1

/** process each line of input and count entries requested */
fun calculateMinEnergy(myInput: String): Int {
    val algorithm: String
    val graph = Graph(myInput)
    //val minPath =  Dijkstra<String>().runIt(graph, Graph(Graph.endState)); algorithm = "Dijkstra"
    val minPath = AStar<String>().runIt(graph, Graph(Graph.endState)); algorithm = "A*"
    println("min cost path: ${minPath.path}")
    var i = 0
    minPath.path.forEach { println("\nstep ${i++}\n${Graph(it)}") }
    println("$algorithm number of iterations: ${minPath.numberOfIterations}")
    return minPath.minCost
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val elapsedTime: Long
    input = getInput(args)
    elapsedTime = measureTimeMillis { result = calculateMinEnergy(input) }

    println("$RESULT_STRING1: $result")

    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}