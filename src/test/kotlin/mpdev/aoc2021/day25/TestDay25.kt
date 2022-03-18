package mpdev.aoc2021.day25

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 22 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay25 {

    @Test
    @Order(1)
    fun `Test Movement 1`() {
        val myMap = listOf(
            "...>...",
            ".......",
            "......>",
            "v.....>",
            "......>",
            ".......",
            "..vvv.."
        )
        val expected =
                ">......\n" +
                "..v....\n" +
                "..>.v..\n" +
                ".>.v...\n" +
                "...>...\n" +
                ".......\n" +
                "v......"
        val seaBed = SeaBed(myMap)
        println("initial map\n$seaBed")
        for (i in 1..4) {
            seaBed.map = seaBed.nextStep()
            println("step $i\n$seaBed")
        }
        assertEquals(expected, seaBed.toString())
    }

    @Test
    @Order(1)
    fun `Test Movement 2`() {
        val expected =
            "..>>v>vv..\n" +
            "..v.>>vv..\n" +
            "..>>v>>vv.\n" +
            "..>>>>>vv.\n" +
            "v......>vv\n" +
            "v>v....>>v\n" +
            "vvv.....>>\n" +
            ">vv......>\n" +
            ".>v.vv.v.."
        val seaBed = getInput(arrayOf("src/test/resources/day25/input.txt"))
        println("initial map\n$seaBed")
        val myResult = calculatePart1(seaBed)
        println("number of steps: $myResult final map\n$seaBed")
        assertEquals(expected, seaBed.toString())
        assertEquals(58, myResult)
    }
}



