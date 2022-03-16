package mpdev.aoc2021.day23

const val _h = 1
const val _1 = 2
const val _2 = 3
const val _a = 3
const val _b = 5
const val _c = 7
const val _d = 9
const val empty = '.'

val roomNames= mapOf(_a to 'A', _b to 'B', _c to 'C', _d to 'D')

class Position(var y: Int, var x: Int)

class Building(var input: List<String>) {

    var map: MutableList<MutableList<Char>> = mutableListOf()

    init {
        input.forEach { map.add(it.toMutableList()) }
    }

    override fun toString(): String {
        var s = ""
        map.forEach { s += it.toString().replace(",","") + "\n" }
        return s.substring(0,s.length-1)
    }

    fun isEmpty(y: Int, x: Int): Boolean = map[y][x] == empty

    fun moveUpInRoom(room: Int) {
        map[_1][room] = map[_2][room]
        map[_2][room] = empty
    }

    fun fromRoom2Hall(room: Int, left: Boolean) {
        if (isEmpty(_1,room) && !isEmpty(_2,room))
            moveUpInRoom(room)
        val newCol = if (left) room-1 else room+1
        map[_h][newCol] = map[_1][room]
        map[_1][room] = empty
    }
}