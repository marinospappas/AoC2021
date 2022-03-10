package mpdev.aoc2021.day21

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 18 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay21 {

    @Test
    @Order(1)
    fun `Test Example Img - part 1`() {
        part1_2 = 1
        result = calculateTotalPart1(arrayOf(4-1, 8-1)).toLong()
        println("result part1: $result")
        assertEquals(739785, result)
    }

    @Test
    @Order(2)
    fun `Test Example Img - part 2`() {
        part1_2 = 1
        val myResult = calculateTotalPart2(arrayOf(4-1, 8-1))
        println("result part1: $myResult")
        assertEquals(444356092776315L, myResult)
    }

}



