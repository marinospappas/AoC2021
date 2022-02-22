package mpdev.aoc2021.day14

/** the polymer rules */
class Polymer(private var rules: Map<String, String>) {

    var elementCount = mutableMapOf<Char,Long>()

    var pairsCount = mutableMapOf<String,Long>()

    override fun toString(): String {
        var s = ""
        rules.forEach { (k,v) -> s += "\n$k -> $v" }
        return s.substring(1)
    }

    /** String insert into position extension */
    private fun String.insert(s: String, pos: Int): String {
        return substring(0,pos) + s + substring(pos)
    }

    /** apply the polymer rules to a template */
    fun applyRules(template: String): String {
        var length = template.length
        var result = template
        var i = -1
        while (++i < length-1) {
            result = aplyRulesForPosition(result, i)
            if (result.length > length) {
                ++i
                length = result.length
            }
        }
        return result
    }

    /** apply the rules on a specific position */
    private fun aplyRulesForPosition(polymer: String, position: Int): String {
        rules.forEach { (k, v) ->
            if (k == polymer.substring(position, position+2)) {
                return polymer.insert(v, position + 1)
            }
        }
        return polymer
    }

    /** count the list of elements */
    fun countElements(polymer: String) {
        for (element in 'A'..'Z') {
            var count = 0L
            polymer.forEach { c -> if (c == element) ++count }
            if (count > 0)
                elementCount[element] = count
        }
    }

    /** get most - least frequent element count */
    fun countOfMostFreqElement() = elementCount.values.maxOrNull()?:0

    fun countOfLeastFreqElement() = elementCount.values.minOrNull()?:0

    /** initialise pairs counts from string */
    fun initPairs(s: String) {
        pairsCount = mutableMapOf()
        for (i in 0 until s.length-1) {
            val pair = s.substring(i, i + 2)
            var count: Long = pairsCount[pair] ?: 0
            pairsCount[pair] = ++count
        }
    }

    /** apply rules part 2 - just count elements */
    fun applyRulesPart2(template: String, steps: Int) {
        initPairs(template)
        for (i in 1..steps) {
            val newPairsCount = mutableMapOf<String, Long>()
            pairsCount.forEach { (pair, count) ->
                if (rules[pair] != null) {
                    val newElement = rules[pair]?:""
                    val newPair1 = pair[0] + newElement
                    val newPair2 = newElement + pair[1]
                    val existingCountPair1 = newPairsCount[newPair1]?:0L
                    val existingCountPair2 = newPairsCount[newPair2]?:0L
                    newPairsCount[newPair1] = count + existingCountPair1
                    newPairsCount[newPair2] = count + existingCountPair2
                } else {
                    newPairsCount[pair] = count
                }
            }
            pairsCount = newPairsCount
        }
    }

    fun countElementsFromPairs() {
        elementCount = mutableMapOf()
        pairsCount.forEach { (pair,count) ->
            for (i in 0..1) {
                val elmntCnt: Long = elementCount[pair[i]]?:0L
                elementCount[pair[i]] = elmntCnt + count
            }
        }
        elementCount.forEach { (k,v) -> elementCount[k] = (v+1L)/2L }
    }
}