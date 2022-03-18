package mpdev.aoc2021.day23

import java.lang.StrictMath.abs

const val _h = 1
const val _1 = 2
const val _2 = 3
const val _3 = 4
const val _4 = 5
const val _a = 3
const val _b = 5
const val _c = 7
const val _d = 9
const val empty = '.'

val roomName = mapOf(_a to 'A', _b to 'B', _c to 'C', _d to 'D')
val roomId = mapOf('A' to _a, 'B' to _b, 'C' to _c, 'D' to _d)

class HouseVisual(var input: List<String>) {

    var map: MutableList<MutableList<Char>> = mutableListOf()

    init {
        input.forEach { map.add(it.toMutableList()) }
    }

    val energy = mutableMapOf('A' to 0, 'B' to 0, 'C' to 0, 'D' to 0)

    override fun toString(): String {
        var s = ""
        map.forEach { s += it.toString().replace(",","") + "\n" }
        return s.substring(0,s.length-1)
    }

    fun isEmpty(y: Int, x: Int): Boolean = map[y][x] == empty

    fun manhDist(x1: Int, y1: Int, x2: Int, y2: Int) = abs(x1-x2) + abs(y1-y2)

    fun moveRoom2Hall(room: Int, newPos: Int) {
        var curLevel = 0
        for (i in _1 .. 2*part1_2+1)
            if(!isEmpty(i, room)) {curLevel = i; break}
        if (curLevel == 0) return
        map[_h][newPos] = map[curLevel][room]
        map[curLevel][room] = empty
        energy[ map[_h][newPos] ] = energy[ map[_h][newPos] ]?.plus( manhDist(curLevel,room, _h,newPos) )!!
    }

    fun moveHall2Room(name: Char, fromPos: Int = 0) {
        val pos = if (fromPos > 0) fromPos else getPosInHall(name)
        if (pos == 0) return
        val room = roomId[name]?:0
        var newLevel = 0
        for (i in  2*part1_2+1 downTo _1)
            if(isEmpty(i, room)) {newLevel = i; break}
        if (newLevel == 0) return
        map[newLevel][room] = map[_h][pos]
        map[_h][pos] = empty
        energy[ map[newLevel][room] ] = energy[ map[newLevel][room] ]?.plus(manhDist(_h,pos, newLevel,room) )!!
    }

    fun getPosInHall(name: Char): Int {
        for (i in 1..11)
            if (map[_h][i] == name) return i
        return 0
    }

    fun totalEnergy(): Int {
        val ea: Int = energy['A']!!
        val eb: Int = energy['B']!!
        val ec: Int = energy['C']!!
        val ed: Int = energy['D']!!
        return ea + eb*10 + ec*100 + ed*1000
    }
}