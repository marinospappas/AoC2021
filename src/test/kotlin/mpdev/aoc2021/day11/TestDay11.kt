package mpdev.aoc2021.day11

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 11 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay11 {

    @Test
    @Order(1)
    fun `Test Data Map1`() {
        val expected0 = File("src/test/resources/day11/input0.txt").readText()
        val myMap = getInput(arrayOf("src/test/resources/day11/input0.txt"))
        println(myMap)
        assertEquals(expected0, myMap.toString())
    }

    @Test
    @Order(2)
    fun `Test Data Map1 Step1-2-3`() {
        val expected1 = File("src/test/resources/day11/input01.txt").readText()
        val myMap = getInput(arrayOf("src/test/resources/day11/input0.txt"))
        myMap.processDataMapCycle()
        println(myMap)
        assertEquals(expected1, myMap.toString())
        val expected2 = File("src/test/resources/day11/input02.txt").readText()
        myMap.processDataMapCycle()
        println()
        println(myMap)
        assertEquals(expected2, myMap.toString())
    }

    @Test
    @Order(3)
    fun `Test Data Map2 Step1-2`() {
        val expected1 = File("src/test/resources/day11/input10_1.txt").readText()
        val myMap = getInput(arrayOf("src/test/resources/day11/input10.txt"))
        println(myMap)
        myMap.processDataMapCycle()
        println()
        println(myMap)
        assertEquals(expected1, myMap.toString())
        val expected2 = File("src/test/resources/day11/input10_2.txt").readText()
        myMap.processDataMapCycle()
        println()
        println(myMap)
        assertEquals(expected2, myMap.toString())
    }


    @Test
    @Order(4)
    fun `Test Data Map2 Step10`() {
        val myMap = getInput(arrayOf("src/test/resources/day11/input10.txt"))
        val expected10 = File("src/test/resources/day11/input10_10.txt").readText()
        var flagsCount = 0
        for (i in 1..10) {
            myMap.processDataMapCycle()
            flagsCount += myMap.getFlagsCount()
        }
        println(myMap)
        assertEquals(expected10, myMap.toString())
        assertEquals(204, flagsCount)
    }

    @Test
    @Order(5)
    fun `Test Data Map2 Step100 Part 1`() {
        val myMap = getInput(arrayOf("src/test/resources/day11/input10.txt"))
        val count = calculateResultPart1(myMap)
        assertEquals(1656, count)
    }

    @Test
    @Order(5)
    fun `Test Data Map2 Part 2`() {
        val myMap = getInput(arrayOf("src/test/resources/day11/input10.txt"))
        val count = calculateResultPart2(myMap)
        println(myMap)
        assertEquals(195, count)
    }

}

