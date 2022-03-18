package mpdev.aoc2021.day24

class ALUCompilerException(override var message: String): Exception(message)

class ALURuntimeException(override var message: String): Exception(message)

class ALUStep(var step: (String, String) -> Unit, var arg1: String, var arg2: String = "", var srcInst: String) {
    fun runStep() {
        step(arg1, arg2)
    }
}

fun startSub(subroutineId: Int) = 18 * (subroutineId - 1)
fun endSub(subroutineId: Int) = 18 * subroutineId - 1

val compiledProgram = mutableListOf<String>()

class ALUCompiler(var inputPrg: List<String>): ALU {

    var execPrg = mutableListOf<ALUStep>()
    override val variables = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0)
    override var inputValues = ""
    override var inputIndex = 0

    override fun compileProgram() {
        execPrg = mutableListOf()
        inputPrg.forEach { execPrg.add(parseLine(it)) }
    }

    override fun runProgram() = runProgram(0, execPrg.size-1)

    override fun runProgram(start: Int, end: Int): Int {
        if (start == 0) {
            variables["w"] = 0
            variables["x"] = 0
            variables["y"] = 0
            variables["z"] = 0
        }
        inputIndex = start / 18
        for (instrPointer in start..end)
            execPrg[instrPointer].runStep()
        return variables["z"]!!
    }

    /** compile functions here */
    fun parseLine(line: String): ALUStep {
        val tokens = line.split(" ")
        return when (tokens[0]) {
            "inp" -> parseInp(tokens[1], line)
            "add" -> parseAdd(tokens[1], tokens[2], line)
            "mul" -> parseMul(tokens[1], tokens[2], line)
            "div" -> parseDiv(tokens[1], tokens[2], line)
            "mod" -> parseMod(tokens[1], tokens[2], line)
            "eql" -> parseEql(tokens[1], tokens[2], line)
            else -> throw ALUCompilerException("invalid command $tokens[0]")
        }
    }

    fun parseInp(arg1: String, srcInst: String): ALUStep {
        compiledProgram.add("///////////////////////////////////")
        compiledProgram.add("$arg1 = getNextInput()")
        return ALUStep({ name, _ -> variables[name] = getNextInput() }, arg1, srcInst = srcInst)
    }

    fun parseAdd(arg1: String, arg2: String, srcInst: String): ALUStep {
        compiledProgram.add("$arg1 += $arg2")
        return if (arg2 in variables.keys)
            ALUStep({ name1, name2 -> variables[name1] = variables[name1]!! + variables[name2]!! }, arg1, arg2, srcInst)
        else
            ALUStep({ name1, value -> variables[name1] = variables[name1]!! + value.toInt() }, arg1, arg2, srcInst)
    }

    fun parseMul(arg1: String, arg2: String, srcInst: String): ALUStep {
        if (arg2 == "0")
            compiledProgram.add("$arg1 = 0")
        else
            compiledProgram.add("$arg1 *= $arg2")
        return if (arg2 in variables.keys)
            ALUStep({ name1, name2 -> variables[name1] = variables[name1]!! * variables[name2]!! }, arg1, arg2, srcInst)
        else {
            if (arg2 == "0")
                ALUStep({ name1, _ -> variables[name1] = 0 }, arg1, srcInst = srcInst)
            else
                ALUStep({ name1, value -> variables[name1] = variables[name1]!! * value.toInt() }, arg1, arg2, srcInst)
        }
    }

    fun parseDiv(arg1: String, arg2: String, srcInst: String): ALUStep {
        compiledProgram.add("$arg1 /= $arg2")
        return if (arg2 in variables.keys)
            ALUStep({ name1, name2 ->
                if (variables[name2] == 0) throw ALURuntimeException("division by 0")
                variables[name1] = variables[name1]!! / variables[name2]!!
            }, arg1, arg2, srcInst)
        else {
            if (arg2 == "1")
                ALUStep({ _, _ -> ; }, arg1, srcInst = srcInst)
            else
                ALUStep({ name1, value ->
                    if (value == "0") throw ALURuntimeException("division by 0")
                    variables[name1] = variables[name1]!! / value.toInt()
                }, arg1, arg2, srcInst)
        }
    }

    fun parseMod(arg1: String, arg2: String, srcInst: String): ALUStep {
        compiledProgram.add("$arg1 %= $arg2")
        return if (arg2 in variables.keys)
            ALUStep({ name1, name2 ->
                if (variables[name1]!! < 0 || variables[name2]!! <= 0)
                    throw ALURuntimeException("invalid mod operation: ${variables[name1]} mod ${variables[name2]}")
                variables[name1] = variables[name1]!! % variables[name2]!!
            }, arg1, arg2, srcInst)
        else
            ALUStep({ name1, value ->
                if (variables[name1]!! < 0 || value.toInt() <= 0)
                    throw ALURuntimeException("invalid mod operation: ${variables[name1]} mod $value")
                variables[name1] = variables[name1]!! % value.toInt()
            }, arg1, arg2, srcInst)
    }

    fun parseEql(arg1: String, arg2: String, srcInst: String): ALUStep {
        compiledProgram.add("$arg1 = if($arg1 == $arg2) 1 else 0")
        return if (arg2 in variables.keys)
            ALUStep({ name1, name2 ->
                        variables[name1] = if (variables[name1]!! == variables[name2]!!) 1 else 0
                    }, arg1, arg2, srcInst)
        else
            ALUStep({ name1, value ->
                        variables[name1] = if (variables[name1]!! == value.toInt()) 1 else 0
                    }, arg1, arg2, srcInst)
    }

    fun getNextInput(): Int {
        if (inputIndex >= inputValues.length) throw ALURuntimeException("No more input - index: $inputIndex")
        return inputValues[inputIndex++].digitToInt()
    }

    /** debug program */
    fun debugProgram(start: Int = 0, end: Int = execPrg.size-1) {
        variables["w"] = 0
        variables["x"] = 0
        variables["y"] = 0
        variables["z"] = 0
        inputIndex = 0
        for (instrPointer in start..end) {
            execPrg[instrPointer].runStep()
            println(
                "instr. $instrPointer: ${execPrg[instrPointer].srcInst}:\t\t" +
                        "output: w=${variables["w"]} x=${variables["x"]} y=${variables["y"]} z=${variables["z"]}"
            )
        }
        println("debugging completed")
    }
}