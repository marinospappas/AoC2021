package mpdev.aoc2021.day18

import mpdev.aoc2021.day01.processInputPart1
import mpdev.aoc2021.day16.processInput
import mpdev.aoc2021.day18.StringsMaths.magnitude
import mpdev.aoc2021.day18.StringsMaths.reduce
import org.junit.jupiter.api.*
import java.io.File
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
        var tokenList = StringsMaths.tokenise("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")
        var listDepth = StringsMaths.depth(tokenList)
        println("token list: ${StringsMaths.toString(tokenList)}, depth: $listDepth")
        var exploded = StringsMaths.explode(tokenList)
        exploded = StringsMaths.explode(exploded)
        println("exploded: ${StringsMaths.toString(exploded)}, depth: ${StringsMaths.depth(exploded)}")
        assertEquals("[[[[0,7],4],[15,[0,13]]],[1,1]]", StringsMaths.toString(exploded))

        tokenList = StringsMaths.tokenise("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]")
        listDepth = StringsMaths.depth(tokenList)
        println("token list: ${StringsMaths.toString(tokenList)}, depth: $listDepth")
        exploded = StringsMaths.explode(tokenList)
        println("exploded: ${StringsMaths.toString(exploded)}, depth: ${StringsMaths.depth(exploded)}")
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", StringsMaths.toString(exploded))
    }

    @Test
    @Order(4)
    fun `Test Split`() {
        var input = "[[[[0,7],4],[15,[0,13]]],[1,1]]"
        var tokenList = StringsMaths.tokenise(input)
        println("token list: ${StringsMaths.toString(tokenList)}")
        assertEquals(input.length-2, tokenList.size)
        var spl = StringsMaths.split(tokenList)
        spl = StringsMaths.split(spl)
        println("split: ${StringsMaths.toString(spl)}")
        assertEquals("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]", StringsMaths.toString(spl))

        input = "[[[[0,7],4],[12,[0,10]]],[1,1]]"
        tokenList = StringsMaths.tokenise(input)
        println("token list: ${StringsMaths.toString(tokenList)}")
        assertEquals(input.length-2, tokenList.size)
        spl = StringsMaths.split(tokenList)
        spl = StringsMaths.split(spl)
        println("split: ${StringsMaths.toString(spl)}")
        assertEquals("[[[[0,7],4],[[6,6],[0,[5,5]]]],[1,1]]", StringsMaths.toString(spl))
    }

    @Test
    @Order(5)
    fun `Test single Reduce add-explode-split Depth 4`() {
        val s1 = StringsMaths.tokenise("[[[[4,3],4],4],[7,[[8,4],9]]]")
        val s2 = StringsMaths.tokenise("[1,1]")
        // [[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]
        // [[[[0,7],4],[7,[[8,4],9]]],[1,1]]
        // [[[[0,7],4],[15,[0,13]]],[1,1]]
        // [[[[0,7],4],[[7,8],[0,13]]],[1,1]]
        // [[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]
        // [[[[0,7],4],[[7,8],[6,0]]],[8,1]]
        val sum = StringsMaths.add(s1, s2)
        println("sum: ${StringsMaths.toString(sum)}")
        val reduced = StringsMaths.reduce(sum)
        println("reduced: ${StringsMaths.toString(reduced)}")
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", StringsMaths.toString(reduced))
    }

    @Test
    @Order(6)
    fun `Test multiple Reduce add-explode-split Depth 5`() {
        val slist = listOf(
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]"
        )
        val expected = listOf(
            "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
            "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",
            "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]",
            "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]",
            "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]",
            "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]",
            "[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]",
            "[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]",
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        )
        var sum = StringsMaths.tokenise("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
        var reduced = sum
        for (i in slist.indices) {
            sum = StringsMaths.add(reduced, StringsMaths.tokenise(slist[i]))
            println("sum [$i]: ${StringsMaths.toString(sum)}")
            reduced = StringsMaths.reduce(sum)
            println("reduced [$i]: ${StringsMaths.toString(reduced)}")
            assertEquals(expected[i], StringsMaths.toString(reduced))
        }
    }

    @Test
    @Order(7)
    fun `Test Magnitude Examples`() {
        val input = listOf(
            "[[1,2],[[3,4],5]]",
            "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]",
            "[[[[1,1],[2,2]],[3,3]],[4,4]]",
            "[[[[3,0],[5,3]],[4,4]],[5,5]]",
            "[[[[5,0],[7,4]],[5,5]],[6,6]]",
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        )
        val expected = listOf(143, 1384, 445, 791, 1137, 3488)
        for (i in input.indices) {
            val snumber = StringsMaths.tokenise(input[i])
            //val magnitude = input[i].magnitude
            val magnitude = StringsMaths.magnitude(snumber)
            println("s-number: ${StringsMaths.toString(snumber)} magnitude $magnitude")
            assertEquals(expected[i], magnitude)
        }
    }

    @Test
    @Order(8)
    fun `Test full homework example`() {
        val input = File("src/test/resources/day18/input.txt").readLines()
        // val expSum = "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]"
        val expMagnitude = 4140
        val finalMagnitude = calculateResultPart1(input)
        println("magnitude $finalMagnitude")
        assertEquals(expMagnitude, finalMagnitude)
    }

    @Test
    @Order(9)
    fun `Test full homework example Part 2 - max magnitude`() {
        val input = File("src/test/resources/day18/input.txt").readLines()
        val expMaxMagnitude = 3993
        val finalMaxMagnitude = calculateResultPart2(input)
        println("max magnitude $finalMaxMagnitude")
        assertEquals(expMaxMagnitude, finalMaxMagnitude)
    }

}
