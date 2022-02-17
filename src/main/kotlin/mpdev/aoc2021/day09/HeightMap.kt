package mpdev.aoc2021.day09

class HeightMap(var depthMap: List<List<Int>> = listOf(), var xSize: Int = 0, var ySize: Int = 0) {

    class CoOrds(var x:Int, var y:Int) {
        override fun toString() = "($x,$y)"
    }

    override fun toString(): String {
        var s = ""
        for (i in depthMap.indices) {
            if (i > 0)
                s+= "\n"
            depthMap[i].forEach { s += it.toString() }
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
        return neighbours
    }

    /** check if a point is deeper that it's neighbours */
    private fun isDeepest(c: CoOrds): Boolean = getNeighbours(c).filter { depthMap[c.y][c.x] >= depthMap[it.y][it.x] } .isEmpty()

    var riskLevel: Int = 0

    /** find number of low points */
    fun getLowPoints(): Int {
        var count = 0
        riskLevel = 0
        for (i in depthMap.indices)
            for (j in depthMap[i].indices)
                if (isDeepest(CoOrds(j, i))) {
                    ++count
                    riskLevel += depthMap[i][j]+1
                }
        return count
    }

    /** get the basins */
    class Basin(var points: MutableList<CoOrds> = mutableListOf()) {
        fun doesNotContain(c: CoOrds) = points.none { it.x == c.x && it.y == c.y }
    }

    lateinit var basins: MutableList<Basin>

    fun getBasins() {
        basins = mutableListOf()
        for (y in depthMap.indices)
            for (x in depthMap[y].indices)
                if (isDeepest(CoOrds(x, y))) {
                    // deepest point = new basin
                    val b = Basin()
                    b.points.add(CoOrds(x,y))
                    checkBasinNeighbours(b, CoOrds(x,y))
                    basins.add(b)
                }
        basins.sortBy { it.points.size }
        basins = basins.reversed() as MutableList<Basin>
    }

    private fun checkBasinNeighbours(b: Basin, c: CoOrds) {
        val neighbours = getNeighbours(c)
        for (nc in neighbours) {
            if (depthMap[nc.y][nc.x] > depthMap[c.y][c.x]) {
                if (b.doesNotContain(nc) && depthMap[nc.y][nc.x] < 9)
                    b.points.add(nc)
                checkBasinNeighbours(b, nc)
            }
        }
    }
}