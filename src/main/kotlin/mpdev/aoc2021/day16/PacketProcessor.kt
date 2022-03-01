package mpdev.aoc2021.day16

object PacketProcessor {

    /** calculate sum of versions */
    fun getSumOfVersions(packet: Packet): Int {
        var sumOfV = packet.version
        if (packet.type != 4) {
            for (pack in (packet.data as Operation).p)
                sumOfV += getSumOfVersions(pack)
        }
        return sumOfV
    }

    /** process packet */
    fun processPacket(packet: Packet): ULong {
        return when(packet.type) {
            4 -> (packet.data as Literal).n
            0 -> processSum(packet)
            1 -> processProduct(packet)
            2 -> processMin(packet)
            3 -> processMax(packet)
            5 -> processGT(packet)
            6 -> processLT(packet)
            7 -> processEQ(packet)
            else -> 0u
        }
    }

    /** process sum */
    private fun processSum(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        var result: ULong = 0u
        packets.forEach { result += processPacket(it) }
        return result
    }
    /** process prod */
    private fun processProduct(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        var result: ULong = 1u
        packets.forEach { result *= processPacket(it) }
        return result
    }
    /** process min */
    private fun processMin(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        var result: ULong = ULong.MAX_VALUE
        packets.forEach { val n = processPacket(it); if (n < result) result = n }
        return result
    }
    /** process max */
    fun processMax(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        var result: ULong = 0u
        packets.forEach { val n = processPacket(it); if (n > result) result = n }
        return result
    }
    /** process gt */
    private fun processGT(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        return if (processPacket(packets[0]) > processPacket(packets[1]))
            1u
        else
            0u
    }
    /** process lt */
    private fun processLT(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        return if (processPacket(packets[0]) < processPacket(packets[1]))
            1u
        else
            0u
    }
    /** process eq */
    private fun processEQ(packet: Packet): ULong {
        val packets = (packet.data as Operation).p
        return if (processPacket(packets[0]) == processPacket(packets[1]))
            1u
        else
            0u
    }
}