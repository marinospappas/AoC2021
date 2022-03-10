package mpdev.aoc2021.day20

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 18 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay20 {

    @Test
    @Order(1)
    fun `Test Example Img - part 1`() {
        //val myImage = getInput(arrayOf("src/test/resources/day20/inputtest.txt"))
        val myImage = getInput(arrayOf("src/test/resources/day20/input.txt"))
        println(myImage)
        println()
        println(myImage.ieaToString())
        println()
        part1_2 = 1
        val countOfLit = calculateTotalPart1(myImage)
        println("number of lit pixels: $countOfLit")
        assertEquals(35, countOfLit)
    }

    @Test
    @Order(2)
    fun `Test Example Img - part 2`() {
        val myImage = getInput(arrayOf("src/test/resources/day20/input.txt"))
        println(myImage)
        println()
        println(myImage.ieaToString())
        println()
        part1_2 = 2
        val countOfLit = calculateTotalPart1(myImage)
        println("number of lit pixels: $countOfLit")
        assertEquals(3351, countOfLit)
    }
}



