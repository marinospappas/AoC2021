package mpdev.aoc2021.day21

import java.lang.StrictMath.abs
import java.lang.StrictMath.min

fun play1(posn: Array<Int>): Int {
    var roll = 1
    var player = 1
    val total = arrayListOf(0, 0)
    while (total[player] < 1000) {
        player = abs(player - 1)
        posn[player] = (posn[player] + 3*(roll + 1)) % 10
        total[player] += posn[player] + 1
        roll += 3
    }
    println("losing score: ${min(total[0], total[1])}, rolled ${roll-1} times")
    return total.minOrNull()!! * (roll-1)
}

//                     0 1 2 3 4 5 6 7 8 9
val freq = arrayListOf(0,0,0,1,3,6,7,6,3,1)

/**
 * inspiring solution by https://www.reddit.com/user/ai_prof/
 * original code in Python here:
 *     https://topaz.github.io/paste/#XQAAAQBoAwAAAAAAAAA4G8rIJREvntAdJU9+tkueLxoyyGrifhlrTvS5cS5rOG27ntR1n+Ei4eLswyeiivg+ZAAEOpim9SW4uCHWnDJ0RgPSt+VoeYPmngg6V7Gd6KSpSI1v9UIjedAz05MOaf5MlT3AO15VJBfr8xXL1bxjQRs/ckbjDkhztrSD7tz+rWp0oV6XS74m+lVgWNaW66z4CEHp1fHkN6aM19mozxhx1SBiv8LQNhrDu7qL5UzS5OUZb4mVPb/PWS2B65npv4lvDS3cmBU5N9fG/1w9tD4nV5DlRvM9S98h6f3tbQ7eMvfVZjusAvJgISv61U4TLMMXj+CYbQTyFAj0QT84HMlxH+IbVdQDvoNkmGfc3S5BfL4ASWYWMkScpnftM9lxiQaj1YnT+Pc6p9FLdChtcvLm/yXqZZaJnn2oYcsOyq/4jPGydbhR2OOr5Wi/8oAyiFhdoqcqrvBrzoAqOaOm6neyab0Ku4eXaEVecT7IZggozO7fi3kK5RyQPvoGoV96+T01ZHcqqh2g+Jx3EWPxE7joPR4CruHtZLcTXFNhpu6FCdf8GwFa++Yi5jzaAUtgQ0ErAYU9PTOpbSqt2qwc/src2NUplqlW7HP9yFSDE8KVvGzzNGWH66GjPBfMlh13bLu/a2kykpVy4fR0EKeMTAXrwGtEzHiIDYgS2kBtNVW83Vmvn/+BHQgA
 * I merely translated to Kotlin...
 */
fun play2(posn1: Int, total1: Int, posn2: Int, total2: Int): Array<Long> {
    if (total2 <= 0)
        return arrayOf(0L, 1L)
    val wins = arrayOf(0L, 0L)
    for (roll in 3..9) {
        val count = play2(posn2, total2, (posn1+roll)%10, total1-1-(posn1+roll)%10)
        wins[0] += freq[roll] * count[1]
        wins[1] += freq[roll] * count[0]
    }
    return wins
}