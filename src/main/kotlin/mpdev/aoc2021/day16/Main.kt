package mpdev.aoc2021.day16

import java.util.BitSet

lateinit var inputBitSet: BitSet
var xSize = 0
var ySize = 0
var part1_2 = 1
var result: ULong = 0u

/** calculate */
fun calculateResult(myInput: BitSet): ULong {
    val p = PacketHandler(myInput)
    val myPacket = p.readMessage()
    val proc = PacketProcessor
    return if (part1_2 == 1)
        proc.getSumOfVersions(myPacket).toULong()
    else
        proc.processPacket(myPacket)
}

/** main */
fun main(args: Array<String>) {

    part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    inputBitSet = getInput(args)
    val t1 = System.currentTimeMillis()

    result = calculateResult(inputBitSet)

    println("$RESULT_STRING1 - part $part1_2: $result")

    val t2 = System.currentTimeMillis()
    exit("$DAY Part $part1_2 - Completed in ${t2-t1} msec")
}
