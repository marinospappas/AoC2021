package mpdev.aoc2021.day18


class SNumber_(s1: Any, s2: Any) {
    val s: List<Any>
    override fun toString() = "[${s[0]},${s[1]}]"
    init {
        if (!(s1 is Int) && !(s1 is SNumber_))
            throw SNumberException("invalid parameter: ${s1.javaClass.simpleName} $s1 - must be either Int or SNumber")
        if (!(s2 is Int) && !(s2 is SNumber_))
            throw SNumberException("invalid parameter: ${s2.javaClass.simpleName} $s2 - must be either Int or SNumber")
        s = listOf(s1, s2)
    }
}

/** S-Maths implementation (lists of pairs of numbers) */
object SMaths_ {

    /** add */
    fun add(s1: Any, s2: Any) = SNumber_(s1, s2)

    /** explode */
    fun explode(s: SNumber_): SNumber_ {
        return myExplode(s, 1)
    }

    private fun myExplode(s: SNumber_, level: Int): SNumber_ {
        if (level < 4) {
            println("    myExplode s is ${s}, level is ${level}")
            val newS = Array(2) { Any() }
            for (i in 0..1) {
                val s_ = s.s[i]
                if (s_ is Int)
                        newS[i] = s_
                else {
                    newS[i] = myExplode(s_ as SNumber_, level + 1)
                    println("    returned from myExplode (level ${level+1}, object is ${newS[i]}")
                }
            }
            return  SNumber_(newS[0], newS[1])
        }
        else {
            println("    explode now - s is ${s}, reached level ${level}")
            return explodeNow(s)
        }
    }

    private fun explodeNow(s: SNumber_): SNumber_ {
        return s
    }

    /** split */
    fun split(s: SNumber_): SNumber_ {
        val newS = Array(2) { Any() }
        for (i in 0..1) {
            val s_ = s.s[i]
            if (s_ is Int) {
                if (s_ > 9)
                    newS[i] = splitInt(s_)
                else
                    newS[i] = s_
            }
            else
                newS[i] = split(s_ as SNumber_)
        }
        return SNumber_(newS[0], newS[1])
    }

    private fun splitInt(i: Int) = SNumber_(i/2, i/2+1)

}