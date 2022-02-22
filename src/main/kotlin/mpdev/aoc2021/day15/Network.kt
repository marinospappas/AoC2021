package mpdev.aoc2021.day15

class Node(var name: String = "",
           var cost: Int,
           var connectedNodes: List<Int> = listOf(),
           var costToConnections: List<Int> = listOf(),
           var thisId: Int) {

    var lastUpdatedBy: Int = -1
    var dijkCost: Int = Int.MAX_VALUE

    override fun toString() = "$name cost $cost dijk cost $dijkCost last upd by $lastUpdatedBy"

}

class Network(var grid: MutableList<Node> = mutableListOf()) {

    override fun toString(): String {
        var s = ""
        for (i in grid.indices) {
            val node = grid[i]
            s += "\nnode id: $i $node"
            s += "\n    connected to "
            node.connectedNodes.forEach { conx -> s += "${getCoordsFromNodeId(conx)}, " }
        }
        return s.substring(1)
    }

    /** convert x,y coords to nodeId) */
    fun getNodeIdFromCoords(x: Int, y: Int): Int = xSize*y + x

    /** convert nodeid to coords x,y */
    fun getCoordsFromNodeId(id: Int): List<Int> {
        val coords = mutableListOf<Int>()
        coords.add(id % ySize)
        coords.add(id / ySize)
        return coords
    }

    /** add a node to the grid */
    fun addNode(name: String, cost: Int, connectedNodes: List<Int>, costToConnections: List<Int>, thisId: Int) {
        grid.add(Node(name, cost, connectedNodes, costToConnections, thisId))
    }

    /** get connected points to a specific point */
    fun getConnectedNodes(x: Int, y: Int): List<Int> {
        val nodesIdList = mutableListOf<Int>()
        if (x == (xSize-1) && y == (ySize-1))   // end node points to no other node
            return nodesIdList
        if (x > 0 && !( (x-1) == 0 && y == 0) ) // no node points back to start
            nodesIdList.add(getNodeIdFromCoords(x-1, y))
        if (x < xSize-1)
            nodesIdList.add(getNodeIdFromCoords(x+1, y))
        if (y > 0 && !( x == 0 && (y-1) == 0) ) // no node points back to start
            nodesIdList.add(getNodeIdFromCoords(x, y-1))
        if (y < ySize-1)
            nodesIdList.add(getNodeIdFromCoords(x, y+1))
        return nodesIdList
    }

    /** for a specific node get the list of costs to all connected nodes */
    fun getCostsToConnections(nodeId: Int): List<Int> {
        val costsList = mutableListOf<Int>()
        grid[nodeId].connectedNodes.forEach { id -> costsList.add(grid[id].cost) }
        return costsList
    }

    /** run DijKstra algorithm */
    fun findMinCostforAllPaths(): Int {
        return Dijkstra.runIt(grid, 0)     // return min cost
    }

    /** get the minimum cost path */
    fun getMinCostPath(): List<Int> {
        val path = mutableListOf<Int>()
        var prevNode = grid.last()      // start from the end node and go backwards
        do {
            path.add(prevNode.thisId)
            prevNode = grid[prevNode.lastUpdatedBy]
        } while (prevNode != grid[0])
        path.add(grid[0].thisId)
        return path.reversed()
    }

    /** print square grid in visualised way */
    fun printSquareGrid(): String {
        var s = ""
        for (y in 0 until ySize) {
            s += "\n"
            for (x in 0 until xSize) {
                val node = grid[getNodeIdFromCoords(x,y)]
                s += node.cost
            }
        }
        return s.substring(1)
    }

    /** visualise the min cost path in the grid */
    fun minCostPathToString(path: List<Int>): String {
        var s = ""
        for (y in 0 until ySize) {
            s += "\n"
            for (x in 0 until xSize) {
                val nodeId = getNodeIdFromCoords(x, y)
                if (path.contains(nodeId))
                    s += " ${grid[nodeId].cost}"
                else
                    s += " ."
            }
        }
        return s.substring(1)
    }
}