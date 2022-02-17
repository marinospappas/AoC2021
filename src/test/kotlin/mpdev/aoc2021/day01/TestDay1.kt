package mpdev.aoc2021.day01

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 1 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay1 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(199,200,208,210,200,207,240,269,260,263)
        depthData = getInput(arrayOf("src/test/resources/day01/input.txt"))
        assertEquals(expected, getDepths(depthData))
    }

    @Test
    @Order(2)
    fun `Test Process Depths Part 1`() {
        val expected = listOf("N/A", "increased", "increased", "increased" , "decreased",
                "increased", "increased", "increased", "decreased", "increased")
        depthData = getInput(arrayOf("src/test/resources/day01/input.txt"))
        processDepths(depthData)
        assertEquals(expected, getIsDeeper(depthData))
    }

    @Test
    @Order(3)
    fun `Test Answer Part 1`() {
        depthData = getInput(arrayOf("src/test/resources/day01/input.txt"))
        processDepths(depthData)
        totalDeeper = produceAnswer(depthData)
        assertEquals(7, totalDeeper)
    }

    @Test
    @Order(4)
    fun `Test Running Sum Part 2`() {
        val expected = listOf(607,618,618,617,647,716,769,792)
        depthData = getInput(arrayOf("src/test/resources/day01/input.txt"))
        depthRunSum = processRunSum(depthData)
        assertEquals(expected, getDepths(depthRunSum))
    }

    @Test
    @Order(5)
    fun `Test Process Depths Part 2`() {
        val expected = listOf("N/A", "increased", "decreased", "decreased" , "increased",
            "increased", "increased", "increased")
        depthData = getInput(arrayOf("src/test/resources/day01/input.txt"))
        depthRunSum = processRunSum(depthData)
        processDepths(depthRunSum)
        assertEquals(expected, getIsDeeper(depthRunSum))
    }

    @Test
    @Order(6)
    fun `Test Answer Part 2`() {
        depthData = getInput(arrayOf("src/test/resources/day01/input.txt"))
        depthRunSum = processRunSum(depthData)
        processDepths(depthRunSum)
        totalDeeper = produceAnswer(depthRunSum)
        assertEquals(5, totalDeeper)
    }
}