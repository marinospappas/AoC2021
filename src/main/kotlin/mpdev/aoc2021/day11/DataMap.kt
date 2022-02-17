package mpdev.aoc2021.day11

class DataPoint (var value: Int, var flag: Boolean = false)

class DataMap(var dataMap: MutableList<MutableList<DataPoint>> = mutableListOf(), var xSize: Int = 0, var ySize: Int = 0) {

    class CoOrds(var x:Int, var y:Int) {
        override fun toString() = "($x,$y)"
    }

    override fun toString(): String {
        var s = ""
        for (i in dataMap.indices) {
            if (i > 0)
                s+= "\n"
            dataMap[i].forEach { s += it.value.toString() }
        }
        return s
    }

    /** find neighbor points coordinates */
    fun getNeighbours(c: CoOrds): List<CoOrds> {
        val neighbours = mutableListOf<CoOrds>()
        if (c.x > 0)    // to the left
            neighbours.add(CoOrds(c.x-1, c.y))
        if (c.x < xSize-1)    // to the right
            neighbours.add(CoOrds(c.x+1, c.y))
        if (c.y > 0)    // above
            neighbours.add(CoOrds(c.x, c.y-1))
        if (c.y < ySize-1)    // below
            neighbours.add(CoOrds(c.x, c.y+1))
        if (c.x > 0 && c.y > 0)    // to the left and above
            neighbours.add(CoOrds(c.x-1, c.y-1))
        if (c.x < xSize - 1 && c.y > 0)    // to the right and above
            neighbours.add(CoOrds(c.x+1, c.y-1))
        if (c.x > 0 && c.y < ySize - 1)    // to the left and below
            neighbours.add(CoOrds(c.x-1, c.y+1))
        if (c.x < xSize-1 && c.y < ySize - 1)    // to the right and below
            neighbours.add(CoOrds(c.x+1, c.y+1))
        return neighbours
    }

    fun resetAllFlags() {
        for (y in dataMap.indices)
            for (x in dataMap[y].indices)
                dataMap[y][x].flag = false
    }

    fun processDataMapCycle() {
        resetAllFlags()
        for (y in dataMap.indices)
            for (x in dataMap[y].indices)
                processOnePoint(CoOrds(x,y))
    }

    private fun processOnePoint(c: CoOrds) {
        if (!dataMap[c.y][c.x].flag)
            ++dataMap[c.y][c.x].value
        if (dataMap[c.y][c.x].value > 9 && !dataMap[c.y][c.x].flag) {
            dataMap[c.y][c.x].flag = true
            getNeighbours(c).forEach { processOnePoint(it) }
            dataMap[c.y][c.x].value = 0
        }
    }

    fun getFlagsCount(): Int {
        var count = 0
        for (line in dataMap)
            count += line.count { it.flag }
        return count
    }

    fun allFLagsAreOn(): Boolean = getFlagsCount() == xSize * ySize
}