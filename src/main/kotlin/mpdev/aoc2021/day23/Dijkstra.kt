package mpdev.aoc2021.day23

import java.util.PriorityQueue

object Dijkstra {

    /**
     * the Dijkstra algorithm implementation
     * calculates the minimum costs for all the paths starting from start to all nodes in the graph
     * returns minimum cost for the path start to end
     */
    fun runIt(startState: String, endState: String): Int {

        // setup priority queue, visited set and minimum total costs for each node
        val toVisitQueue = PriorityQueue<Node>().apply { add(Node(startState,0)) }
        val visitedNodes = mutableSetOf<Node>()
        val dijkCost =  mutableMapOf<String, Node>(). withDefault { Node("", Int.MAX_VALUE) }

        while (toVisitQueue.isNotEmpty()) {
            val currentNode = toVisitQueue.poll().also { visitedNodes.add(it) }
            State(currentNode.stateId).nextStates().forEach { nextState ->
                if (visitedNodes.contains(nextState))
                    return@forEach
                // cost comparison is based on cost from start to this node
                val newCost = currentNode.cost + nextState.cost
                if (newCost < dijkCost.getValue(nextState.stateId).cost) {
                    dijkCost[nextState.stateId] = Node(nextState.stateId, newCost, currentNode.stateId)
                    toVisitQueue.add(Node(nextState.stateId, newCost, currentNode.stateId))
                }
            }
        }
        val minEnergyKey = dijkCost.keys.first { it == endState }
        printMinEnergyPath(minEnergyKey, dijkCost)
        return dijkCost[minEnergyKey]?.cost!!
    }

    fun printMinEnergyPath(minEnergyKey: String, dijkCost: Map<String,Node>) {
        var key = minEnergyKey
        val path = mutableListOf<String>()
        do {
            path.add(key)
            key = dijkCost.getValue(key).updatedBy
        } while (key != "")
        var i = 0
        path.reversed().forEach { println("step ${i++}\n${State(it)}"); println("cost ${dijkCost[it]?.cost ?: 0}\n") }
        println("Total number of states ${dijkCost.size}")
    }
}

