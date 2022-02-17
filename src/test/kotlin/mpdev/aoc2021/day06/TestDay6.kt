package mpdev.aoc2021.day06

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 6 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay6 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = File("src/test/resources/day06/input.txt").readText()
        val input = getInput(arrayOf("src/test/resources/day06/input.txt"))
        assertEquals(expected, arrayToString(input))
    }

    @Test
    @Order(2)
    fun `Test 18-days Result`() {
        val input = getInput(arrayOf("src/test/resources/day06/input.txt"))
        val fishAges = Array(MAX_AGE+1) {0L}
        processInput(input, fishAges, days=18)
        val result = calculateResult(fishAges)
        assertEquals(26, result)
    }

    @Test
    @Order(3)
    fun `Test 80-days Result`() {
        val input = getInput(arrayOf("src/test/resources/day06/input.txt"))
        val fishAges = Array(MAX_AGE+1) {0L}
        processInput(input, fishAges, days=80)
        val result = calculateResult(fishAges)
        assertEquals(5934, result)
    }

    @Test
    @Order(4)
    fun `Test 256-days Result (Part 2)`() {
        val input = getInput(arrayOf("src/test/resources/day06/input.txt"))
        val fishAges = Array(MAX_AGE+1) {0L}
        processInput(input, fishAges, days=256)
        val result = calculateResult(fishAges)
        assertEquals(26984457539, result)
    }

}
