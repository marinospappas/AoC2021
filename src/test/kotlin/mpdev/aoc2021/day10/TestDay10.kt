package mpdev.aoc2021.day10

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 10 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay10 {

    @Test
    @Order(1)
    fun `Test Error 1`() {
        val input1 = File("src/test/resources/day10/input.txt").readLines()[2]
        val p = Parser(input1)
        try {
            p.parseBlock()
        }
        catch (e: ParserException) {
            assertEquals(Error.illegCurl, e.error)
            assertEquals(1197, e.score)
        }
    }

    @Test
    @Order(2)
    fun `Test Error 2`() {
        val input1 = File("src/test/resources/day10/input.txt").readLines()[4]
        val p = Parser(input1)
        try {
            p.parseBlock()
        }
        catch (e: ParserException) {
            assertEquals(Error.illegParen, e.error)
            assertEquals(3, e.score)
        }
    }

    @Test
    @Order(3)
    fun `Test Error 3`() {
        val input1 = File("src/test/resources/day10/input.txt").readLines()[5]
        val p = Parser(input1)
        try {
            p.parseBlock()
        }
        catch (e: ParserException) {
            assertEquals(Error.illegSq, e.error)
            assertEquals(57, e.score)
        }
    }

    @Test
    @Order(4)
    fun `Test Error 4`() {
        val input1 = File("src/test/resources/day10/input.txt").readLines()[8]
        val p = Parser(input1)
        try {
            p.parseBlock()
        }
        catch (e: ParserException) {
            assertEquals(Error.illegTr, e.error)
            assertEquals(25137, e.score)
        }
    }

    @Test
    @Order(5)
    fun `Test Total Error Score Part 1`() {
        val myResult = calculateErrorScore(arrayOf("src/test/resources/day10/input.txt"))
        assertEquals(26397, myResult)
    }

    @Test
    @Order(6)
    fun `Test AutoComplete String 1`() {
        val input1 = File("src/test/resources/day10/input.txt").readLines()[0]
        val p = Parser(input1)
        var err = Error.OK
        try {
            p.parseBlock(autoComplete = true)
        }
        catch (e: ParserException) {
            err = e.error
        }
        assertEquals("}}]])})]", p.autoCompleteString)
        assertEquals(288957, p.getAutoCompleteScore())
        assertEquals(Error.OK, err)
    }

    @Test
    @Order(7)
    fun `Test AutoComplete String 2`() {
        val input1 = File("src/test/resources/day10/input.txt").readLines()[1]
        val p = Parser(input1)
        var err = Error.OK
        try {
            p.parseBlock(autoComplete = true)
        }
        catch (e: ParserException) {
            err = e.error
        }
        assertEquals(")}>]})", p.autoCompleteString)
        assertEquals(5566, p.getAutoCompleteScore())
        assertEquals(Error.OK, err)
    }

    @Test
    @Order(8)
    fun `Test Middle Auto Correct Score Part 2`() {
        val myScores = calculateParsingScore(arrayOf("src/test/resources/day10/input.txt"))
        result = calculateAutoCorrectScore(myScores)
        assertEquals(288957, result)
    }

}

