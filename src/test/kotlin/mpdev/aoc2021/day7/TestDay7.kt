package mpdev.aoc2021.day7

import org.junit.jupiter.api.*
import java.io.File
import java.lang.StrictMath.abs
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 7 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay7 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = File("src/test/resources/day7/input.txt").readText()
        val input = getInput(arrayOf("src/test/resources/day7/input.txt"))
        assertEquals(expected, arrayToString(input))
    }

    @Test
    @Order(2)
    fun `Test Calculate Costs for Each Position Part 1`() {
        positions = getInput(arrayOf("src/test/resources/day7/input.txt"))
        part1_2 = 1
        val costs = calulcateCosts(positions) { a, b -> abs(a - b) }
        println(arrayToString(costs))
        val leastCost = calculateResult(costs)
        assertEquals(37, leastCost, "Least Cost failed")
        assertEquals(2, cheapestPosition, "Position of Least Cost failed")
        assertEquals(41, costs[1 - basePosition], "Cost for Posn 1 failed")
        assertEquals(39, costs[3 - basePosition], "Cost for Posn 3 failed")
        assertEquals(71, costs[10 - basePosition], "Cost for Posn 10 failed")
    }

    @Test
    @Order(3)
    fun `Test Calculate Costs for Each Position Part 2`() {
        positions = getInput(arrayOf("src/test/resources/day7/input.txt"))
        part1_2 = 2
        calculateUpfrontCosts()
        val costs = calulcateCosts(positions) { a, b -> part2Costs[abs(a-b)] }
        println(arrayToString(costs))
        val leastCost = calculateResult(costs)
        assertEquals(168, leastCost, "Least Cost failed")
        assertEquals(5, cheapestPosition, "Position of Least Cost failed")
        assertEquals(206, costs[2 - basePosition], "Cost for Posn 1 failed")
    }
}
