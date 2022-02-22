package mpdev.aoc2021.day12

/** the network node */
class Node( var connectedNodes: MutableList<String>,
            var minor: Boolean = false,
            var timesVisited: Int = 0,
            var maxTimesAllowed: Int = Int.MAX_VALUE) {

    override fun toString(): String {
        var s = "visited $timesVisited max allowed $maxTimesAllowed\n    connected to: "
        connectedNodes.forEach { s += "$it, " }
        return s
    }

    fun hasReachedMaxTimes() = timesVisited >= maxTimesAllowed

    fun incTimesVisited() = ++timesVisited
}

/** the nodes map */
class NodesMap(var map: MutableMap<String,Node> = mutableMapOf()) {

    override fun toString(): String {
        var s = ""
        for (id in map.keys) { s += "id: [$id]\n    ${map[id].toString()}\n" }
        return s
    }

    operator fun get(id: String) = map[id]
}

/** the network */
class Network(var netMap: NodesMap = NodesMap(), var detectLoop: Boolean = false) {

    override fun toString() = netMap.toString()

    /** add a connection between nodes */
    fun addConnection(id1: String, id2: String) {
        val node1 = netMap.map[id1]
        if (node1 != null)
            node1.connectedNodes.add(id2)           // update existing node
        else
            netMap.map[id1] = Node(mutableListOf(id2))  // create new node
    }

    /** reset the max allowed times for all minor nodes */
    fun resetAllMinorNodes() {
        netMap.map.filterValues { it.minor } .forEach { (_, v) -> v.maxTimesAllowed = 1; v.timesVisited = 0 }
    }

    // functions that create new instances of the curPath and the nodesMap
    private fun copyOf(obj: MutableList<String>): MutableList<String> {
        val newList = mutableListOf<String>()
        obj.forEach { s -> newList.add(String(s.toCharArray())) }
        return newList
    }
    private fun copyOf(obj: NodesMap): NodesMap {
        val newMap = NodesMap()
        obj.map.forEach { (k, v) ->
            val newConnectionsList = mutableListOf<String>()
            v.connectedNodes.forEach { c ->
                newConnectionsList.add(c)
            }
            newMap.map[k] = Node(newConnectionsList, v.minor, v.timesVisited, v.maxTimesAllowed)
        }
        return newMap
    }

    // auxiliary functions to simplify the main recursive function (findAllPaths)
    private fun addPointToPath(id: String, curPath: MutableList<String>, nodesMap: NodesMap) {
        curPath.add(id)      // add point to cur path
        nodesMap[id]?.incTimesVisited()
    }
    private fun notAllowedAgain(id: String, nodesMap: NodesMap): Boolean =
        nodesMap[id]?.hasReachedMaxTimes()!!

    /**
     * check if we are going in a loop
     * the actual implementation depends on the requirements (definition of "loop")
     * this version checks if the path is A -> B -> A -> B
     */
    private fun weAreGoingInCircles(curPath: MutableList<String>, nextNode: String): Boolean {
        return if (curPath.size < 3)
            false
        else
            nextNode == curPath[curPath.size - 2] && curPath.last() == curPath[curPath.size-3]
    }

    // functions to adjust the connections at start and end points
    private fun setNoNodePointsToStartNode(startNodeId: String) {
        netMap.map.values.forEach { node ->
            node.connectedNodes.removeIf { it == startNodeId }
        }
    }
    private fun setEndNodePointsToNoNodes(endNodeId: String) {
        netMap[endNodeId]?.connectedNodes = mutableListOf()
    }

    var allPaths: MutableList<List<String>> = mutableListOf()

    /** find all the paths from A to B */
    private fun findAllPaths(a: String, b: String, curPath: MutableList<String>, nodesMap: NodesMap) {
        addPointToPath(a, curPath, nodesMap)
        if (a == b) {       // destination reached
            allPaths.add(curPath)
            return
        }
        nodesMap[a]?.connectedNodes?.forEach {  connectedNode -> // try path for each connection from this node
            if (notAllowedAgain(connectedNode, nodesMap))
                return@forEach
            if (detectLoop &&  weAreGoingInCircles(curPath, connectedNode))
                return@forEach
            findAllPaths(connectedNode, b, copyOf(curPath), copyOf(nodesMap))
        }
    }

    /** externally used function to find all paths - also adjust the connections for start and end */
    fun findPaths(a:String, b:String) {
        setNoNodePointsToStartNode(a)
        setEndNodePointsToNoNodes(b)
        findAllPaths(a, b, mutableListOf(), netMap)
    }
}