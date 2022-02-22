package mpdev.aoc2021.day14

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 14 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay14 {

    @Test
    @Order(1)
    fun `Test Input Data`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        val expected = File("src/test/resources/day14/input.txt").readText()
        println("Template: $template")
        println("Rules: \n$myPolymer")
        assertEquals(expected, "$template\n\n${myPolymer}")
    }

    @Test
    @Order(2)
    fun `Test Polymer Rules`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        val expected1 = File("src/test/resources/day14/expected.txt").readLines()[0].split(" ")[3]
        val expected2 = File("src/test/resources/day14/expected.txt").readLines()[1].split(" ")[3]
        val expected3 = File("src/test/resources/day14/expected.txt").readLines()[2].split(" ")[3]
        val expected4 = File("src/test/resources/day14/expected.txt").readLines()[3].split(" ")[3]
        println("Template: $template")
        val step1 = myPolymer.applyRules(template)
        println("Step 1: $step1")
        assertEquals(expected1, step1)
        val step2 = myPolymer.applyRules(step1)
        println("Step 2: $step2")
        assertEquals(expected2, step2)
        val step3 = myPolymer.applyRules(step2)
        println("Step 3: $step3")
        assertEquals(expected3, step3)
        val step4 = myPolymer.applyRules(step3)
        println("Step 4: $step4")
        assertEquals(expected4, step4)
    }

    @Test
    @Order(3)
    fun `Test Element Count step 10`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        var step = template
        for (i in 1..10) {
            step = myPolymer.applyRules(step)
        }
        myPolymer.countElements(step)
        println("Step 10 length: ${step.length}")
        println(myPolymer.elementCount)
        assertEquals(3073, step.length)
        assertEquals(1749, myPolymer.elementCount['B'])
        assertEquals(298, myPolymer.elementCount['C'])
        assertEquals(161, myPolymer.elementCount['H'])
        assertEquals(865, myPolymer.elementCount['N'])
    }

    @Test
    @Order(4)
    fun `Test Result Part 1`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        result = calculateResult(template, myPolymer, 10)
        println("Part 1 result: $result")
        assertEquals(1588, result)
    }

    @Test
    @Order(5)
    fun `Test Result Part 2 - 10 steps`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        result = calculateResultPart2(template, myPolymer, 10)
        println(myPolymer.pairsCount)
        println(myPolymer.elementCount)
        assertEquals(1588, result)
    }

    @Test
    @Order(6)
    fun `Test Result Part 2 - 40 steps`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        result = calculateResultPart2(template, myPolymer, 40)
        println(myPolymer.pairsCount)
        assertEquals(2188189693529L, result)
    }

    @Disabled
    @Test
    @Order(10)
    fun `Test Step Timing - String Implementation`() {
        val myPolymer = getInput(arrayOf("src/test/resources/day14/input.txt"))
        val t1 = System.currentTimeMillis()
        var s = template
        for (i in 1..20) {
            s = myPolymer.applyRules(s)
            val t2 = System.currentTimeMillis()
            val timeDiff: Double = (t2-t1)/1000.0
            println("step $i length ${s.length}  - "+"%.3f sec".format(timeDiff))
        }
    }
}

