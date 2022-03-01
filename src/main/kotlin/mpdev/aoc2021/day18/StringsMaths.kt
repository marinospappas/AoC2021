package mpdev.aoc2021.day18

const val LEFT_BRACKET = 0
const val RIGHT_BRACKET = 1
const val COMMA = 2
const val NUMBER = 3

class Token (var type: Int, var value: Int = -1)

object StringsMaths {

    private var inputString: String = ""
    private var indx: Int = 0
    private var nextChar: Char = ' '
    private val zero = 0
    private val endOfString = zero.toChar()

    fun tokenise(s: String): List<Token> {
        val tokenList = mutableListOf<Token>()
        if (s.isEmpty())
            return tokenList
        inputString = s
        indx = 0
        var c = getNextChar()
        while (c != endOfString) {
            when (c) {
                '[' -> tokenList.add(Token(LEFT_BRACKET))
                ']' -> tokenList.add(Token(RIGHT_BRACKET))
                ',' -> tokenList.add(Token(COMMA))
                else -> tokenList.add(Token(NUMBER, getNumber()))
            }
            c = getNextChar()
        }
        return tokenList
    }

    fun add(l1: List<Token>, l2: List<Token>): List<Token> {
        val result = mutableListOf<Token>()
        result.addAll(l1)
        result.addAll(l2)
        return result
    }

    fun explode(l: List<Token>): List<Token> {
        val result = mutableListOf<Token>()
        result.addAll(l)
        val listDepth = depth(result)
        //while (listDepth >= 4) {
            var thisDepth = -1
            var i = -1
            while (++i < result.size) {
                if (result[i].type == LEFT_BRACKET)
                    ++thisDepth
                if (result[i].type == RIGHT_BRACKET)
                    --thisDepth
                if (thisDepth == listDepth) {
                    val left: Int = result[i+1].value
                    val right: Int = result[i+3].value
                    var point = i+1
                    result[point].type = NUMBER
                    result[point].value = 0
                    result.removeAt(point+2)
                    result.removeAt(point+1)
                    ++i
                    --thisDepth
                    result.removeAt(i + 1)
                    result.removeAt(i - 1)
                }
            }
        //}
        return result
    }

    fun depth(l: List<Token>): Int {
        var thisDepth = -1
        var maxDepth = Integer.MIN_VALUE
        l.forEach { token ->
            if (token.type == LEFT_BRACKET)
                ++thisDepth
            if (token.type == RIGHT_BRACKET)
                --thisDepth
            if (thisDepth > maxDepth)
                maxDepth = thisDepth
        }
        return maxDepth
    }

    fun toString(tokenList: List<Token>): String {
        var s = ""
        tokenList.forEach { token ->
            when (token.type) {
                LEFT_BRACKET -> s += "["
                RIGHT_BRACKET -> s += "]"
                COMMA -> s += ","
                else -> s += token.value
            }
        }
        return s
    }

    private fun getNextChar(): Char {
        if (indx >= inputString.length)
            nextChar = endOfString
        else
            nextChar =  inputString[indx++]
        return nextChar
    }

    private fun getNumber(): Int {
        return nextChar.digitToInt()
    }
}