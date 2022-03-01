package mpdev.aoc2021.day16

import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 16 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay16 {

    @Test
    @Order(1)
    fun `Test Literal Packet 1`() {
        val myBitSet = processInput("D2FE28")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        assertEquals(6, myPacket.version)
        assertEquals(4, myPacket.type)
        assertEquals(24, myPacket.getLength())
        assertEquals(2021u, (myPacket.data as Literal).n)
    }

    @Test
    @Order(2)
    fun `Test Operation Packet - total length`() {
        val myBitSet = processInput("38006F45291200")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        assertEquals(1, myPacket.version, "main packet version")
        assertEquals(6, myPacket.type, "main packet type")
        assertEquals(56, myPacket.getLength(), "main packet length")
        assertEquals(10u, ((myPacket.data as Operation).p[0].data as Literal).n, "sub packet 0 value")
        assertEquals(6, (myPacket.data as Operation).p[0].version, "sub packet 0 version")
        assertEquals(20u, ((myPacket.data as Operation).p[1].data as Literal).n, "sub packet 1 value")
        assertEquals(2, (myPacket.data as Operation).p[1].version, "sub packet 1 version")
    }

    @Test
    @Order(3)
    fun `Test Operation Packet - number of packets`() {
        val myBitSet = processInput("EE00D40C823060")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        assertEquals(7, myPacket.version, "main packet version")
        assertEquals(3, myPacket.type, "main packet type")
        assertEquals(56, myPacket.getLength(), "main packet length")
        assertEquals(1u, ((myPacket.data as Operation).p[0].data as Literal).n, "sub packet 0 value")
        assertEquals(2u, ((myPacket.data as Operation).p[1].data as Literal).n, "sub packet 1 value")
        assertEquals(3u, ((myPacket.data as Operation).p[2].data as Literal).n, "sub packet 2 value")
    }

    @Test
    @Order(4)
    fun `Test Sum of Versions 1`() {
        val myBitSet = processInput(File("src/test/resources/day16/input.txt").readLines()[0])
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        println("sum of versions 1 ${PacketProcessor.getSumOfVersions(myPacket)}")
        assertEquals(16, PacketProcessor.getSumOfVersions(myPacket), "sum of version 1")
    }

    @Test
    @Order(5)
    fun `Test Sum of Versions 2`() {
        val myBitSet = processInput(File("src/test/resources/day16/input.txt").readLines()[1])
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        println("sum of versions 2 ${PacketProcessor.getSumOfVersions(myPacket)}")
        assertEquals(12, PacketProcessor.getSumOfVersions(myPacket), "sum of version 2")
    }

    @Test
    @Order(6)
    fun `Test Sum of Versions 3`() {
        val myBitSet = processInput(File("src/test/resources/day16/input.txt").readLines()[2])
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        println("sum of versions 2 ${PacketProcessor.getSumOfVersions(myPacket)}")
        assertEquals(23, PacketProcessor.getSumOfVersions(myPacket), "sum of version 2")
    }

    @Test
    @Order(7)
    fun `Test Sum of Versions 4`() {
        val myBitSet = processInput(File("src/test/resources/day16/input.txt").readLines()[3])
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        println("sum of versions 3 ${PacketProcessor.getSumOfVersions(myPacket)}")
        assertEquals(31, PacketProcessor.getSumOfVersions(myPacket), "sum of version 3")
    }

    @Test
    @Order(8)
    fun `Test Sum`() {
        val myBitSet = processInput("C200B40A82")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of sum $myResult")
        assertEquals(3u, myResult)
    }

    @Test
    @Order(9)
    fun `Test Product`() {
        val myBitSet = processInput("04005AC33890")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of prod $myResult")
        assertEquals(54u, myResult)
    }

    @Test
    @Order(10)
    fun `Test Min`() {
        val myBitSet = processInput("880086C3E88112")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of min $myResult")
        assertEquals(7u, myResult)
    }

    @Test
    @Order(10)
    fun `Test Max`() {
        val myBitSet = processInput("CE00C43D881120")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of max $myResult")
        assertEquals(9u, myResult)
    }

    @Test
    @Order(11)
    fun `Test GT`() {
        val myBitSet = processInput("D8005AC2A8F0")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of gt $myResult")
        assertEquals(1u, myResult)
    }

    @Test
    @Order(12)
    fun `Test LT`() {
        val myBitSet = processInput("F600BC2D8F")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of lt $myResult")
        assertEquals(0u, myResult)
    }

    @Test
    @Order(13)
    fun `Test EQ`() {
        val myBitSet = processInput("9C005AC2F8F0")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of eq $myResult")
        assertEquals(0u, myResult)
    }

    @Test
    @Order(14)
    fun `Test Process Packet`() {
        val myBitSet = processInput("9C0141080250320F1802104A08")
        val p = PacketHandler(myBitSet)
        val myPacket = p.readMessage()
        println(myPacket)
        val myResult = PacketProcessor.processPacket(myPacket)
        println("result of processing $myResult")
        assertEquals(1u, myResult)
    }
}



