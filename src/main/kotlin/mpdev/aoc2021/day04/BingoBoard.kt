package mpdev.aoc2021.day04

var boardSize = 0

class BoardCell(var number: Int = 0, var marked: Boolean = false)

class BingoBoard(var cells: MutableList<MutableList<BoardCell>> = mutableListOf(), var isWinner: Boolean = false) {

    /** check board against a number */
    fun markNumber(n: Int) {
        for (row in cells)
            for (cell in row)
                if (cell.number == n)
                    cell.marked = true
    }

    /** check if a row wins */
    fun rowWins(row: List<BoardCell>) = row.none { !it.marked }

    /** check if a column wins */
    fun colWins(i: Int): Boolean {
        for (row in cells)
            if (!row[i].marked)
                return false
        return true
    }

    /** check if the board wins */
    fun boardWins(): Boolean {
        for (row in cells)
            if (rowWins(row)) {
                isWinner = true
                return true
            }
        for (i in 0 until boardSize)
            if (colWins(i)) {
                isWinner = true
                return true
            }
        return false
    }

    /** calculate total unmarkes */
    fun calcTotal(): Int {
        var score = 0
        for (row in cells)
            for (cell in row)
                if (!cell.marked)
                    score += cell.number
        return score
    }

    /** toString functions mainly for testing */
    override fun toString(): String {
        var s = ""
        for (i in 0 until boardSize)
            s += rowToString(cells[i])
        return s
    }
    private fun rowToString(row: List<BoardCell>): String {
        var s = ""
        if (row.size != boardSize)
            return "\n"
        for (i in 0 until boardSize) {
            if (i > 0)
                s += " "
            s += "%2d".format(row[i].number)
        }
        return s+"\n"
    }

}
