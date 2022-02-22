package mpdev.aoc2021.day15

object Dijkstra {

    fun runIt(graph: List<Node>, start: Int): Int {

        //val graph = network.grid
        val count = graph.size
        val visitedVertex = BooleanArray(count)
        val dijkCost = IntArray(count)
        for (i in 0 until count) {
            visitedVertex[i] = false
            dijkCost[i] = Int.MAX_VALUE
        }
        // Cost of self loop is zero
        dijkCost[start] = 0
        for (i in 0 until count) {
            // Update the cost between neighbouring vertex and source vertex
            val u = findMinCost(dijkCost, visitedVertex)
            visitedVertex[u] = true

            // Update all the neighbouring vertex costs
            val conxId = graph[u].connectedNodes
            val costToConx = graph[u].costToConnections
            for (v in conxId.indices) {
                if (!visitedVertex[conxId[v]] && dijkCost[u] + costToConx[v] < dijkCost[conxId[v]]) {
                    dijkCost[conxId[v]] = dijkCost[u] + costToConx[v]
                }
            }
        }
        return dijkCost.last()
    }

    // Finding the vertex of minimum distance
    private fun findMinCost(cost: IntArray, visitedVertex: BooleanArray): Int {
        var minCost = Int.MAX_VALUE
        var minCostVertex = -1
        for (i in cost.indices) {
            if (!visitedVertex[i] && cost[i] < minCost) {
                minCost = cost[i]
                minCostVertex = i
            }
        }
        return minCostVertex
    }

}