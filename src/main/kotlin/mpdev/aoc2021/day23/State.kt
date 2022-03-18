package mpdev.aoc2021.day23


class State(var stateId: String) {

   val endState = if (part1_2 ==1)
       "...........AABBCCDD"
   else
       "...........AAAABBBBCCCCDDDD"

    val STATE_LEN = 19 + 8 * (part1_2-1)
    //val stateId = stateIdU.toStateString()

    val costMap = mapOf('A' to 1, 'B' to 10, 'C' to 100, 'D' to 1000)

    val HALL_LEN = 11
    val ROOM_LEN = part1_2 * 2
    val hall = stateId.substring(0, HALL_LEN).toCharArray()
    val rooms = Array(4) {
        val roomStart = HALL_LEN + it * ROOM_LEN
        Room('A'+it, it, stateId.substring(roomStart, roomStart + ROOM_LEN).toCharArray())
    }

    val validHallPsns = listOf(0,1,3,5,7,9,10)

    fun nextStates(): List<Node> {
        val myStatesList = mutableListOf<Node>()
        nextMovesRoom2Hall().forEach {
            val fromY = it[0]; val fromX = it[1]; val toY = it[2]; val toX = it[3]
            val myHall = hall.copyOf()
            val myRooms = Array(4) {
                Room(rooms[it].roomId, rooms[it].indx, rooms[it].occupants.copyOf())
            }
            val roomIndx = (fromX-2)/2
            val occupant = myRooms[roomIndx].occupants[fromY-1]
            myHall[toX] = occupant
            myRooms[roomIndx].occupants[fromY-1] = empty
            val costThisMovement = manhDist(fromX, fromY, toX, toY) * costMap[ occupant ]!!
            var newState = String(myHall)
            for (i in 0..3) newState += String(myRooms[i].occupants)
            myStatesList.add(Node(newState, costThisMovement))
        }
        nextMovesHall2Room().forEach {
            val fromY = it[0]; val fromX = it[1]; val toY = it[2]; val toX = it[3]
            val myHall = hall.copyOf()
            val myRooms = Array(4) {
                Room(rooms[it].roomId, rooms[it].indx, rooms[it].occupants.copyOf())
            }
            val roomIndx = (toX-2)/2
            val occupant = myHall[fromX]
            myRooms[roomIndx].occupants[toY-1]= occupant
            myHall[fromX] = empty
            val costThisMovement = manhDist(fromX, fromY, toX, toY) * costMap[ occupant ]!!
            var newState = String(myHall)
            for (i in 0..3) newState += String(myRooms[i].occupants)
            myStatesList.add(Node(newState, costThisMovement))
        }
        return myStatesList
    }

    private fun nextMovesRoom2Hall(): List<List<Int>> {
        val fromToList: MutableList<List<Int>> = mutableListOf()
        rooms.forEach { r ->
            if (r.isEmptyOrHasOnlyValidOccupants()) return@forEach
            val roomLevel = r.topOccupiedLevel() + 1
            val hallIndx = (r.indx+1) * 2
            for (i in validHallPsns.filter { it < hallIndx }.reversed()) {
                if (hall[i] == empty) fromToList.add(listOf(roomLevel, hallIndx, 0, i))
                else break
            }
            for (i in validHallPsns.filter { it > hallIndx }) {
                if (hall[i] == empty) fromToList.add(listOf(roomLevel, hallIndx, 0, i))
                else break
            }
        }
        return fromToList
    }

    private fun nextMovesHall2Room(): List<List<Int>> {
        val fromToList: MutableList<List<Int>> = mutableListOf()
        for (i in 0 .. hall.size-1) {
            if (hall[i] == empty) continue
            val roomIndx = hall[i] - 'A'
            val hallIndx = i
            val roomX = 2 * roomIndx + 2
            if (rooms[roomIndx].isEmptyOrHasOnlyValidOccupants()) {
                if (isHallFree(hallIndx, roomX)) {
                    val roomLevel = rooms[roomIndx].topOccupiedLevel() - 1
                    fromToList.add(listOf(0, hallIndx, roomLevel+1, roomX))
                }
            }
        }
        return fromToList
    }

    private fun isHallFree(a: Int, b: Int): Boolean {
        return hall.slice(
            if (a > b) b .. a-1
            else a+1 .. b
        ).all { it == empty }
    }

    class Room(val roomId: Char, val indx: Int, val occupants: CharArray) {

        override fun toString() = "$roomId ($indx) ${String(occupants)}"

        fun hasOnlyValidOccupants() = occupants.all { it == roomId }

        fun isEmptyOrHasOnlyValidOccupants() = occupants.all { it == empty || it == roomId }

        fun topOccupiedLevel(): Int {
            for (level in 0 until occupants.size)
                if (occupants[level] != empty) return level
            return occupants.size
        }
    }

    private fun manhDist(x1: Int, y1: Int, x2: Int, y2: Int) = StrictMath.abs(x1 - x2) + StrictMath.abs(y1 - y2)

    override fun toString(): String {
        var s = "# # # # # # # # # # # # #\n"
        s += "# "; hall.forEach { s += "$it " }; s += "#\n"
        s += "# # # "
        for (i in 0..3) s+= "${rooms[i].occupants[0]} # "
        s += "# # \n"
        for (j in 1 until rooms[0].occupants.size) {
            s += "    # "
            for (i in 0..3) s+= "${rooms[i].occupants[j]} # "
            s += "  \n"
        }
        s += "    # # # # # # # # #    "
        return s
    }

    /**
     * heuristic is an estimate to get from this state to the end state by moving the occupants
     * to the target as if there are no obstacles
     * used in the A* algorithm
     */
    fun heuristic(): Int {
        setTopFree()
        val cost1 = moveRoom2Room()
        val cost2 = moveHall2Room()
        return cost1 + cost2
    }

    val topFree = Array(4) {ROOM_LEN - 1}

    fun setTopFree() {
        for (i in 0..3) {
            for (j in ROOM_LEN-1 downTo 0) {
                if (rooms[i].occupants[j] != rooms[i].roomId) {
                    topFree[i] = j
                    break
                }
            }
        }
    }
    fun moveHall2Room(): Int {
        var cost = 0
        for (i in 0 .. HALL_LEN-1) {
            if (hall[i] == empty) continue
            val obj = hall[i]
            val fromX = i
            val fromY = 0
            val roomIndx = 3 - ('D' - obj)
            val toX = (roomIndx+1)*2
            val toY = topFree[roomIndx] + 1
            cost += manhDist(fromX, fromY, toX, toY) * costMap[ obj ]!!
            ++topFree[roomIndx]
        }
        return cost
    }

    fun moveRoom2Room(): Int {
        return 0
    }
}

class Node(val stateId: String, var cost: Int, var updatedBy: String = "", var totalCost: Int = 0): Comparable<Node> {
    override fun compareTo(other: Node) = cost.compareTo(other.cost)
}
