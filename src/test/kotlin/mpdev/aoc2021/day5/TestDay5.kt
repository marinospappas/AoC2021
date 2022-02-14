package mpdev.aoc2021.day5

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 4 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay5 {

    val s = arrayOf("src/test/resources/day4/boards.txt")

    @Test
    @Order(1)
    fun `Test Process Input Part 1`() {
        val expected = File("src/test/resources/day5/expected1.txt").readText()
        val input = getInput(arrayOf("src/test/resources/day5/input.txt"))
        canvas = Canvas()
        processInput(input, canvas, false)
        assertEquals(expected, canvas.toString())
    }

    @Test
    @Order(2)
    fun `Test Calculate Result Part 1`() {
        val input = getInput(arrayOf("src/test/resources/day5/input.txt"))
        canvas = Canvas()
        processInput(input, canvas, false)
        println(canvas)
        result = calculateResult(canvas)
        assertEquals(5, result)
    }

    @Test
    @Order(3)
    fun `Test Process Input Part 2`() {
        val expected = File("src/test/resources/day5/expected2.txt").readText()
        val input = getInput(arrayOf("src/test/resources/day5/input.txt"))
        canvas = Canvas()
        processInput(input, canvas, true)
        assertEquals(expected, canvas.toString())
    }

    @Test
    @Order(4)
    fun `Test Calculate Result Part 2`() {
        val input = getInput(arrayOf("src/test/resources/day5/input.txt"))
        canvas = Canvas()
        processInput(input, canvas, true)
        println(canvas)
        result = calculateResult(canvas)
        assertEquals(12, result)
    }

}

