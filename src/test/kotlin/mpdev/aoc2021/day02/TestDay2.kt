package mpdev.aoc2021.day02

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 2 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay2 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected1 = listOf("forward", "down", "forward", "up", "down", "forward")
        val expected2 = listOf(5, 5, 8, 3, 8, 2)
        commandData = getInput(arrayOf("src/test/resources/day02/input.txt"))
        assertEquals(expected1, getCmds(commandData))
        assertEquals(expected2, getCmdArgs(commandData))
    }

    @Test
    @Order(2)
    fun `Test Process Commands Part 1`() {
        commandData = getInput(arrayOf("src/test/resources/day02/input.txt"))
        result = processCommands(commandData, { r, n -> r.totalDepth += n }, { r, n -> r.totalFwd += n } )
        assertEquals(15, result.totalFwd)
        assertEquals(10, result.totalDepth)
    }

    @Test
    @Order(3)
    fun `Test Answer Part 1`() {
        commandData = getInput(arrayOf("src/test/resources/day02/input.txt"))
        result = processCommands(commandData, { r, n -> r.totalDepth += n }, { r, n -> r.totalFwd += n } )
        product = produceAnswer(result)
        assertEquals(150, product)
    }

    @Test
    @Order(4)
    fun `Test Process Commands Part 2`() {
        commandData = getInput(arrayOf("src/test/resources/day02/input.txt"))
        result = processCommands(commandData, { r, n -> r.aim += n }, { r, n -> r.totalFwd += n; r.totalDepth += r.aim*n })
        assertEquals(15, result.totalFwd)
        assertEquals(60, result.totalDepth)
    }

    @Test
    @Order(5)
    fun `Test Answer Part 2`() {
        commandData = getInput(arrayOf("src/test/resources/day02/input.txt"))
        result = processCommands(commandData, { r, n -> r.aim += n }, { r, n -> r.totalFwd += n; r.totalDepth += r.aim*n })
        product = produceAnswer(result)
        assertEquals(900, product)
    }
}

