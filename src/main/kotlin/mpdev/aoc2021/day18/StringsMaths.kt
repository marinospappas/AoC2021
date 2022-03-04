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
        getNextChar()
        while (nextChar != endOfString) {
            when (nextChar) {
                '[' -> { tokenList.add(Token(LEFT_BRACKET)); getNextChar() }
                ']' -> { tokenList.add(Token(RIGHT_BRACKET)); getNextChar() }
                ',' ->  { tokenList.add(Token(COMMA)); getNextChar() }
                else -> tokenList.add(Token(NUMBER, getNumber()))
            }
        }
        return tokenList
    }

    fun add(l1: List<Token>, l2: List<Token>): List<Token> {
        return listOf(Token(LEFT_BRACKET)) + l1 + listOf(Token(COMMA)) + l2 + listOf(Token(RIGHT_BRACKET))
    }

    fun reduce(l: List<Token>): List<Token> {
        var tokenList = l
        while (mustExplode(tokenList) || mustSplit(tokenList)) {
            while (mustExplode(tokenList))
                tokenList = explode(tokenList)
            if (mustSplit(tokenList))
                tokenList = split(tokenList)
        }
        return tokenList
    }

    private fun mustExplode(l: List<Token>): Boolean = depth(l) >= 4

    private fun mustSplit(l: List<Token>) = l.any { it.type == NUMBER && it.value > 9 }

    fun explode(l: List<Token>): List<Token> {
        var result = listOf<Token>()
        var thisDepth = -1
        for (i in l.indices) {
            when (l[i].type) {
                LEFT_BRACKET -> ++thisDepth
                RIGHT_BRACKET -> --thisDepth
            }
            if (thisDepth >= 4 && l[i + 1].type == NUMBER && l[i+3].type == NUMBER) {
                val leftValue: Int = l[i + 1].value
                val rightValue: Int = l[i + 3].value
                val leftPart = l.subList(0,i)
                val rightPart = l.subList(i+5, l.size)
                if(leftPart.any { it.type == NUMBER })
                    leftPart.last { it.type == NUMBER }.value += leftValue
                if (rightPart.any { it.type == NUMBER })
                    rightPart.first { it.type == NUMBER }.value += rightValue
                result = leftPart + listOf(Token(NUMBER,0)) + rightPart
                break
            }
        }
        return result
    }

    fun split(l: List<Token>): List<Token> {
        val result = mutableListOf<Token>()
        result.addAll(l)
        var i = -1
        while (++i < result.size) {
            if (result[i].type == NUMBER && result[i].value > 9) {
                val left = result[i].value / 2
                val right = result[i].value / 2 + result[i].value % 2
                result[i] = Token(LEFT_BRACKET)
                result.add(++i, Token(NUMBER,left))
                result.add(++i, Token(COMMA))
                result.add(++i, Token(NUMBER,right))
                result.add(++i, Token(RIGHT_BRACKET))
                return result
            }
        }
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

    fun magnitude(l: List<Token>): Int {
        val leftPart = left(l)
        val rightPart = right(l)
        val magnLeft: Int
        val magnRight: Int
        if (leftPart is Int)
            magnLeft = 3 * leftPart
        else
        if (leftPart is List<*>)
            magnLeft = 3 * magnitude(leftPart as List<Token>)
        else
            magnLeft = -1
        if (rightPart is Int)
            magnRight = 2 * rightPart
        else
            if (rightPart is List<*>)
                magnRight = 2 * magnitude(rightPart as List<Token>)
            else
                magnRight = -1
        return magnLeft + magnRight
    }

    private fun left(l: List<Token>): Any {
        var thisDepth = -1
        for (i in l.indices) {
            if (l[i].type == LEFT_BRACKET)
                ++thisDepth
            if (l[i].type == RIGHT_BRACKET)
                --thisDepth
            if (l[i].type == COMMA && thisDepth == 0) {
                if (l[i-1].type == NUMBER)
                    return l[i-1].value
                else
                    return l.subList(1,i)
            }
        }
        return -1
    }

    private fun right(l: List<Token>): Any {
        var thisDepth = -1
        for (i in l.indices) {
            if (l[i].type == LEFT_BRACKET)
                ++thisDepth
            if (l[i].type == RIGHT_BRACKET)
                --thisDepth
            if (l[i].type == COMMA && thisDepth == 0) {
                if (l[i+1].type == NUMBER)
                    return l[i+1].value
                else
                    return l.subList(i+1,l.size-1)
            }
        }
        return -1
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
        var number = nextChar.digitToInt()
        var c = getNextChar()
        while (c in '0'..'9') {
            number = number * 10 + c.digitToInt()
            c = getNextChar()
        }
        return number
    }

}