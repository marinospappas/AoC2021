package mpdev.aoc2021.day23

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 22 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay23 {

    @Test
    @Order(1)
    fun `Test Input`() {
        val myInput = listOf(
            "#############",
            "#...........#",
            "###B#C#B#D###",
            "  #A#D#C#A#  ",
            "  #########  "
        )
        val house = Building(myInput)
        println(house)
    }
}



