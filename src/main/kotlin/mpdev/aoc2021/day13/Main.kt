package mpdev.aoc2021.day13

lateinit var inputData: Network
var part1_2 = 1
var result = -1

/** calculate part 1 */
fun calculateResultPart1(myNetworkMap: Network): Int {
    myNetworkMap.findPaths("start","end")
    return myNetworkMap.allPaths.size
}

/** calculate part */
fun calculateResultPart2(myNetworkMap: Network): Int {
    val listOfPaths: MutableList<List<String>> = mutableListOf()
    val minorNodesMap = myNetworkMap.netMap.map.filter { it.value.minor && it.key != "start" && it.key != "end" }
    minorNodesMap.forEach { (_, v) ->
        myNetworkMap.resetAllMinorNodes()
        v.maxTimesAllowed = 2
        myNetworkMap.findPaths("start","end")
        listOfPaths.addAll(myNetworkMap.allPaths)
    }
    return listOfPaths.distinct().size
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    inputData = getInput(args)
    val t1 = System.currentTimeMillis()

    if (part1_2 == 1)
        result = calculateResultPart1(inputData)
    else
        result = calculateResultPart2(inputData)
    println("$RESULT_STRING1 - part $part1_2: $result")

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}