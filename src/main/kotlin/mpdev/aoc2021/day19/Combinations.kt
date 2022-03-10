package mpdev.aoc2021.day19

fun <T> Iterable<T>.combinations(length: Int): Sequence<List<T>> = sequence {
    val pool = this@combinations as? List<T> ?: toList()
    val n = pool.size
    if(length > n) return@sequence
    val indices = IntArray(length) { it }
    while(true) {
        yield(indices.map { pool[it] })
        var i = length
        do {
            i--
            if(i == -1) return@sequence
        } while(indices[i] == i + n - length)
        indices[i]++
        for(j in i+1 until length) indices[j] = indices[j - 1] + 1
    }
}

fun <T> Iterable<T>.permutations(length: Int? = null): Sequence<List<T>> = sequence {
    val pool = this@permutations as? List<T> ?: toList()
    val n = pool.size
    val r = length ?: n
    if(r > n) return@sequence
    val indices = IntArray(n) { it }
    val cycles = IntArray(r) { n - it }
    yield(List(r) { pool[indices[it]] })
    if(n == 0) return@sequence
    cyc@ while(true) {
        for(i in r-1 downTo 0) {
            cycles[i]--
            if(cycles[i] == 0) {
                val temp = indices[i]
                for(j in i until n-1) indices[j] = indices[j+1]
                indices[n-1] = temp
                cycles[i] = n - i
            } else {
                val j = n - cycles[i]
                indices[i] = indices[j].also { indices[j] = indices[i] }
                yield(List(r) { pool[indices[it]] })
                continue@cyc
            }
        }
        return@sequence
    }
}