package mpdev.aoc2021.day18

class Input {
    /////////////// scan string to produce tree
    var indx = 0
    var nextChar = ' '
    val zero = 0
    val endOfString = zero.toChar()

    init {
        indx = 0
        nextChar = ' '
    }

    private fun getNextChar(s: String): Char {
        return if (indx >= s.length)
            endOfString
        else
            s[indx++]
    }

    private fun match(s: String, c: Char) {
        val next = getNextChar(s)
        if (next == c)
            return
        else
            abort("scan input: expected $c found $next")
    }

    fun scan(s: String): Node {
        match(s, '[')
        val node = scanNode(s)
        match(s, ']')
        return node
    }

    private fun scanNode(s: String): Node {
        var left: Any? = null
        var right: Any? = null
        var c = getNextChar(s)
        if (c == '[') {
            left = scanNode(s)
            match(s, ']')
        }
        else if (c in '0'..'9')
            left = c.digitToInt()
        match(s,',')
        c = getNextChar(s)
        if (c == '[') {
            right = scanNode(s)
            match(s, ']')
        }
        else if (c in '0'..'9')
            right = c.digitToInt()
        return Node(left, right)

    }
}