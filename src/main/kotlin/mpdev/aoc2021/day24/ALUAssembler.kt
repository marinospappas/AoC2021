package mpdev.aoc2021.day24

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintStream

class ALUAssembler(var inputPrg: List<String>, outFile: String = ""): ALU {

    private var outStream: PrintStream = System.out
    init {
        if (outFile != "") {
            try {
                outStream = PrintStream(File(outFile))
            } catch (e: Exception) {
                System.err.println("could not create output file - $e")
                System.err.println("output code will be sent to stdout")
            }
        }
    }

    override val variables = mutableMapOf<String,Int>()
    override var inputValues = ""
    override var inputIndex = 0
    override fun compileProgram() { /* dummy */ }

    override fun runProgram(): Int {return -1}
    override fun runProgram(start: Int, end: Int): Int {return -1}

    fun asm() {
        outStream.println(".text\n")
        outStream.println(".global alu_start\n")
        outStream.println("alu_start:")
        outStream.println("\txorq %r8,%r8")
        outStream.println("\txorq %r9,%r9")
        outStream.println("\txorq %r10,%r10")
        outStream.println("\txorq %r11,%r11")
        inputPrg.forEach { line -> parseLine(line) }
        outStream.println("\n\tmovq %r11,%rax")
        outStream.println("\tret")
    }

    fun execPrgm(): String {
        val pb = ProcessBuilder("/home/marinos/asm/aoc2021/aluexec")
        val proc = pb.start()
        val reader = BufferedReader(InputStreamReader(proc.inputStream))
        val z = reader.readLine()
        return z
    }

    fun perfTest(repeat: Int): String {
        val pb = ProcessBuilder("/home/marinos/asm/aoc2021/aluexec_loop", repeat.toString())
        val proc = pb.start()
        val reader = BufferedReader(InputStreamReader(proc.inputStream))
        val z = reader.readLine()
        return z
    }

    fun parseLine(line: String) {
        val tokens = line.split(" ")
        when (tokens[0]) {
            "inp" -> parseInp(tokens[1])
            "add" -> parseAdd(tokens[1], tokens[2])
            "mul" -> parseMul(tokens[1], tokens[2])
            "div" -> parseDiv(tokens[1], tokens[2])
            "mod" -> parseMod(tokens[1], tokens[2])
            "eql" -> parseEql(tokens[1], tokens[2])
            else -> throw ALUCompilerException("invalid command ${tokens[0]}")
        }
    }

    fun parseInp(arg1: String) {
        ++inputIndex
        val l1 = "aluinput_" + "%02d".format(inputIndex)
        if (inputIndex == 9) {
            outStream.println("\n\tmovq %r11,%rax")
            outStream.println("\tret\t\t# end of first part of program (z must be 0 here)")
            outStream.println("\n.global $l1\t\t# start of second part of program\n")
        }
        outStream.println("\n$l1:")
        outStream.println("\txorq %rax,%rax")
        outStream.println("\tmovb (%rdi),%al")
        var cmd = "\tmovq %rax,${regFromVar(arg1)}"
        outStream.println(cmd)
        cmd = "\tinc %rdi"
        outStream.println(cmd)
    }

    fun parseAdd(arg1: String, arg2: String) {
        var cmd = "\taddq "
        if (arg2[0].isLetter())
            cmd += "${regFromVar(arg2)},${regFromVar(arg1)}"
        else
            cmd += "$${arg2},${regFromVar(arg1)}"
        outStream.println(cmd)
    }

    fun parseMul(arg1: String, arg2: String) {
        var cmd: String
        if (arg2 == "0") {
            cmd = "\txorq ${regFromVar(arg1)},${regFromVar(arg1)}"
            outStream.println(cmd)
            return
        }
        cmd = "\timulq "
        if (arg2[0].isLetter())
            cmd += "${regFromVar(arg2)},${regFromVar(arg1)}"
        else
            cmd += "$${arg2},${regFromVar(arg1)}"
        outStream.println(cmd)
    }

    fun parseDiv(arg1: String, arg2: String) {
        if (arg2 == "1")
            return
        var cmd = "\tmovq "
        if (arg2[0].isLetter())
            cmd += regFromVar(arg2)
        else
            cmd += "$${arg2}"
        cmd += ",%rbx"
        outStream.println(cmd)
        cmd = "\txorq %rdx,%rdx"
        outStream.println(cmd)
        cmd = "\tmovq ${regFromVar(arg1)},%rax"
        outStream.println(cmd)
        cmd = "\tidivq %rbx"
        outStream.println(cmd)
        cmd = "\tmovq %rax,${regFromVar(arg1)}"
        outStream.println(cmd)
    }

    fun parseMod(arg1: String, arg2: String) {
        var cmd = "\tmovq "
        if (arg2[0].isLetter())
            cmd += regFromVar(arg2)
        else
            cmd += "$${arg2}"
        cmd += ",%rbx"
        outStream.println(cmd)
        cmd = "\txorq %rdx,%rdx"
        outStream.println(cmd)
        cmd = "\tmovq ${regFromVar(arg1)},%rax"
        outStream.println(cmd)
        cmd = "\tidivq %rbx"
        outStream.println(cmd)
        cmd = "\tmovq %rdx,${regFromVar(arg1)}"
        outStream.println(cmd)
    }

    fun parseEql(arg1: String, arg2: String) {
        var cmd = "\tmovq $1,%rax"
        outStream.println(cmd)
        cmd = "\tcmpq "
        if (arg2[0].isLetter())
            cmd += regFromVar(arg2)
        else
            cmd += "$${arg2}"
        cmd += ",${regFromVar(arg1)}"
        outStream.println(cmd)
        val l1 = newLabel()
        cmd = "\tje $l1"
        outStream.println(cmd)
        cmd = "\txorq %rax,%rax"
        outStream.println(cmd)
        outStream.println("$l1:")
        cmd = "\tmovq %rax,${regFromVar(arg1)}"
        outStream.println(cmd)

    }

    fun regFromVar(varName: String): String {
        return when(varName) {
            "w" -> "%r8"
            "x" -> "%r9"
            "y" -> "%r10"
            "z" -> "%r11"
            else -> "invalid variable"
        }
    }

    var labelIndx = 0
    fun newLabel() = "alulbl_" + "%03d".format(labelIndx++)
}
