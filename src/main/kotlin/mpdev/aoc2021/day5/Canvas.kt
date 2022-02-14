package mpdev.aoc2021.day5

import java.lang.StrictMath.*

class Point (var x: Int, var y: Int) {

    constructor(s: String) :
            this(s.split(",")[0].toInt(), s.split(",")[1].toInt())

    override fun toString() = "($x,$y)"
}

class Canvas (var xSize: Int = 0, var ySize: Int = 0) {

    var rows: MutableList<MutableList<Int>> = mutableListOf()

    init {
        for (i in 0 until ySize) {
            val row = MutableList(xSize) { 0 }
            rows.add(row)
        }
    }

    /** row to String */
    fun rowToString(row: List<Int>): String {
        var s = ""
        for (n in row) {
            s += if (n == 0) "." else n.toString()
        }
        return s
    }

    /** to string used for testing */
    override fun toString(): String {
        var s = ""
        for (i in rows.indices) {
            if (i > 0)
                s += "\n"
            s += rowToString(rows[i])
        }
        return s
    }

    /** extend the canvas */
    fun extend(x: Int = 0, y: Int = 0) {
        if (x > xSize) {
            rows.forEach {
                val extension = List(x-xSize) {0}
                it.addAll(extension)
            }
            xSize = x
        }
        if (y > ySize) {
            for (i in ySize until y) {
                val newRow = MutableList(max(x, xSize)) { 0 }
                rows.add(newRow)
            }
            ySize = y
        }
    }

    /** apply a line */
    fun applyLine(p1: Point, p2: Point) {
        if (p1.x < 0 || p1.x >= xSize || p1.y < 0 || p1.y >= ySize)
            abort("invalid line coordinates $p1")
        if (p2.x < 0 || p2.x >= xSize || p2.y < 0 || p2.y >= ySize)
            abort("invalid line coordinates $p2")
        val xStep = 1 * sign(p2.x - p1.x)
        val yStep = 1 * sign(p2.y - p1.y)
        var x = p1.x - xStep
        var y = p1.y - yStep
        do {
            x += xStep
            y += yStep
            ++rows[y][x]
        } while (x != p2.x || y != p2.y)
    }

    /** count points that fulfill certain criteria */
    fun countPoints(criteria: (Int) -> Boolean): Int {
        var count = 0
        for (r in rows)
            count += r.filter { criteria(it) } .count()
        return count
    }

    /** aux math and compare functions */
    private fun sign(n: Int): Int {
        if (n > 0)
            return 1
        else if (n < 0)
            return -1
        else
            return 0
    }
}