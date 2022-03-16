package mpdev.aoc2021.day22

class Reactor {
    var cubList = listOf<Cuboid>()

    fun processCuboid(newCube: Cuboid) {
        val newCubeList = mutableListOf<Cuboid>()
        // the same code in one line using the Elvis Operator
        ///// cubList.forEach { cube -> newCubeList.addAll(cube.split(cube.intersects(newCube)) ?: listOf(cube)) }
        // first check for any intersects
        cubList.forEach { cube ->
            val intersect = cube.intersects(newCube)
            // is there is intersect, then split and add the non-intersecting pieces, else the whole cube
            if (intersect != null)
                newCubeList.addAll(cube.split(intersect) as List<Cuboid>)
            else
                newCubeList.add(cube)
        }
        // if the new cuboid is "on" then add it to the list
        if (newCube.state == 1)
            newCubeList.add(newCube)
        cubList = newCubeList
    }

    fun numberOfCubes(): Long = cubList.sumOf { it.numberOfCubes() }
}