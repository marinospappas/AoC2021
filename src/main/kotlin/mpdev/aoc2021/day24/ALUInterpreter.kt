package mpdev.aoc2021.day24

class ALUInterpreter(var inputPrg: List<String>): ALU {

    override val variables = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0)
    override var inputValues = ""
    override var inputIndex = 0
    override fun compileProgram() { /* dummy */ }

    override fun runProgram() = runProgram(0, inputPrg.size-1)

    override fun runProgram(start: Int, end: Int): Int {
        if (start == 0) {
            variables["w"] = 0
            variables["x"] = 0
            variables["y"] = 0
            variables["z"] = 0
        }
        inputIndex = start / 18
        for (instrPointer in start..end)
            executeLine(inputPrg[instrPointer])
        return variables["z"]!!
    }

    fun executeLine(line: String) {
        val tokens = line.split(" ")
        when (tokens[0]) {
            "inp" -> executeInp(tokens[1])
            "add" -> executeAdd(tokens[1], tokens[2])
            "mul" -> executeMul(tokens[1], tokens[2])
            "div" -> executeDiv(tokens[1], tokens[2])
            "mod" -> executeMod(tokens[1], tokens[2])
            "eql" -> executeEql(tokens[1], tokens[2])
            else -> throw ALUCompilerException("invalid command $tokens[0]")
        }
    }

    fun executeInp(arg1: String) {
        variables[arg1] = getNextInput()
    }

    fun getNextInput(): Int {
        if (inputIndex >= inputValues.length) throw ALURuntimeException("No more input - index: $inputIndex")
        return inputValues[inputIndex++].digitToInt()
    }

    fun executeAdd(arg1: String, arg2: String) {
        if (arg2 in variables.keys)
            variables[arg1] = variables[arg1]!! + variables[arg2]!!
        else
            variables[arg1] = variables[arg1]!! + arg2.toInt()
    }

    fun executeMul(arg1: String, arg2: String) {
        if (arg2 in variables.keys)
            variables[arg1] = variables[arg1]!! * variables[arg2]!!
        else
            variables[arg1] = variables[arg1]!! * arg2.toInt()
    }

    fun executeDiv(arg1: String, arg2: String) {
        if (arg2 in variables.keys) {
            if (variables[arg2] == 0) throw ALURuntimeException("division by 0")
            variables[arg1] = variables[arg1]!! / variables[arg2]!!
        }
        else {
            if (arg2 == "0") throw ALURuntimeException("division by 0")
            variables[arg1] = variables[arg1]!! / arg2.toInt()
        }
    }

    fun executeMod(arg1: String, arg2: String) {
        if (arg2 in variables.keys) {
            if (variables[arg1]!! < 0 || variables[arg2]!! <= 0)
                throw ALURuntimeException("invalid mod operation: ${variables[arg1]} mod ${variables[arg2]}")
            variables[arg1] = variables[arg1]!! % variables[arg2]!!
        }
        else {
            if (variables[arg1]!! < 0 || arg2.toInt() <= 0)
                throw ALURuntimeException("invalid mod operation: ${variables[arg1]} mod $arg2")
            variables[arg1] = variables[arg1]!! % arg2.toInt()
        }
    }

    fun executeEql(arg1: String, arg2: String) {
        if (arg2 in variables.keys)
            variables[arg1] = if (variables[arg1]!! == variables[arg2]!!) 1 else 0
        else
            variables[arg1] = if (variables[arg1]!! == arg2.toInt()) 1 else 0
    }

}
