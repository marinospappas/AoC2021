package mpdev.aoc2021.day15

object DijkstraExampleCode {

    class Node(var connection: List<Int>, var distance: List<Int>)

    fun dijkstra(graph: List<Node>, source: Int) {
        val count = graph.size
        val visitedVertex = BooleanArray(count)
        val distance = IntArray(count)
        for (i in 0 until count) {
            visitedVertex[i] = false
            distance[i] = Int.MAX_VALUE
        }
        // Distance of self loop is zero
        distance[source] = 0
        for (i in 0 until count) {
            // Update the distance between neighbouring vertex and source vertex
            val u = findMinDistance(distance, visitedVertex)
            visitedVertex[u] = true

            // Update all the neighbouring vertex distances
            val conx = graph[u].connection
            val dist = graph[u].distance
            for (v in conx.indices) {
                if (!visitedVertex[conx[v]] && distance[u] + dist[v] < distance[conx[v]]) {
                    distance[conx[v]] = distance[u] + dist[v]
                }
            }
        }
        for (i in distance.indices) {
            println(String.format("Distance from %s to %s is %s", source, i, distance[i]))
        }
    }

    // Finding the vertex of minimum distance
    private fun findMinDistance(distance: IntArray, visitedVertex: BooleanArray): Int {
        var minDistance = Int.MAX_VALUE
        var minDistanceVertex = -1
        for (i in distance.indices) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i]
                minDistanceVertex = i
            }
        }
        return minDistanceVertex
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val orig_array_NOT_USED = arrayOf(
            //         Connections and distances
            //         0   1   2   3   4   5   6
            intArrayOf(0,  0, 11, 12,  0,  0,  0),   // 0 Nodes
            intArrayOf(0,  0, 12,  0,  0, 13,  0),   // 1
            intArrayOf(11,12,  0, 11, 13,  0,  0),   // 2
            intArrayOf(12, 0, 11,  0,  0,  0, 11),   // 3
            intArrayOf(0,  0, 13,  0,  0, 12,  0),   // 4
            intArrayOf(0, 13,  0,  0,  2,  0, 11),   // 5
            intArrayOf(0,  0,  0, 11,  0, 11,  0)    // 6
        )
        val graph: List<Node> = listOf(
            Node(listOf(2,3), listOf(11,12)),           // 0
            Node(listOf(2,5), listOf(12,13)),           // 1
            Node(listOf(0,1,3,4), listOf(11,12,11,13)), // 2
            Node(listOf(0,2,6), listOf(12,11,11)),      // 3
            Node(listOf(2,5), listOf(13,12)),           // 4
            Node(listOf(1,4,6), listOf(13,12,11)),      // 5
            Node(listOf(3,5), listOf(11,11))            // 6
        )
        println("Graph is represented as list of nodes - each node has a list of connections")
        dijkstra(graph, 0)
    }
}

