package mpdev.aoc2021.day22

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 22 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay22 {

    @Test
    @Order(1)
    fun `Test Cuboid Intersect and Split 1`() {
        val inp = listOf("on x=10..12,y=10..12,z=10..12",
                        "on x=11..13,y=11..13,z=11..13",
                        "off x=9..11,y=9..11,z=9..11",
                        "on x=10..10,y=10..10,z=10..10")
        is3D = false
        val inpCuboids = mutableListOf<Cuboid>()
        inp.forEach { inpCuboids.add(processLine(it)) }
        val intersect = inpCuboids[0].intersects(inpCuboids[1])
        println("cubes in 0: ${inpCuboids[0].numberOfCubes()}")
        println("intersect 0 and 1:\n$intersect")
        val split = inpCuboids[0].split(intersect!!)
        println("split 0:\n$split")
        println("total cubes in split: ${split?.sumOf{it.numberOfCubes()}}")
        assertEquals(5, split?.sumOf{it.numberOfCubes()})
    }

    @Test
    @Order(2)
    fun `Test Cuboid Intersect and Split 2`() {
        val inp = listOf("on x=10..12,y=10..12,z=10..12",
            "on x=11..13,y=11..13,z=11..13",
            "off x=9..11,y=9..11,z=9..11",
            "on x=10..10,y=10..10,z=10..10")
        is3D = false
        val inpCuboids = mutableListOf<Cuboid>()
        inp.forEach { inpCuboids.add(processLine(it)) }
        val intersect = inpCuboids[0].intersects(inpCuboids[2])
        println("cubes in 0: ${inpCuboids[0].numberOfCubes()}")
        println("intersect 0 and 1:\n$intersect")
        val split = inpCuboids[0].split(intersect!!)
        println("split 0:\n$split")
        println("total cubes in split: ${split?.sumOf{it.numberOfCubes()}}")
        assertEquals(5, split?.sumOf{it.numberOfCubes()})
    }

    @Test
    @Order(3)
    fun `Test Cuboid Intersect and Split 3`() {
        val inp = listOf("on x=10..12,y=10..12,z=10..12",
            "on x=11..13,y=11..13,z=11..13",
            "off x=9..11,y=9..11,z=9..11",
            "on x=12..12,y=12..12,z=12..12")
        is3D = false
        val inpCuboids = mutableListOf<Cuboid>()
        inp.forEach { inpCuboids.add(processLine(it)) }
        val intersect = inpCuboids[0].intersects(inpCuboids[3])
        println("cubes in 0: ${inpCuboids[0].numberOfCubes()}")
        println("intersect 0 and 1:\n$intersect")
        val split = inpCuboids[0].split(intersect!!)
        println("split 0:\n$split")
        println("total cubes in split: ${split?.sumOf{it.numberOfCubes()}}")
        assertEquals(8, split?.sumOf{it.numberOfCubes()})
    }

    @Test
    @Order(4)
    fun `Test Cuboid Intersect and Split 4`() {
        val inp = listOf("on x=10..12,y=10..12,z=10..12",
            "on x=11..13,y=11..13,z=11..13",
            "off x=9..11,y=9..11,z=9..11",
            "on x=11..11,y=11..11,z=11..11")
        is3D = false
        val inpCuboids = mutableListOf<Cuboid>()
        inp.forEach { inpCuboids.add(processLine(it)) }
        val intersect = inpCuboids[0].intersects(inpCuboids[3])
        println("cubes in 0: ${inpCuboids[0].numberOfCubes()}")
        println("intersect 0 and 1:\n$intersect")
        val split = inpCuboids[0].split(intersect!!)
        println("split 0:\n$split")
        println("total cubes in split: ${split?.sumOf{it.numberOfCubes()}}")
        assertEquals(8, split?.sumOf{it.numberOfCubes()})
    }

    @Test
    @Order(5)
    fun `Test Cuboid 1`() {
        val inp = listOf("on x=10..12,y=10..12,z=10..12",
            "on x=11..13,y=11..13,z=11..13",
            "off x=9..11,y=9..11,z=9..11",
            "on x=10..10,y=10..10,z=10..10")
            val inpCuboids = mutableListOf<Cuboid>()
        is3D = true
        inp.forEach { inpCuboids.add(processLine(it)) }
        val reactor = Reactor()
        for (newCube in inpCuboids) {
            reactor.processCuboid(newCube)
            println("applied ${newCube} ${newCube.state}")
            println("number of cubes on: ${reactor.numberOfCubes()}")
        }
        assertEquals(39, reactor.numberOfCubes())
    }

    @Test
    @Order(6)
    fun `Test Full Sequence - part 1`() {
        is3D = true
        part1_2 = 1
        val inpCuboids = getInput(arrayOf("src/test/resources/day22/input.txt"))
        val myResult = calculateTotal(inpCuboids)
        println("final number of cubes on: $myResult")
        assertEquals(590784, myResult)
    }

    @Test
    @Order(7)
    fun `Test Full Sequence - part 2`() {
        is3D = true
        part1_2 = 2
        val inpCuboids = getInput(arrayOf("src/test/resources/day22/input2.txt"))
        val myResult = calculateTotal(inpCuboids)
        println("final number of cubes on: $myResult")
        assertEquals(2758514936282235, myResult)
    }
}



