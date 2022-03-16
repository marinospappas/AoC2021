package mpdev.aoc2021.day22

import java.lang.StrictMath.max
import java.lang.StrictMath.min

var is3D = true

class Range3d(var x1: Int, var x2: Int, var y1: Int, var y2: Int, var z1: Int = 0, var z2: Int = 0) {
    init {
        if (!is3D) { z1 = 0; z2 = 0 }
    }
    override fun toString() = if (is3D) "x=$x1..$x2,y=$y1..$y2,z=$z1..$z2" else "x=$x1..$x2,y=$y1..$y2"
    fun isEqual(range2: Range3d) =
        x1 == range2.x1 && x2 == range2.x2 && y1 == range2.y1 && y2 == range2.y2 && z1 == range2.z1 && z2 == range2.z2
}

/** class Cuboid */
class Cuboid (var r: Range3d, var state: Int = 1) {

    constructor(x1: Int, x2: Int, y1: Int, y2: Int, z1: Int = 0, z2: Int = 0, state: Int = 1):
            this(Range3d(x1, x2, y1, y2, z1, z2), state) {
        if (!is3D) {
            r.z1 = 0
            r.z2 = 0
        }
    }

    override fun toString() = r.toString()

    fun isEqual(cub2: Cuboid) = r.isEqual(cub2.r)

    fun numberOfCubes(): Long = (r.x2 - r.x1 + 1).toLong() * (r.y2 - r.y1 + 1) * (r.z2 - r.z1 + 1)

    fun intersects(cub2: Cuboid): Range3d? {
        // range of potential intersect (if any)
        val x1 = max(r.x1, cub2.r.x1)
        val x2 = min(r.x2, cub2.r.x2)
        val y1 = max(r.y1, cub2.r.y1)
        val y2 = min(r.y2, cub2.r.y2)
        val z1 = max(r.z1, cub2.r.z1)
        val z2 = min(r.z2, cub2.r.z2)
        //println("INTERSECT:")
        //println("checking $this against $cub2")
        //println("checking ranges: x=$x1-$x2, y=$y1-$y2, z=$z1-$z2")
        if (x1 <= x2 && y1 <= y2 && z1 <= z2) {
            //println("returning intersect of ${Range3d(x1, x2, y1, y2, z1, z2)}")
            return Range3d(x1, x2, y1, y2, z1, z2)
        }
        else
            return null
    }

    /** split a cuboid and return the children minus the intersect */
    fun split(intersect: Range3d?): List<Cuboid>? {
        if (intersect == null) return null
        val newCuboids = mutableListOf<Cuboid>()
        // #1-9 (left)
        if(r.x1 < intersect.x1)
            newCuboids.add(Cuboid(r.x1, intersect.x1-1, r.y1, r.y2, r.z1, r.z2))
        // #10-18 (center)
        if (r.y1 < intersect.y1)  // back
            newCuboids.add(Cuboid(intersect.x1, intersect.x2, r.y1, intersect.y1-1, r.z1, r.z2))
        if (intersect.y2 < r.y2)  // front
            newCuboids.add(Cuboid(intersect.x1, intersect.x2, intersect.y2+1, r.y2, r.z1, r.z2))
        // this is the actual intersect
        // newCuboids.add(Cuboid(intersect.x1, intersect.x2, intersect.y1, intersect.y2, intersect.z1, intersect.z2))
        if (r.z1 < intersect.z1)  // bottom
            newCuboids.add(Cuboid(intersect.x1, intersect.x2, intersect.y1, intersect.y2, r.z1, intersect.z1-1))
        if (intersect.z2 < r.z2)  // top
            newCuboids.add(Cuboid(intersect.x1, intersect.x2, intersect.y1, intersect.y2, intersect.z2+1, r.z2))
        // #19-27 (right)
        if(intersect.x2 < r.x2)
            newCuboids.add(Cuboid(intersect.x2+1, r.x2, r.y1, r.y2, r.z1, r.z2))


        return newCuboids
    }
}