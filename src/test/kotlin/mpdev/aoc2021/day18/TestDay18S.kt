package mpdev.aoc2021.day18

import mpdev.aoc2021.day01.processInputPart1
import mpdev.aoc2021.day16.processInput
import org.junit.jupiter.api.*
import kotlin.math.exp
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 18 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay18S {

    @Test
    @Order(1)
    fun `Test Tokenise`() {
        val tokenList = StringsMaths.tokenise("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
        val listToString = StringsMaths.toString(tokenList)
        println("token list: $listToString")
        assertEquals("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]", listToString)
    }

    @Test
    @Order(2)
    fun `Test Depth`() {
        val tokenList = StringsMaths.tokenise("[[[0,[4,[5,6]]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
        val listDepth = StringsMaths.depth(tokenList)
        println("token list: ${StringsMaths.toString(tokenList)}, depth: $listDepth")
        assertEquals(4, listDepth)
    }

    @Test
    @Order(3)
    fun `Test Explode`() {
        val tokenList = StringsMaths.tokenise("[[[0,[4,[5,6]]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
        val listDepth = StringsMaths.depth(tokenList)
        println("token list: ${StringsMaths.toString(tokenList)}, depth: $listDepth")
        val exploded = StringsMaths.explode(tokenList)
        println("exploded: ${StringsMaths.toString(exploded)}")
        assertEquals(4, listDepth)
    }
}


