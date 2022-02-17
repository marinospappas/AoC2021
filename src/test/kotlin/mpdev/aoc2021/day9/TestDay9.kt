package mpdev.aoc2021.day9

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 9 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay9 {

    @Test
    @Order(1)
    fun `Test Height Map`() {
        input = getInput(arrayOf("src/test/resources/day9/input.txt"))
        val expected = File("src/test/resources/day9/input.txt").readText()
        assertEquals(expected, input.toString(), "height map failed")
        assertEquals(10, input.xSize, "X size failed")
        assertEquals(5, input.ySize, "X size failed")
    }

    @Test
    @Order(2)
    fun `Test Number of Low Points`() {
        input = getInput(arrayOf("src/test/resources/day9/input.txt"))
        println(input.getNeighbours(HeightMap.CoOrds(1,0)))
        println(input.getNeighbours(HeightMap.CoOrds(9,0)))
        println(input.getNeighbours(HeightMap.CoOrds(2,2)))
        println(input.getNeighbours(HeightMap.CoOrds(6,4)))
        assertEquals(4, input.getLowPoints(), "number of low points failed")
    }

    @Test
    @Order(3)
    fun `Test Total Level of Risk`() {
        input = getInput(arrayOf("src/test/resources/day9/input.txt"))
        input.getLowPoints()
        assertEquals(15, input.riskLevel, "risk level failed")
    }

    @Test
    @Order(4)
    fun `Test Basins`() {
        input = getInput(arrayOf("src/test/resources/day9/input.txt"))
        input.getBasins()
        println ("number of basins = ${input.basins.size}")
        assertEquals(4, input.basins.size, "basins size failed")
        assertEquals(14, input.basins[0].points.size, "basin[0] failed")
        assertEquals(9, input.basins[1].points.size, "basin[0] failed")
        assertEquals(9, input.basins[2].points.size, "basin[0] failed")
        assertEquals(3, input.basins[3].points.size, "basin[0] failed")
    }

    @Test
    @Order(5)
    fun `Test Result Part2`() {
        input = getInput(arrayOf("src/test/resources/day9/input.txt"))
        result = calculateResultPart2(input)
        assertEquals(1134, result)
    }
}

