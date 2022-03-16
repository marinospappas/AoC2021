package mpdev.aoc2021.day25

class SeaBed(mapInput: List<String>) {

    var map: List<List<Char>>

    init {
        val thisMap: MutableList<List<Char>> = mutableListOf()
        mapInput.forEach { thisMap.add(it.toList()) }
        map = thisMap
    }

    fun nextStep(): List<List<Char>> {
        val myMap: MutableList<MutableList<Char>> = map.copyOf()
        for (i in myMap.indices) {
            var j = -1
            var endOfRow = myMap[0].size
            if (myMap[i][0] != '.')
                // if the first item in the row is not free, don't look at the last item
                --endOfRow
            while (++j < endOfRow) {
                val next = if (j < myMap[0].size-1) j+1 else 0
                if (myMap[i][j] == '>' && myMap[i][next] == '.') {
                    myMap[i][next] = '>'
                    myMap[i][j] = '.'
                    ++j
                }
            }
        }
        for (j in myMap[0].indices) {
            var i = -1
            var endOfCol = myMap.size
            if (myMap[0][j] != '.')
            // if the first item in the col is not free, don't look at the last item
                --endOfCol
            while (++i < endOfCol) {
                val next = if (i < myMap.size-1) i+1 else 0
                if (myMap[i][j] == 'v' && myMap[next][j] == '.') {
                    myMap[next][j] = 'v'
                    myMap[i][j] = '.'
                    ++i
                }
            }
        }
        return myMap
    }

    override fun toString(): String {
        var s = ""
        map.forEach { row ->
            row.forEach { char -> s += char }
            s += "\n"
        }
        return s.substring(0,s.length-1)
    }
}

fun List<List<Char>>.copyOf(): MutableList<MutableList<Char>> {
    val newMap: MutableList<MutableList<Char>> = mutableListOf()
    this.forEach { row ->
        val newRow = mutableListOf<Char>()
        row.forEach { char -> newRow.add(char) }
        newMap.add(newRow)
    }
    return newMap
}

fun List<List<Char>>.isEqual(map2: List<List<Char>>): Boolean {
    for (i in this.indices)
        for (j in this[0].indices)
            if (this[i][j] != map2[i][j])
                return false
    return true
}