package mpdev.aoc2021.day13

/** the matrix */
class Matrix(var xSize: Int, var ySize: Int) {

    var grid: MutableList<MutableList<Int>> = mutableListOf()

    init {
        var line: Array<Int>
        for (i in 0 until ySize) {
            line = Array(xSize) {0}
            grid.add(line.toMutableList())
        }
    }

    override fun toString(): String {
        var s = ""
        for (j in grid.indices) {
            if (j > 0)
            s += "\n"
            grid[j].forEach {
                s += if (it == 0) "."
                else
                    "#"
            }
        }
        return s
    }

    fun toStringEnh(): String {
        var s = "   "
        for (i in 0 until xSize)
            s += (i % 10)
        for (j in grid.indices) {
            s += "\n" + "%2d".format(j) + " "
            grid[j].forEach {
                s += if (it == 0) " "
                else
                    "#"
            }
        }
        return s
    }

    /** set a "dot" */
    fun setDot(x: Int, y: Int) {
        grid[y][x] = 1
    }

    /** overlap one row over another */
    private fun overlapRow(y: Int, toLine: Int) {
        val rowFrom = grid[y]
        for (i in 0 until xSize)
            if (rowFrom[i] == 1)
                grid[toLine][i] = 1
    }

    /** overlap columns in a row from x onwards */
    fun overlapColumns(line: Int, x: Int) {
        val workingRow = grid[line]
        for (i in 1 until xSize-x)
            if (workingRow[x+i] == 1)
                workingRow[x-i] = 1
    }

    /** flip matrix horizontally at y */
    fun flipHor(y: Int) {
        if (y > ySize)
            return
        for (i in 1 until ySize-y)
            overlapRow(y + i, y - i)
        for (i in y until ySize)
            grid.removeLast()
        ySize = y
    }

    /** flip matrix vertically at x */
    fun flipVert(x: Int) {
        if (x >= xSize)
            return
        for (i in 0 until ySize)
            overlapColumns(i, x)
        for (i in 0 until ySize)
            for (j in x until xSize)
                grid[i].removeLast()
        xSize = x
    }

    /** count the "dots" */
    fun countDots(): Int {
        var count = 0
        for (i in grid.indices)
            count += grid[i].sum()
        return count
    }
}