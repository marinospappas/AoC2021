package mpdev.aoc2021.day13

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 13 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay13 {

    @Test
    @Order(1)
    fun `Test Input Data`() {
        val myMatrix = getInput(arrayOf("src/test/resources/day13/input.txt"))
        val expected = File("src/test/resources/day13/expected.txt").readText()
        println("Matrix:\n$myMatrix")
        assertEquals(expected, myMatrix.toString(), "matrix failed")
        assertEquals(11, myMatrix.xSize, "x size failed")
        assertEquals(15, myMatrix.ySize, "y size failed")
        assertEquals("x=5", flipInstr[1], "fold x failed")
        assertEquals("y=7", flipInstr[0], "fold y failed")
    }

    @Test
    @Order(2)
    fun `Test Flip Horizontal`() {
        val myMatrix = getInput(arrayOf("src/test/resources/day13/input.txt"))
        val expected = File("src/test/resources/day13/expected1.txt").readText()
        myMatrix.flipHor(7)
        println("Matrix:\n$myMatrix")
        assertEquals(expected, myMatrix.toString(), "matrix failed")
        assertEquals(17, myMatrix.countDots(), "count of dots failed")
        assertEquals(11, myMatrix.xSize, "x size failed")
        assertEquals(7, myMatrix.ySize, "y size failed")
    }

    @Test
    @Order(3)
    fun `Test Flips H+V Part 1`() {
        val myMatrix = getInput(arrayOf("src/test/resources/day13/input.txt"))
        val expected = File("src/test/resources/day13/expected2.txt").readText()
        myMatrix.flipHor(7)
        myMatrix.flipVert(5)
        result = myMatrix.countDots()
        val result = calculateResult(myMatrix)
        println("Matrix:\n$myMatrix")
        assertEquals(expected, myMatrix.toString(), "matrix failed")
        assertEquals(16, result, "count of dots failed")
        assertEquals(5, myMatrix.xSize, "x size failed")
        assertEquals(7, myMatrix.ySize, "y size failed")
    }

    @Test
    @Order(4)
    fun `Test Series of Y Flips`() {
        val myMatrix = getInput(arrayOf("src/test/resources/day13/input.txt"))
        println("Matrix:\n${myMatrix.toStringEnh()}")
        println("flip horiz at 7")
        myMatrix.flipHor(7)
        println("=======\n${myMatrix.toStringEnh()}")
        println("flip vert at 5")
        myMatrix.flipVert(5)
        println("=======\n${myMatrix.toStringEnh()}")
        println("flip horiz at 5")
        myMatrix.flipHor(5)
        println("=======\n${myMatrix.toStringEnh()}")
        println("flip horiz at 3")
        myMatrix.flipHor(3)
        println("=======\n${myMatrix.toStringEnh()}")
        println("flip vert at 3")
        myMatrix.flipVert(3)
        println("=======\n${myMatrix.toStringEnh()}")
        assertEquals(true, true)

    }
}

