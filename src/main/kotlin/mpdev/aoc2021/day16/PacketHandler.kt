package mpdev.aoc2021.day16

import java.util.BitSet

class Literal(var n: ULong = 0u, var length: Int = 0)

class Operation(var p: List<Packet> = mutableListOf(), var length: Int = 0)

class Packet(var version: Int = 0, var type: Int = 0, var data: Any? = null, var padding: String = "") {

    fun getLength(): Int {
        var myLength = 3 + 3 + padding.length
        myLength += if (type == 4) (data as Literal).length else (data as Operation).length
        return myLength
    }

    override fun toString(): String {
        var s = "ver: $version typ: $type pad: $padding len: ${getLength()}"
        if (type == 4) {
            val l = data as Literal
            s += "\n    literal: ${l.n}"
        }
        else {
            val op = data as Operation
            for (i in op.p.indices) {
                s += "\nop packet $i\n"
                s += "${op.p[i]}"
            }
        }
        return s
    }
}

/**
 * <message> ::= <packet> <padding>
 *     <packet> ::= <version> <type> <data>
 *         <version> ::== <3-bits>
 *         <type> ::= <3-bits>
 *                     *** type == 4 literal else operation
 *         <data> ::= <literal> | <operation>
 *             <literal> ::= [ '1' <4-bits> ]* '0' <4-bits>
 *             <operation> ::= <id> [ <15-bits> [ <packet> ]* ] | [ <11-bits> [ <packet> ]* ]
 *                      *** id == 0 15 bits length else 11 bits number of packets
 *         <padding> ::= [ 0 ]*
 *                      *** to length mult-8
 */
class PacketHandler(var inputData: BitSet) {

    var inpBitSetindex = 0

    /** read an incoming message */
    fun readMessage(): Packet {
        val myPacket = readPacket()
        myPacket.padding = readPadding(myPacket.getLength())
        return myPacket
    }

    /** read a packet */
    private fun readPacket(): Packet {
        val myPacket = Packet()
        myPacket.version = readBits(3)
        myPacket.type = readBits(3)
        myPacket.data = readData(myPacket.type)
        return myPacket
    }

    /** read packet data */
    private fun readData(type: Int) = if (type == 4) readLiteral()  else readOperation()

    /** read a literal - unsigned long */
    private fun readLiteral(): Any {
        val myLiteral = Literal()
        do {
            val continueReading = readBits(1) == 1
            myLiteral.n = 16u * myLiteral.n + readBits(4).toULong()
            myLiteral.length += 5
        } while (continueReading)
        return myLiteral
    }

    /** read an operation */
    private fun readOperation(): Any {
        val myOp = Operation()
        val lengthType = readBits(1)
        myOp.length = 1
        if (lengthType == 0) {
            // read packets until total length is reached
            val totalLength = readBits(15)
            myOp.length += 15 + totalLength
            myOp.p = readPacketsByLength(totalLength)
        }
        else {
            // read n packets
            val numberOfPackets = readBits(11)
            myOp.length += 11
            myOp.p = readPacketsByNumber(numberOfPackets)
            myOp.p.forEach { myOp.length += it.getLength() }
        }
        return myOp
    }

    /** read operand packets by length */
    private fun readPacketsByLength(totalLength: Int): List<Packet> {
        val packetList = mutableListOf<Packet>()
        var length = totalLength
        while (length > 0) {
            val nextPacket = readPacket()
            packetList.add(nextPacket)
            length -= nextPacket.getLength()
        }
        return packetList
    }

    /** read operand packets by number */
    private fun readPacketsByNumber(numberOfPackets: Int): List<Packet> {
        val packetList = mutableListOf<Packet>()
        for (i in 1..numberOfPackets) {
            val nextPacket = readPacket()
            packetList.add(nextPacket)
        }
        return packetList
    }

    /** read padding up to length of multiple of 4 */
    private fun readPadding(curLength: Int): String {
        var padding = ""
        if (curLength % 8 == 0)
            return padding
        for (i in 0 until 8 - curLength % 8) {
            readBits(1)
            padding += 0
        }
        return padding
    }

    /** read n bits from input stream */
    private fun readBits(numberOfBits: Int): Int {
        var result = 0
        for (i in 1 .. numberOfBits) {
            result = 2 * result + if (inputData.get(inpBitSetindex++)) 1 else 0
        }
        return result
    }
}