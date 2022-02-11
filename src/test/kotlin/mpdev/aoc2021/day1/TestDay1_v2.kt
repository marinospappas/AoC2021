package mpdev.aoc2021.day1

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 1 - Part 1 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay1_v2 {

    @Test
    @Order(1)
    fun `Test Answer Part 1`() {
        totalDeeper = processInputPart1(arrayOf("src/test/resources/day1/input.txt"))
        assertEquals(7, totalDeeper)
    }
}