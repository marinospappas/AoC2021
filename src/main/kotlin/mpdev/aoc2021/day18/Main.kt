package mpdev.aoc2021.day18

lateinit var input: List<String>
var xSize = 0
var ySize = 0
var part1_2 = 1
var result = 0

/** calculate the tree */
fun processInput(myInput: List<String>): Node {
    var tree = Node(null,null)
    myInput.forEach { line ->
        tree = tree.add(Input().scan(line))
        if (tree.left != null && tree.right != null) {
            while (tree.depth() >= 4)
                tree = tree.explode().split()
        }
    }
    return tree
}

fun calculateResult(myInput: List<String>): Int {
    val myTree = processInput(myInput)
    return myTree.magnitude()
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    input = getInput(args)
    val t1 = System.currentTimeMillis()

    result = calculateResult(input)

    println("$RESULT_STRING1 - part $part1_2: $result")

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}
