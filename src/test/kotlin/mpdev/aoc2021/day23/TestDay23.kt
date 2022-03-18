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
    fun `Test Example 1`() {
        val myInput = listOf(
            "#12345678901#",
            "1...........#",
            "2##B#C#B#D###",
            "3 #A#D#C#A#  ",
            "  #########  "
        )
        part1_2 = 1
        val house = HouseVisual(myInput)
        println("$house\n")
        if (house.isEmpty(_h, _d-1))
            house.moveRoom2Hall(_d, _d-1)
        else
            house.moveRoom2Hall(_d, _d+1)
        println("$house\n")
        if (house.isEmpty(_h, _d-1))
            house.moveRoom2Hall(_d, _d-1)
        else
            house.moveRoom2Hall(_d, _d+1)
        println("$house\n")
        house.moveHall2Room('D')
        println("$house\n")

        house.moveRoom2Hall(_c, _c-3)
        println("$house\n")

        house.moveRoom2Hall(_b, _b+1)
        house.moveHall2Room('C')
        println("$house\n")

        house.moveRoom2Hall(_b, _b+1)
        house.moveHall2Room('D')
        println("$house\n")

        house.moveHall2Room('B')
        house.moveRoom2Hall(_a, _a+1)
        house.moveHall2Room('B')
        println("$house\n")

        house.moveHall2Room('A')
        println("$house\n")

        println(house.energy)
        println(house.totalEnergy())

        assertEquals (12521, house.totalEnergy())
    }

    @Test
    @Order(2)
    fun `Test Part1`() {
        val myInput = listOf(
            "#12345678901#",
            "1...........#",
            "2##B#A#A#D###",
            "3 #D#C#B#C#  ",
            "  #########  "
        )
        part1_2 = 1
        val h = HouseVisual(myInput)
        println("$h\n")

        h.moveRoom2Hall(_b,1)
        h.moveRoom2Hall(_c,2)
        println("$h\n")

        h.moveRoom2Hall(_c,4)
        h.moveRoom2Hall(_b,6)
        println("$h\n")

        h.moveHall2Room('B')
        h.moveHall2Room('C')
        println("$h\n")

        h.moveRoom2Hall(_a,4)
        h.moveHall2Room('B')
        println("$h\n")

        h.moveRoom2Hall(_d,10)
        h.moveRoom2Hall(_d,8)
        println("$h\n")

        h.moveHall2Room('C')
        h.moveHall2Room('D')
        println("$h\n")

        h.moveRoom2Hall(_a,4)
        h.moveHall2Room('D')
        println("$h\n")

        h.moveHall2Room('A', 2)
        h.moveHall2Room('A', 1)
        println("$h\n")

        println(h.energy)
        println(h.totalEnergy())
        assertEquals (15237, h.totalEnergy())

    }

    @Test
    @Order(3)
    fun `Test Example Part2`() {
        val myInput = listOf(
            "#12345678901#",
            "1...........#",
            "2##B#C#B#D###",
            "3 #D#C#B#A#  ",
            "4 #D#B#A#C#  ",
            "5 #A#D#C#A#  ",
            "  #########  "
        )
        part1_2 = 2
        val h = HouseVisual(myInput)
        println("$h\n")

        h.moveRoom2Hall(_d,11)
        h.moveRoom2Hall(_d,1)
        println("$h\n")

        h.moveRoom2Hall(_c,10)
        h.moveRoom2Hall(_c,8)
        h.moveRoom2Hall(_c,2)
        println("$h\n")

        h.moveRoom2Hall(_b,6)
        h.moveHall2Room('C')
        h.moveRoom2Hall(_b,6)
        h.moveHall2Room('C')
        println("$h\n")

        h.moveRoom2Hall(_b,6)
        h.moveRoom2Hall(_b,4)
        println("$h\n")

        h.moveHall2Room('B', 6)
        h.moveHall2Room('B', 8)
        h.moveHall2Room('B', 10)
        println("$h\n")

        h.moveRoom2Hall(_d,8)
        h.moveHall2Room('C')
        println("$h\n")

        h.moveRoom2Hall(_d,10)
        println("$h\n")

        h.moveHall2Room('D')
        println("$h\n")

        h.moveRoom2Hall(_a,4)
        h.moveHall2Room('B')
        println("$h\n")

        h.moveRoom2Hall(_a,4)
        h.moveHall2Room('D')
        h.moveRoom2Hall(_a,4)
        h.moveHall2Room('D')
        println("$h\n")

        h.moveHall2Room('A', 2)
        h.moveHall2Room('A', 1)
        h.moveHall2Room('A', 10)
        println("$h\n")

        h.moveHall2Room('D')
        println("$h\n")

        println(h.energy)
        println(h.totalEnergy())
        assertEquals (44169, h.totalEnergy())
    }


    @Test
    @Order(4)
    fun `Test State ID`() {
        val myInput = listOf(
            "#12345678901#",
            "1...........#",
            "2##B#A#A#D###",
            "3 #D#C#B#A#  ",
            "4 #D#B#A#C#  ",
            "5 #D#C#B#C#  ",
            "  #########  "
        )
        part1_2 = 2
        val stateStr1 = processInput(myInput)
        println(stateStr1)
        val state1 = State(stateStr1)
        println(state1)
        println(Dijkstra.runIt(stateStr1, state1.endState))
        assertEquals(1,1)

    }
}



