package mpdev.aoc2021.day19

import java.lang.StrictMath.abs

class Coordinates(var x: Int, var y: Int, var z: Int) {
    override fun toString() = "($x,$y,$z)"
    fun isEqual(c1: Coordinates): Boolean = x == c1.x && y == c1.y && z == c1.z
    fun manhattanDistance(coord2: Coordinates) = abs(x-coord2.x) + abs(y-coord2.y) + abs(z-coord2.z)
}

class Beacon(var relCoord: Coordinates) {
    var absCoord = Coordinates(0,0,0)
    override fun toString() = "relCoord: $relCoord absCoord: ${absCoord}"
    operator fun minus(b: Beacon): Long {
        return (b.relCoord.x - relCoord.x) * (b.relCoord.x - relCoord.x).toLong() +
               (b.relCoord.y - relCoord.y) * (b.relCoord.y - relCoord.y) +
               (b.relCoord.z - relCoord.z) * (b.relCoord.z - relCoord.z)
    }
}

class Scanner(var beacons: MutableList<Beacon>, var id: Int) {

    var position = Coordinates(0,0,0)
    var xyzMapping = arrayOf(arrayOf(1,0,0), arrayOf(0,1,0), arrayOf(0,0,1))
    var distanceToBeacon: Array<Array<Long>> = Array(beacons.size) { Array(beacons.size) {0} }
    var inPosition = false
    var overlappedBy = -1

    init {
        for (i in beacons.indices)
            for (j in 0 until beacons.size)
                distanceToBeacon[i][j] = beacons[i] - beacons[j]
    }

    fun xMapped(coords: Coordinates) = mapCoords(coords, 0)
    fun yMapped(coords: Coordinates) = mapCoords(coords, 1)
    fun zMapped(coords: Coordinates) = mapCoords(coords, 2)

    private fun mapCoords(coords: Coordinates, indx: Int): Int {
        if (xyzMapping[indx][0] != 0) return coords.x * xyzMapping[indx][0]
        if (xyzMapping[indx][1] != 0) return coords.y * xyzMapping[indx][1]
        if (xyzMapping[indx][2] != 0) return coords.z * xyzMapping[indx][2]
        println("*** scanner $id - invalid orientation ***")
        return Int.MAX_VALUE
    }

    fun updBcnCoord() {
        beacons.forEach { beacon ->
            // beacon abs coords
            beacon.absCoord.x = position.x + xMapped(beacon.relCoord)
            beacon.absCoord.y = position.y + yMapped(beacon.relCoord)
            beacon.absCoord.z = position.z + zMapped(beacon.relCoord)
        }
    }

    fun xyzMapToString(): String {
        var s = if (xyzMapping[0][0] == 1) "(x)" else
                if (xyzMapping[0][0] == -1) "(-x)" else
                if (xyzMapping[0][1] == 1) "(y)" else
                if (xyzMapping[0][1] == -1) "(-y)" else
                if (xyzMapping[0][2] == 1) "(z)" else
                "(-z)"
        s += if (xyzMapping[1][0] == 1) "(x)" else
            if (xyzMapping[1][0] == -1) "(-x)" else
            if (xyzMapping[1][1] == 1) "(y)" else
            if (xyzMapping[1][1] == -1) "(-y)" else
            if (xyzMapping[1][2] == 1) "(z)" else
                 "(-z)"
        s += if (xyzMapping[2][0] == 1) "(x)" else
            if (xyzMapping[2][0] == -1) "(-x)" else
            if (xyzMapping[2][1] == 1) "(y)" else
            if (xyzMapping[2][1] == -1) "(-y)" else
            if (xyzMapping[2][2] == 1) "(z)" else
                 "(-z)"
        return s
    }

    override fun toString(): String {
        var s = ""
        s += "id: $id position: $position orientation: ${xyzMapToString()} in position $inPosition\n"
        for (i in beacons.indices)
            s += "    beacon[$i]  ${beacons[i]}\n"
        for (i in beacons.indices)
            s += "    ${distanceToBeacon[i].toList()}\n"
        return s
    }

}

fun List<Scanner>.beaconCount(): Int {
    val totalBeacons = mutableMapOf<String, Coordinates>()
    forEach { scanner ->
        scanner.beacons.forEach { beacon ->
            totalBeacons[beacon.absCoord.toString()] = beacon.absCoord
        }
    }
    return totalBeacons.count()
}

fun List<Scanner>.maxManhDist(): Int {
    return this.maxOf { sc1 ->
        this.maxOf { sc2 -> sc1.position.manhattanDistance(sc2.position) }
    }
}
