package mpdev.aoc2021.day04

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 4 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay4 {

    val params = arrayOf("src/test/resources/day04/boards.txt","src/test/resources/day04/bingonumbers.txt","-part1")

    @Test
    @Order(1)
    fun `Test Get Boards`() {
        val expected = File(params[0]).readText()
        bingoData = getInput(params)
        assertEquals(expected, boardsToString(bingoData))
    }

    @Test
    @Order(2)
    fun `Test Get Bingo Numbers`() {
        val expected = File(params[1]).readText()
        numbers = getInputNumbers(params)
        assertEquals(expected, numbersToString(numbers))
    }

    @Test
    @Order(3)
    fun `Test First Winning Board and Score Part1`() {
        bingoData = getInput(params)
        numbers = getInputNumbers(params)
        findWinningBoard(bingoData, true)
        score = calculateScore()
        assertEquals(3, winnerBoard+1, "winner board failed")
        assertEquals(4512, score, "score failed")
    }

    @Test
    @Order(4)
    fun `Test Last Winning Board and Score Part2`() {
        bingoData = getInput(params)
        numbers = getInputNumbers(params)
        findWinningBoard(bingoData, false)
        score = calculateScore()
        assertEquals(2, winnerBoard+1, "winner board failed")
        assertEquals(1924, score, "score failed")
    }
}

