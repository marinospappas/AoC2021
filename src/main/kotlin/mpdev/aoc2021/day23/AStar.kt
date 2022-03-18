package mpdev.aoc2021.day23

import java.util.PriorityQueue

object AStar {

    /**
     * the Dijkstra algorithm implementation
     * calculates the minimum costs for all the paths starting from start to all nodes in the graph
     * returns minimum cost for the path start to end
     */
    fun runIt(startState: String, endState: String): Int {

        // setup priority queue, visited set and minimum total costs for each node
        val toVisitQueue = PriorityQueue<Node>().apply { add(Node(startState,0, "", 0)) }
        val visitedNodes = mutableSetOf<Node>()
        val astarCost =  mutableMapOf<String, Node>(). withDefault { Node("", Int.MAX_VALUE, "", Int.MAX_VALUE) }

        while (toVisitQueue.isNotEmpty()) {
            val currentNode = toVisitQueue.poll().also { visitedNodes.add(it) }
            State(currentNode.stateId).nextStates().forEach { nextState ->
                if (visitedNodes.contains(nextState))
                    return@forEach
                // cost comparison is based on cost from start to this node
                // plus heuristic (estimated cost to reach the end from this state)
                val newCost = currentNode.cost + nextState.cost
                val h = State(nextState.stateId).heuristic()
                val newTotalCost = currentNode.cost + nextState.cost + h
                //println("${nextState.stateId} newg $newCost newf $newTotalCost prev f ${astarCost.getValue(nextState.stateId).totalCost}")
                if (newTotalCost < astarCost.getValue(nextState.stateId).totalCost) {
                    astarCost[nextState.stateId] = Node(nextState.stateId, newCost, currentNode.stateId, newTotalCost)
                    toVisitQueue.add(Node(nextState.stateId, newCost, currentNode.stateId))
                }
            }
        }
        val minEnergyKey = astarCost.keys.first { it == endState }
        printMinEnergyPath(minEnergyKey, astarCost)
        return astarCost[minEnergyKey]?.cost!!
    }

    fun printMinEnergyPath(minEnergyKey: String, cost: Map<String,Node>) {
        var key = minEnergyKey
        val path = mutableListOf<String>()
        do {
            path.add(key)
            key = cost.getValue(key).updatedBy
        } while (key != "")
        var i = 0
        path.reversed().forEach { println("step ${i++}\n${State(it)}"); println("cost ${cost[it]?.cost ?: 0}\n") }
        println("Total number of states ${cost.size}")
    }
}

