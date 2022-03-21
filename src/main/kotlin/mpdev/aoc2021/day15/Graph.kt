package mpdev.aoc2021.day15

import java.lang.Math.abs

class Node(override var id: Int, var name: String = "", var thisCost: Int = 0): GraphNode<Int> {
    val nextNodes = mutableListOf<NodeWCost>()

    override fun getConnectedNodes(): List<NodeWCost> {
        val coords = Graph.getCoordsFromNodeId(id)
        val nodeList = Graph.getConnectedNodes(coords[0], coords[1])
        nodeList.forEach { it.costFromPrev = Graph.staticGrid[it.node.id].thisCost }
        return nodeList
    }

    override fun heuristic(): Int {
        var cost = 0
        // h based on manhattan distance from end - did not help
        //val coords = Graph.getCoordsFromNodeId(id)
        //cost += abs(coords[0] - xSize) + abs(coords[1] - ySize)
        /* h based on the cheaper next connected node - did not help
        val connectedNodes = this.getConnectedNodes()
        if (connectedNodes.isEmpty()) return cost
        var prefNextNode = connectedNodes.minByOrNull { it.node.id }
        if (prefNextNode == null)
            prefNextNode = connectedNodes.first()
        cost += Graph.staticGrid[prefNextNode.node.id].thisCost
         */
        return cost
    }

    override fun toString(): String {
        var s = "$name id $id this cost $thisCost\n"
        nextNodes.forEach { s += "    id ${it.node.id} ${Graph.getCoordsFromNodeId(it.node.id)} cost ${it.costFromPrev}\n" }
        return s
    }
}

class NodeWCost(override var node: GraphNode<Int>, override var costFromPrev: Int = 0): NodeCost<Int> {
    override fun toString() = "$node cost ${costFromPrev}"
}

class Graph(var grid: MutableList<Node> = mutableListOf(), var id: Int = 0) {

    init {
        staticGrid = grid
        endNodeId = grid.size-1
    }

    override fun toString(): String {
        var s = ""
        grid.forEach { s += it.toString() + "\n" }
        return s.substring(1, s.length-1)
    }

    companion object {

        lateinit var staticGrid: List<Node>     // the actual grid is kept here as static var

        var endNodeId = 0

        /** convert x,y coords to nodeId) */
        fun getNodeIdFromCoords(x: Int, y: Int): Int = xSize * y + x

        /** convert nodeid to coords x,y */
        fun getCoordsFromNodeId(id: Int): List<Int> {
            val coords = mutableListOf<Int>()
            coords.add(id % ySize)
            coords.add(id / ySize)
            return coords
        }

        /** get connected points to a specific point */
        fun getConnectedNodes(x: Int, y: Int): List<NodeWCost> {
            val nodesList: MutableList<NodeWCost> = mutableListOf()
            if (x == (xSize-1) && y == (ySize-1))   // end node points to no other node
                return nodesList
            if (x > 0 && !( (x-1) == 0 && y == 0) ) // no node points back to start
                nodesList.add(NodeWCost(Node(getNodeIdFromCoords(x-1, y))))
            if (x < xSize-1)
                nodesList.add(NodeWCost(Node(getNodeIdFromCoords(x+1, y))))
            if (y > 0 && !( x == 0 && (y-1) == 0) ) // no node points back to start
                nodesList.add(NodeWCost(Node(getNodeIdFromCoords(x, y-1))))
            if (y < ySize-1)
                nodesList.add(NodeWCost(Node(getNodeIdFromCoords(x, y+1))))
            return nodesList
        }
    }

    /** add a node to the grid */
    fun addNode(name: String, cost: Int, thisId: Int) {
        grid.add(Node(thisId, name, cost))
    }

    /** run DijKstra algorithm */
    fun getMinCostPath(): MinCostPath<Int> {
        //return Dijkstra.runIt(grid, 0, grid.size-1)     // return min cost
        val result = AStar<Int>().runIt(grid.first(), grid.last())
        println("Dijkstra algorithm - number of iterations: ${result.numberOfIterations}")
        println("minimum cost path: ${result.path}")
        return result
    }

    /** print square grid in visualised way */
    fun printSquareGrid(): String {
        var s = ""
        for (y in 0 until ySize) {
            s += "\n"
            for (x in 0 until xSize) {
                val node = staticGrid[getNodeIdFromCoords(x,y)]
                s += node.thisCost
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
                    s += " ${grid[nodeId].thisCost}"
                else
                    s += " ."
            }
        }
        return s.substring(1)
    }

}