package mpdev.aoc2021.day20

import java.lang.StrictMath.abs

val byte0 = 0.toByte()
val byte1 = 1.toByte()

val byteToPixel = mapOf(byte0 to ".", byte1 to "#")
val pixelToByte = mapOf('.' to byte0, '#' to byte1)

class Image(imgStr: List<String>) {

    var img: List<List<Byte>>
    lateinit var iea: List<Byte>

    init {
        val byteArray = mutableListOf<MutableList<Byte>>()
        imgStr.forEach { row ->
            val byteRow = mutableListOf<Byte>()
            row.forEach { pixel -> byteRow.add(pixelToByte[pixel]!!) }
            byteArray.add(byteRow)
        }
        img = byteArray
    }

    fun ieaFromString(ieaStr: String): List<Byte> {
        val myByteList = mutableListOf<Byte>()
        ieaStr.forEach { char -> myByteList.add( pixelToByte[char]!! ) }
        return myByteList
    }

    fun ieaToString(): String {
        var s = ""
        iea.forEach { s += byteToPixel[it] }
        return s
    }

    override fun toString(): String {
        var s = ""
        img.forEach { byteList ->
            byteList.forEach { byte -> s += byteToPixel[byte] }
            s += "\n"
        }
        return s.substring(0,s.length-1)
    }

    fun extend(oddEven: Int): Image {
        val newImg = mutableListOf<MutableList<Byte>>()
        // if iea[0] is 0 then extend pattern is always 0
        val extPattern = extendPtrn(oddEven)
        val byte0Array = Array(img[0].size) { extPattern }
        // extend top
        for (i in 1..2) newImg.add(byte0Array.clone().toMutableList())
        // copy all rows
        img.forEach { row ->
            val newRow = mutableListOf<Byte>()
            row.forEach { byte -> newRow.add(byte) }
            newImg.add(newRow)
        }
        // extend bottom
        for (i in 1..2) newImg.add(byte0Array.clone().toMutableList())
        // extend left and right
        newImg.forEach { row ->
            for (i in 1..2) row.add(0,extPattern)
            for (i in 1..2) row.add(extPattern)
        }
        img = newImg
        return this
    }

    fun enhance(oddEven: Int): Image {
        val newImg = mutableListOf<MutableList<Byte>>()
        val enhPattern = enhancePtrn(oddEven)
        val byte0Array = Array(img[0].size) { enhPattern }
        for (i in img.indices) newImg.add(byte0Array.clone().toMutableList())
        for (i in 1 .. img.size-2)
            for (j in 1 .. img.size-2) {
                val pattern = mutableListOf<Byte>()
                pattern.addAll(img[i-1].subList(j-1,j+2))
                pattern.addAll(img[ i ].subList(j-1,j+2))
                pattern.addAll(img[i+1].subList(j-1,j+2))
                var ptrn2dec = 0
                pattern.forEach { byte -> ptrn2dec = ptrn2dec * 2 + byte }
                newImg[i].set(j, iea[ptrn2dec])
            }
        img = newImg
        return this
    }

    fun extendPtrn(oddEven: Int): Byte {
        if (iea[0] == byte0)
            return byte0
        return if (oddEven == 1)   // odd iterations
            byte0
        else                       // even iterations
            byte1
    }

    fun enhancePtrn(oddEven: Int): Byte {
        if (iea[0] == byte0)
            return byte0
        return if (oddEven == 1)   // odd iterations
            byte1
        else                       // even iterations
            byte0
    }

    fun countPixelsOn(): Int {
        var myCount = 0
        for (i in 0 .. img.size-1)
            for (j in 0 .. img.size-1)
                if (img[i][j] == byte1) ++myCount
        return myCount
    }
}