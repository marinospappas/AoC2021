package mpdev.aoc2021.day19

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 18 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay19 {

    @Test
    @Order(1)
    fun `Test Example`() {
        val scannerList = getInput(arrayOf("src/test/resources/day19/input.txt"))
        val total = calculatePart1And2(scannerList)
        println("${total[0]} total beacons")
        println("${total[1]} max manhattan dist")
        assertEquals(79, total[0])
        assertEquals(3621, total[1])
    }

    @Test
    @Order(2)
    @Disabled
    fun `Test Manhattan Distance`() {
        val c1 = fullList[0]
        val c2 = fullList[1]
        println("$c1 to $c2 manh dist = ${c1.manhattanDistance(c2)}")
        println(
            fullList.maxOf { coord1 -> fullList.maxOf { coord2 -> coord1.manhattanDistance(coord2) } }
        )
    }

    val fullList = listOf(
        Coordinates(-892,524,684),
        Coordinates(-876,649,7630),
        Coordinates(-838,591,734),
        Coordinates(-789,900,-551),
        Coordinates(-739,-1745,668),
        Coordinates(-706,-3180,-659),
        Coordinates(-697,-3072,-689),
        Coordinates(-689,845,-5300),
        Coordinates(-687,-1600,5760),
        Coordinates(-661,-816,-575),
        Coordinates(-654,-3158,-753),
        Coordinates(-635,-1737,486),
        Coordinates(-631,-672,1502),
        Coordinates(-624,-1620,1868),
        Coordinates(-620,-3212,371),
        Coordinates(-618,-824,-6210),
        Coordinates(-612,-1695,1788),
        Coordinates(-601,-1648,-643),
        Coordinates(-584,868,-557),
        Coordinates(-537,-823,-458),
        Coordinates(-532,-1715,1894),
        Coordinates(-518,-1681,-600),
        Coordinates(-499,-1607,-770),
        Coordinates(-485,-357,347),
        Coordinates(-470,-3283,303),
        Coordinates(-456,-621,1527),
        Coordinates(-447,-329,3180),
        Coordinates(-430,-3130,3660),
        Coordinates(-413,-627,14690),
        Coordinates(-345,-311,3810),
        Coordinates(-36,-1284,11710),
        Coordinates(-27,-1108,-650),
        Coordinates(7,-33,-71),
        Coordinates(12,-2351,-103),
        Coordinates(26,-1119,10910),
        Coordinates(346,-2985,3420),
        Coordinates(366,-3059,397),
        Coordinates(377,-2827,367),
        Coordinates(390,-675,-793),
        Coordinates(396,-1931,-563),
        Coordinates(404,-588,-901),
        Coordinates(408,-1815,803),
        Coordinates(423,-701,434),
        Coordinates(432,-2009,850),
        Coordinates(443,580,662),
        Coordinates(455,729,728),
        Coordinates(456,-540,1869),
        Coordinates(459,-707,401),
        Coordinates(465,-695,1988),
        Coordinates(474,580,667),
        Coordinates(496,-1584,1900),
        Coordinates(497,-1838,-617),
        Coordinates(527,-524,1933),
        Coordinates(528,-643,409),
        Coordinates(534,-1912,768),
        Coordinates(544,-627,-890),
        Coordinates(553,345,-567),
        Coordinates(564,392,-477),
        Coordinates(568,-2007,-577),
        Coordinates(605,-1665,1952),
        Coordinates(612,-1593,1893),
        Coordinates(630,319,-379),
        Coordinates(686,-3108,-505),
        Coordinates(776,-3184,-501),
        Coordinates(846,-3110,-434),
        Coordinates(1135,-1161,1235),
        Coordinates(1243,-1093,1063),
        Coordinates(1660,-552,429),
        Coordinates(1693,-557,386),
        Coordinates(1735,-437,1738),
        Coordinates(1749,-1800,1813),
        Coordinates(1772,-405,1572),
        Coordinates(1776,-675,371),
        Coordinates(1779,-442,1789),
        Coordinates(1780,-1548,337),
        Coordinates(1786,-1538,337),
        Coordinates(1847,-1591,415),
        Coordinates(1889,-1729,1762),
        Coordinates(1994,-1805,1792)
    )
}



