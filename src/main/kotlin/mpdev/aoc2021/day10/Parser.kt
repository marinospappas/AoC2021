package mpdev.aoc2021.day10

class Parser(var input: String) {

    val char0 = 0
    val charAny = 255
    val endOfInput = char0.toChar()
    val anyChar = charAny.toChar()

    var nextChar = ' '
    var indx = 0

    init {
        indx = 0
        advanceToNextChar()
    }

    // scanner functions
    private fun match(c: Char = anyChar): Char {
        if (c != nextChar && c != anyChar) {
            val err = when(nextChar) {
                ')' -> Error.illegParen
                ']' -> Error.illegSq
                '}' -> Error.illegCurl
                '>' -> Error.illegTr
                endOfInput -> Error.incomplete
                else -> Error.other
            }
            throw ParserException("found illegal '$nextChar'", err, errScore[err]?:0)
        }
        val thisChar = nextChar
        advanceToNextChar()
        return thisChar
    }

    private fun lookahead() = nextChar

    private fun advanceToNextChar() {
        if (indx < input.length)
            nextChar = input[indx++]
        else
            nextChar = endOfInput
    }

    private fun isBlockStart() = "([{<".contains(nextChar)

    // parser functions
    var autoCompleteString = ""

    fun getAutoCompleteScore(): Long {
        var score = 0L
        autoCompleteString.forEach {
            val thisCharScore = when(it) {
                ')' -> 1L
                ']' -> 2L
                '}' -> 3L
                '>' -> 4L
                else -> 0L
            }
            score = score * 5L + thisCharScore
        }
        return score
    }

    fun parseBlock(autoComplete: Boolean = false) {
        var endChar = endOfInput
        while (isBlockStart()) {
            when (lookahead()) {
                '(' -> endChar = ')'
                '[' -> endChar = ']'
                '{' -> endChar = '}'
                '<' -> endChar = '>'
                else -> match()
            }
            if (!autoComplete)
                parseBracketBlockWError(endChar)
            else
                parseBracketBlockWAutoComplete(endChar)
        }
    }

    private fun parseBracketBlockWError(endChar: Char) {
        match()
        parseBlock()
        match(endChar)
    }

    private fun parseBracketBlockWAutoComplete(endChar: Char) {
        match()
        parseBlock(true)
        try {
            match(endChar)
        }
        catch (e: ParserException) {
            if (e.error == Error.incomplete) {
                autoCompleteString += endChar
                input += endChar.toString()
            }
            else
                throw e
        }
    }

}