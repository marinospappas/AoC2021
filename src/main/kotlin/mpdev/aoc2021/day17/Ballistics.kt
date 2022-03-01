package mpdev.aoc2021.day17

import java.lang.StrictMath.abs
import kotlin.math.sign
import kotlin.math.sqrt

class Velocity(var vx: Int, var vy: Int) {
    override fun toString() = "($vx,$vy)"
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + vx
        hash = hash * 31 + vy
        return hash
    }
    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false
        val v2 = other as Velocity
        return vx == v2.vx && vy == v2.vy
    }
}

class Position(var x: Int, var y: Int) {
    override fun toString() = "($x,$y)"
}

class Ballistics(var x1: Int, var x2: Int, var y1: Int, var y2: Int) {

    var curPosition = Position(0,0)
    var curVelocity = Velocity(0,0)
    var maxHeight = 0

    fun isInTarget(position: Position): Boolean {
        return (x1 <= position.x && position.x <= x2) && (y1 <= position.y && position.y <= y2)
    }

    fun overshoot(position: Position):Boolean {
        return (position.x > x2) || (position.y < y1)
    }

    fun velocityStep(velocity: Velocity): Velocity {
        val newVy = velocity.vy - 1
        val neWVx = (abs(velocity.vx)-1) * velocity.vx.sign
        return Velocity(neWVx, newVy)
    }

    fun positionStep(position: Position, velocity: Velocity): Position {
        val newX = position.x + velocity.vx
        val newY = position.y + velocity.vy
        return Position(newX, newY)
    }

    fun step() {
        curPosition = positionStep(curPosition, curVelocity)
        curVelocity = velocityStep(curVelocity)
    }

    fun hitTarget(): Boolean {
        while (true) {
            step()
            //println("p: $curPosition v: $curVelocity")
            if (curPosition.y > maxHeight)
                maxHeight = curPosition.y
            if (isInTarget(curPosition))
                return true
            if (overshoot(curPosition))
                return false
        }
    }

    fun positionForTandV(t: Int, v: Velocity): Position {
        curVelocity = v
        curPosition = Position(0,0)
        for (i in 1..t) {
            step()
            print("$i -> $curPosition ")
        }
        println()
        return curPosition
    }

    fun reset() {
        curPosition = Position(0,0)
        curVelocity = Velocity(0, 0)
        maxHeight = 0
    }

    // calculate x and y positions
    fun calcTForXandVx(x: Int, vx: Int): Int {
        val d = (2 * vx + 1) * (2 * vx + 1) - 8 * x
        val t1 = ( 2.0 * vx + 1.0 - sqrt(d.toDouble()) ) / 2.0
        var t = t1.toInt()
        // verification needed due to rounding
        val x1 = calcXforTandVx(t, vx)
        if (x1 < x)
            ++t
        val x2 = calcXforTandVx(t, vx)
        if (x2 > this.x2)
            return -1       // can't reach the target at t or t+1
        return t
    }

    fun calcYforTandVy(t: Int, vy: Int): Int {
        val y: Int
        if (vy <= 0) {
            y = t * vy - t * (t-1) / 2
        }
        else {
            // same as above but change t to (t-2vy-1) and vy to -vy as y will be 0 again at t = 2vy+1
            y = (t - 2*vy - 1) * (-vy) - (t - 2*vy - 1) * (t - 2*vy) / 2
        }
        return y
    }

    fun calcXforTandVx(t: Int, vx: Int): Int {
        var tx = t
        if (t > vx)
            tx = vx  // vx becomes 0 at t = vx
        return tx * vx - tx * (tx-1) / 2
    }
}