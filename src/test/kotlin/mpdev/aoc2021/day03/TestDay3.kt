package mpdev.aoc2021.day03

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 3 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay3 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf("00100", "11110", "10110", "10111", "10101",
            "01111", "00111", "11100", "10000", "11001", "00010", "01010")
        diagnosticData = getInput(arrayOf("src/test/resources/day03/input.txt"))
        println("data word length: $dataLength")
        assertEquals(expected, diagnosticData)

    }

    @Test
    @Order(2)
    fun `Test Process Diagnostics Part 1`() {
        val expected1 = Integer.parseInt("10110", 2)
        val expected2 = Integer.parseInt("01001", 2)
        diagnosticData = getInput(arrayOf("src/test/resources/day03/input.txt"))
        result = processDiagPart1(diagnosticData)
        assertEquals(expected1, result.epsilon, "epsilon failed")
        assertEquals(expected2, result.gamma, "gama failed")
    }

    @Test
    @Order(3)
    fun `Test Produce Answer Part 1`() {
        diagnosticData = getInput(arrayOf("src/test/resources/day03/input.txt"))
        result = processDiagPart1(diagnosticData)
        powerConsum = produceAnswerPart1(result)
        assertEquals(198, powerConsum)
    }

    @Test
    @Order(4)
    fun `Test Process Diagnostics Part 2`() {
        val expected1 = Integer.parseInt("10111", 2)
        val expected2 = Integer.parseInt("01010", 2)
        diagnosticData = getInput(arrayOf("src/test/resources/day03/input.txt"))
        result2 = processDiagPart2(diagnosticData)
        assertEquals(expected1, result2.oxygenRating, "oxygen rating failed")
        assertEquals(expected2, result2.co2Rating, "co2 rating failed")
    }

    @Test
    @Order(5)
    fun `Test Produce Answer Part 2`() {
        diagnosticData = getInput(arrayOf("src/test/resources/day03/input.txt"))
        result2 = processDiagPart2(diagnosticData)
        survivalRate = produceAnswerPart2(result2)
        assertEquals(230, survivalRate)
    }
}

