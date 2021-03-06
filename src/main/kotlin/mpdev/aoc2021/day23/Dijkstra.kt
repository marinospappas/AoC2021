package mpdev.aoc2021.day23

import java.util.*

/*
interface NodeCost<T> {
    var node: GraphNode<T>
    var costFromPrev: Int
}

interface GraphNode<T> {
    var id: T
    fun getConnectedNodes(): List<NodeCost<T>>
    fun heuristic(): Int
}

class MinCostPath<T> {
    var path: List<T> = listOf()
    var minCost: Int = Int.MAX_VALUE
    var numberOfIterations: Int = 0
}
*/

/**
 * A* implementation
 * T is the type of the Node ID in the Graph
 */
class Dijkstra<T> {

    class PathNode<T>(
        val node: GraphNode<T>?,
        var costFromStart: Int,
        var updatedBy: T? = null): Comparable<PathNode<T>> {
        override fun compareTo(other: PathNode<T>): Int {
            // in Dijkstra min cost criteria is based on cost from start to this node
            return costFromStart.compareTo(other.costFromStart)
        }

        override fun toString(): String {
            return "\nnode: $node cost from start $costFromStart updated by $updatedBy"
        }
    }

    class DikstraException(override var message: String): Exception()

    /**
     * The Dijkstra algorithm implementation
     */
    fun runIt(startState: GraphNode<T>, endState: GraphNode<T>): MinCostPath<T> {

        // setup priority queue, visited set and minimum total costs for each node
        val toVisitQueue = PriorityQueue<PathNode<T>>().apply { add(PathNode(startState,0)) }
        val visitedNodes = mutableSetOf<PathNode<T>>()
        val dijkstraCost =  mutableMapOf<T,PathNode<T>>(). withDefault { PathNode(null, Int.MAX_VALUE) }

        var iterations = 0
        // while the priority Q has elements, get the top one (least cost as per Comparator)
        while (toVisitQueue.isNotEmpty()) {
            val currentNode = toVisitQueue.poll()
            // if this is the endNode ID, we are done
            if (currentNode.node?.id!! == endState.id) {
                val minCostPath = MinCostPath<T>()
                minCostPath.minCost = currentNode.costFromStart
                minCostPath.numberOfIterations = iterations
                minCostPath.path = getMinCostPath(currentNode.node.id, startState.id, dijkstraCost)
                return minCostPath
            }
            // else for each connected node
            currentNode.node.getConnectedNodes().forEach { connectedNode ->
                ++iterations
                val nextPathNode = PathNode(connectedNode.node, connectedNode.costFromPrev)
                if (visitedNodes.contains(nextPathNode))
                    return@forEach
                visitedNodes.add(nextPathNode)
                // calculate the new cost to that node and the new *estimated* total cost to the end node
                val newCost = currentNode.costFromStart + connectedNode.costFromPrev
                // if the new cost is less that what we have already recorded in the map of nodes/costs
                // update the map with the new costs and "updatedBy" (to be able to back-track the min.cost path)
                if (newCost < dijkstraCost.getValue(connectedNode.node.id).costFromStart) {
                    nextPathNode.updatedBy = currentNode.node.id
                    nextPathNode.costFromStart = newCost
                    dijkstraCost[connectedNode.node.id] = nextPathNode
                    // and put the updated new node back into the priority queue
                    toVisitQueue.add(nextPathNode)
                }
            }
        }
        throw DikstraException("no path found from ${startState.id} to ${endState.id}")
    }

    fun getMinCostPath(minCostKey: T, startKey: T, dijkstraCost: Map<T, PathNode<T>>): List<T> {
        var key = minCostKey
        val path = mutableListOf<T>()
        do {
            path.add(key)
            key = dijkstraCost.getValue(key).updatedBy ?: startKey
        } while (key != startKey)
        path.add(key)
        return path.reversed()
    }
}