package mpdev.aoc2021.day15

object Dijkstra {

    /**
     * the Dijkstra algorithm implementation
     * calculates the minimum costs for all the paths starting from start to all nodes in the graph
     * returns minimum cost for the path start to end
     */
    fun runIt(graph: List<Node>, start: Int, end: Int): Int {

        // setup visited flags and minimum total costs for each node
        val visitedNode = BooleanArray(graph.size) { false }
        val dijkCost = IntArray(graph.size) { Int.MAX_VALUE }

        // cost of being at start is zero
        dijkCost[start] = 0

        for (i in 1 .. graph.size) {
            // Find the node with the lowest cost from the nodes that have not been visited yet
            val lowestCostNodeId = findMinCost(dijkCost, visitedNode)
            visitedNode[ lowestCostNodeId ] = true
                            // if (lowestCostNodeId == end)     **** terminate the loop here if only interested in the
                            //    return dijkCost[end]          **** lowest path from start to end and not from start to all nodes
            // Check the cost to all the neighbouring nodes and update if lower than the cost already calculated
            val conxId = graph[ lowestCostNodeId ].connectedNodes
            val costToConx = graph[ lowestCostNodeId ].costToConnections
            for (indx in conxId.indices) {
                val tempCost = dijkCost[ lowestCostNodeId ] + costToConx[indx]
                if (!visitedNode[conxId[indx]] && tempCost < dijkCost[conxId[indx]]) {
                    dijkCost[conxId[indx]] = tempCost
                    graph[ conxId[indx] ].lastUpdatedBy = lowestCostNodeId
                }
            }
        }
        return dijkCost[end]
    }

    /** find the node of minimum cost that has not been visited yet */
//    private fun findMinCost(cost: IntArray, visitedVertex: BooleanArray): Int {
    private fun findMinCost(cost: IntArray, visited: BooleanArray): Int {
        var minCost = Int.MAX_VALUE
        var minCostVertex = -1
        for (i in cost.indices) {
            if (!visited[i] && cost[i] < minCost) {
                minCost = cost[i]
                minCostVertex = i
            }
        }
        return minCostVertex
    }

}