package mpdev.aoc2021.day24

interface ALU {
    val variables: MutableMap<String,Int>
    var inputValues: String
    var inputIndex: Int
    fun compileProgram()
    fun runProgram(): Int
    fun runProgram(start:Int, end:Int): Int
}
