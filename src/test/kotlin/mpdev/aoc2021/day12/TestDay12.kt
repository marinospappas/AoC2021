package mpdev.aoc2021.day12

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 11 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay12 {

    @Test
    @Order(1)
    fun `Test Network Map0`() {
        val myMap = getInput(arrayOf("src/test/resources/day12/input0.txt"))
        println("Network Map:\n $myMap")
        myMap.findPaths("start","end")
        println("All paths: ${myMap.allPaths}")
        assertEquals(10, myMap.allPaths.size)
    }

    @Test
    @Order(2)
    fun `Test Network Map1`() {
        val myMap = getInput(arrayOf("src/test/resources/day12/input1.txt"))
        println("Network Map:\n $myMap")
        myMap.findPaths("start","end")
        println("All paths: ${myMap.allPaths}")
        assertEquals(19, myMap.allPaths.size)
    }

    @Test
    @Order(3)
    fun `Test Network Map2`() {
        val myMap = getInput(arrayOf("src/test/resources/day12/input2.txt"))
        println("Network Map:\n $myMap")
        myMap.findPaths("start","end")
        println("All paths: ${myMap.allPaths}")
        assertEquals(226, myMap.allPaths.size)
    }

    @Test
    @Order(4)
    fun `Test Network Map0 - Part 2`() {
        val myMap = getInput(arrayOf("src/test/resources/day12/input0.txt"))
        val myResult = calculateResultPart2(myMap)
        assertEquals(36, myResult)
    }

    @Test
    @Order(5)
    fun `Test Network Map1 - Part 2`() {
        val myMap = getInput(arrayOf("src/test/resources/day12/input1.txt"))
        val myResult = calculateResultPart2(myMap)
        assertEquals(103, myResult)
    }

    @Test
    @Order(6)
    fun `Test Network Map2 - Part 2`() {
        val myMap = getInput(arrayOf("src/test/resources/day12/input2.txt"))
        val myResult = calculateResultPart2(myMap)
        assertEquals(3509, myResult)
    }
}

