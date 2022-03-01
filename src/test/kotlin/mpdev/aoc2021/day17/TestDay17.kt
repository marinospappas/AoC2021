package mpdev.aoc2021.day17

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 17 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay17 {

    @Test
    @Order(1)
    fun `Test Max Height - part 1`() {
        val b = Ballistics(20, 30, -10,  -5)
        val myResult = calculateResultPart1(b)
        println("max height $myResult")
        assertEquals(45, myResult)
    }

    @Test
    @Order(2)
    fun `Test Number of Good Velocities - part 2`() {
        val b = Ballistics(20, 30, -10,  -5)
        val myResult = calculateResultPart2(b)
        println("number of good velocities $myResult")
        assertEquals(112, myResult)
    }


    @Test
    @Order(3)
    fun `Test x,y and speed y`() {
        //val b = Ballistics(20, 30, -10,  -5)
        val b = Ballistics(2277, 2318, -3092, -1553)
        b.curVelocity = Velocity(2300,-2000)
        val myResult = b.hitTarget()
        println("result: $myResult pos: ${b.curPosition}")
        assertEquals(112, 112)
    }
}



