package mpdev.aoc2021.day08

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 8 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay8 {

    @Test
    @Order(1)
    fun `Test Calculate 1-4-7-8 Total Part 1`() {
        result = calculateTotalPart1(arrayOf("src/test/resources/day08/input.txt")) { l -> l <= 4 || l == 7 }
        assertEquals(26, result)
    }

    @Test
    @Order(2)
    fun `Test Get Segment Flag`() {
        val seg7 = SevenSegmentDisplay()
        val f1 = seg7.getSegmentHexValue('A')
        val f2 = seg7.getSegmentHexValue('B')
        val f3 = seg7.getSegmentHexValue('C')
        val f4 = seg7.getSegmentHexValue('D')
        val f5 = seg7.getSegmentHexValue('E')
        val f6 = seg7.getSegmentHexValue('F')
        val f7 = seg7.getSegmentHexValue('G')
        assertEquals( 64, f1, "A flag failed")
        assertEquals( 32, f2, "B flag failed")
        assertEquals( 16, f3, "C flag failed")
        assertEquals(  8, f4, "D flag failed")
        assertEquals(  4, f5, "E flag failed")
        assertEquals(  2, f6, "F flag failed")
        assertEquals(  1, f7, "G flag failed")
    }

    @Test
    @Order(3)
    fun `Test Segment to Number (Int)`() {
        val seg7 = SevenSegmentDisplay()
        val n0 = seg7.convertSegmentsToNumber(0x77)
        val n1 = seg7.convertSegmentsToNumber(0x12)
        val n2 = seg7.convertSegmentsToNumber(0x5d)
        val n3 = seg7.convertSegmentsToNumber(0x5b)
        val n4 = seg7.convertSegmentsToNumber(0x3a)
        val n5 = seg7.convertSegmentsToNumber(0x6b)
        val n6 = seg7.convertSegmentsToNumber(0x6f)
        val n7 = seg7.convertSegmentsToNumber(0x52)
        val n8 = seg7.convertSegmentsToNumber(0x7f)
        val n9 = seg7.convertSegmentsToNumber(0x7b)
        assertEquals( 0, n0, "0 failed")
        assertEquals( 1, n1, "1 failed")
        assertEquals( 2, n2, "2 failed")
        assertEquals( 3, n3, "3 failed")
        assertEquals( 4, n4, "4 failed")
        assertEquals( 5, n5, "5 failed")
        assertEquals( 6, n6, "6 failed")
        assertEquals( 7, n7, "7 failed")
        assertEquals( 8, n8, "8 failed")
        assertEquals( 9, n9, "9 failed")
    }

    @Test
    @Order(4)
    fun `Test Segment to Number (String)`() {
        val seg7 = SevenSegmentDisplay()
        val n0 = seg7.convertSegmentsToNumber("abcefg")
        val n1 = seg7.convertSegmentsToNumber("cf")
        val n2 = seg7.convertSegmentsToNumber("acdeg")
        val n3 = seg7.convertSegmentsToNumber("acfgd")
        val n4 = seg7.convertSegmentsToNumber("bdcf")
        val n5 = seg7.convertSegmentsToNumber("abdfg")
        val n6 = seg7.convertSegmentsToNumber("abegfd")
        val n7 = seg7.convertSegmentsToNumber("acf")
        val n8 = seg7.convertSegmentsToNumber("acfgebd")
        val n9 = seg7.convertSegmentsToNumber("DBACFG")
        assertEquals( 0, n0, "0 failed")
        assertEquals( 1, n1, "1 failed")
        assertEquals( 2, n2, "2 failed")
        assertEquals( 3, n3, "3 failed")
        assertEquals( 4, n4, "4 failed")
        assertEquals( 5, n5, "5 failed")
        assertEquals( 6, n6, "6 failed")
        assertEquals( 7, n7, "7 failed")
        assertEquals( 8, n8, "8 failed")
        assertEquals( 9, n9, "9 failed")
    }

    @Test
    @Order(5)
    fun `Test Segments Map`() {
        val inputLine = getInput(arrayOf("src/test/resources/day08/input1.txt")).readLines().first()
        val seg7 = SevenSegmentDisplay(processLine(inputLine).mappings)
        println(seg7.getSegmentsMap())
        assertEquals( 1, seg7.convertMappedSegmentsToNumber("ab"), "1 failed")
        assertEquals( 2, seg7.convertMappedSegmentsToNumber("gcdfa"), "2 failed")
        assertEquals( 3, seg7.convertMappedSegmentsToNumber("fbcad"), "3 failed")
        assertEquals( 4, seg7.convertMappedSegmentsToNumber("eafb"), "4 failed")
        assertEquals( 5, seg7.convertMappedSegmentsToNumber("cdfbe"), "5 failed")
        assertEquals( 6, seg7.convertMappedSegmentsToNumber("cdfgeb"), "6 failed")
        assertEquals( 7, seg7.convertMappedSegmentsToNumber("dab"), "7 failed")
        assertEquals( 8, seg7.convertMappedSegmentsToNumber("abcdefg"), "8 failed")
        assertEquals( 9, seg7.convertMappedSegmentsToNumber("cefadb"), "9 failed")
        assertEquals( 0, seg7.convertMappedSegmentsToNumber("cagedb"), "0 failed")
    }

    @Test
    @Order(6)
    fun `Test Measurement`() {
        val inputLine = getInput(arrayOf("src/test/resources/day08/input1.txt")).readLines().first()
        val seg7 = SevenSegmentDisplay(processLine(inputLine).mappings)
        val measurements = processLine(inputLine).measurements
        assertEquals( 5, seg7.convertMappedSegmentsToNumber(measurements[0]), "1st digit failed")
        assertEquals( 3, seg7.convertMappedSegmentsToNumber(measurements[1]), "2nd digit failed")
        assertEquals( 5, seg7.convertMappedSegmentsToNumber(measurements[2]), "3rd digit failed")
        assertEquals( 3, seg7.convertMappedSegmentsToNumber(measurements[3]), "4th digit failed")
    }

    @Test
    @Order(7)
    fun `Test Calculate Result Part 2`() {
        result = calculateResultPart2(arrayOf("src/test/resources/day08/input.txt"))
        assertEquals(61229, result)
    }
}

