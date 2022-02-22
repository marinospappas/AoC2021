package mpdev.aoc2021.day15

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 15 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay15 {

    @Test
    @Order(1)
    fun `Test Build Network`() {
        val myNetwork = getInput(arrayOf("src/test/resources/day15/input.txt"))
        val expected = File("src/test/resources/day15/input.txt").readText()
        println("Grid size: $xSize x $ySize")
        println("Number of nodes: ${myNetwork.grid.size}")
        println(myNetwork.printSquareGrid().replace("", " "))
        assertEquals(expected, myNetwork.printSquareGrid())
    }

    @Test
    @Order(2)
    fun `Test Min Cost Path - DijKstra Algorithm`() {
        val myNetwork = getInput(arrayOf("src/test/resources/day15/input.txt"))
        val minCost = myNetwork.findMinCostforAllPaths()
        val minPath = myNetwork.getMinCostPath()
        println("min cost path: $minPath")
        println(myNetwork.minCostPathToString(minPath))
        assertEquals(40, minCost, "min cost failed")
    }

    @Test
    @Order(3)
    fun `Test Build Network - Part 2`() {
        part1_2 = 2
        val myNetwork = getInput(arrayOf("src/test/resources/day15/input.txt"))
        val expected = File("src/test/resources/day15/expected1.txt").readText()
        println("Grid size: $xSize x $ySize")
        println("Number of nodes: ${myNetwork.grid.size}")
        println(myNetwork.printSquareGrid().replace("", " "))
        assertEquals(expected, myNetwork.printSquareGrid())
    }

    @Test
    @Order(4)
    fun `Test Min Cost Path - Dijkstra Algorithm - Part 2`() {
        part1_2 = 2
        val myNetwork = getInput(arrayOf("src/test/resources/day15/input.txt"))
        val minCost = myNetwork.findMinCostforAllPaths()
        val minPath = myNetwork.getMinCostPath()
        println("min cost path: $minPath")
        println(myNetwork.minCostPathToString(minPath))
        assertEquals(315, minCost, "min cost failed")
    }

}

