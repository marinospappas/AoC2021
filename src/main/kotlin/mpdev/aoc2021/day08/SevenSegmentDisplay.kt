package mpdev.aoc2021.day08

/*
Digit   a   b   c   d   e   f   g   abc defg    hex
0       on  on  on      on  on  on  111 0111    0x77
1               on          on	    001 0010    0x12
2       on      on  on  on      on  101 1101    0x5D
3       on      on  on      on  on  101 1001    0x5B
4           on  on  on      on 	    011 1010    0x3A
5       on  on      on      on  on  110 1011    0x6B
6       on  on      on  on  on  on  110 1111    0x6F
7       on      on          on      101 0010    0x52
8       on  on  on  on  on  on  on  111 1111    0x7F
9       on  on  on  on      on  on  111 1011    0x7B
 */

const val SEGMENTS_1 = "CF"
const val SEGMENTS_7 = "ACF"
const val SEGMENTS_4_OFF = "AEG"
const val SEGMENTS_5_COMMON = "ADG"
const val SEGMENTS_6_NON_COMMON = "CDE"

class SevenSegmentDisplay(input: List<String> = listOf()) {

    // the binary (hex) values of the 7 segments for the numbers 0-9
    private val num2seg = listOf(0x77, 0x12, 0x5d, 0x5b, 0x3a, 0x6b, 0x6f, 0x52, 0x7f, 0x7b)

    // the mapping of the incoming segments to "standard" segments
    private var displayMappings = mutableMapOf<String,String>() // original mapping of input values
    private lateinit var segmentsMap: Map<String,String>        // calculated mapping one-to-one

    // initialise the mappings for this input (input is the 10 different combinations of segments)
    init {
        if (input.isNotEmpty())
            segmentsMap = decodeSegments(input)
    }

    /** return the segment mapping for testing */
    fun getSegmentsMap() = segmentsMap

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // functions that handle the decoding of the segments based on the 10 combinations in the input

    /** remove characters from a string */
    private fun String.removeChars(chars: String) = filterNot { it in chars }

    /** find the common characters in a list of strings */
    private fun getCommonSegments(stringsList: List<String>): String {
        var s = ""
        "abcdefg".forEach { c ->
            var isCommon = true
            stringsList.forEach { s -> isCommon = isCommon && c in s }
            if (isCommon)
                s += c
        }
        return s
    }

    /**
     * reduce the mappings by removing segments from both sides that are common across any two mappings
     * try all combinations of each mapping against all mappings
     * It's the same concept as in maths:
     *      5 + x + y = a + b + c and
     *          x + y = a + b
     *      gives us 5 = c
     * In the segment mappings we have
     *      "abg" -> "DCA" and
     *      "bg"  -> "DA"
     *       gives us "a" maps to "C"
     */
    private fun reduceMappings() {
        displayMappings.toMap().forEach { (k, v) ->
            displayMappings.toMap().forEach { (t, u) ->
                if (t != k) {
                    val newKey = t.removeChars(k)
                    val newValue = u.removeChars(v)
                    if (newKey.isNotEmpty() && newValue.isNotEmpty()) {
                        displayMappings[newKey] = newValue
                    }
                }
            }
        }
    }

    // functions that process the segments mappings
    private fun updateInitialMappings_2_3_4(input: List<String>) {
        displayMappings[input.first { it.length == 2 }] = SEGMENTS_1    // 2 segments on
        displayMappings[input.first { it.length == 3 }] = SEGMENTS_7    // 3 segments on
        //    for the 4 segments on map the OFF segments are used instead as they are only 3
        displayMappings["abcdefg".removeChars(input.first { it.length == 4 })] = SEGMENTS_4_OFF
    }
    private fun updateInitialMappings_5_6(input: List<String>) {
        //    5 segments on - 3 common segments
        displayMappings[getCommonSegments(input.filter { it.length == 5 })] = SEGMENTS_5_COMMON
        //    6 segments on - 4 common segments keep the non-common segments as they are only 3
        displayMappings["abcdefg".removeChars(getCommonSegments(input.filter { it.length == 6 }))] = SEGMENTS_6_NON_COMMON
    }
    private fun updateSeventhMapping() {
        val keys = displayMappings.filterKeys { it.length == 1 } .toList().joinToString("")
        val values = displayMappings.filterValues { it.length == 1 } .toList().joinToString("")
        displayMappings["abcdefg".removeChars(keys)] = "ABCDEFG".removeChars(values)
    }

    /** decode the input segments to standard segments and produce the segments map */
    private fun decodeSegments(input: List<String>): Map<String,String> {
        // 1. update the map with the initial mappings
        updateInitialMappings_2_3_4(input)
        updateInitialMappings_5_6(input)
        // 2. create new mappings by combining two mappings at a time and removing segments common in both sides
        // repeat until we have 6 mappings of length 1 (our solution)
        do {
            reduceMappings()
        } while (displayMappings.count { it.key.length == 1 } < 6)
        // 3. add the final 7th mapping
        updateSeventhMapping()
        return displayMappings.filter { it.key.length == 1 }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    // functions that deal with conversions from segments to digits

    /** get the binary flag for a segment */
    fun getSegmentHexValue(c: Char) = power(2, 'G' - c.uppercaseChar())

    /** return the number for a specific segment combination (int) */
    fun convertSegmentsToNumber(seg: Int) = num2seg.indexOf(seg)

    /** return the number for a specific segment combination (string) */
    fun convertSegmentsToNumber(seg: String): Int {
        var numSeg = 0
        seg.forEach { c -> numSeg += getSegmentHexValue(c) }
        return convertSegmentsToNumber(numSeg)
    }

    /** return a decimal digit from 7-segment input after mapping */
    fun convertMappedSegmentsToNumber(sevenSegs: String): Int {
        var mappedSegs = ""
        sevenSegs.forEach { mappedSegs += segmentsMap[it.toString()] }
        return convertSegmentsToNumber(mappedSegs)
    }
}