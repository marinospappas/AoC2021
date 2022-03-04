package mpdev.aoc2021.day18

import kotlin.math.max

class SNumberException(override var message: String): Exception(message)

class Node(var left: Node? = null, var right: Node? = null, var parent: Node? = null, var dataLeft: Int? = null, var dataRight: Int? = null) {

    init {
        // ensure both children point to this as parent
        if (left is Node)
            left?.parent = this
        if (right is Node)
            right?.parent = this
    }

    /** constructor from 2 Objects */
    constructor(l: Any?, r: Any?): this() {
        if (l == null)
            left = null
        else
            if (l is Int)
                dataLeft = l
            else
                if (l is Node) {
                    left = l
                    l.parent = this
                } else
                    throw SNumberException("invalid parameter: ${l.javaClass.simpleName} $l - must be either Int or Node")

        if (r == null)
            right = null
        else
            if (r is Int)
                dataRight = r
            else
                if (r is Node) {
                    right = r
                    r.parent = this
                } else
                    throw SNumberException("invalid parameter: ${r.javaClass.simpleName} $r - must be either Int or Node")
    }

    /** node info for debugging */
    fun info(): String = "Node Info: left: $left, right; $right, parent: $parent, dataLeft: $dataLeft, dataRight: $dataRight"

    override fun toString(): String {
        var s = "["
        s += if (left == null)
                "${dataLeft},"
            else
                "${left!!},"
        s += if (right == null)
            dataRight
        else
            right!!.toString()
        return "$s]"
    }

    /** add */
    fun add(n: Node): Node {
        // add (a,b) to (null,n) -> ((a,b),n)
        if (left == null && dataLeft == null) {
            left = n
            n.parent = this
            return this
        } else
            // add (a,b) to (n,null) -> (n,(a,b))
            if (right == null && dataRight == null) {
                right = n
                n.parent = this
                return this
            } else {
                // add (n1,n2) to (m1,m2) -> ((m1,m2),(n1,n2))
                val new = Node(left = this, right = n)
                new.left?.parent = new
                new.right?.parent = new
                return new
            }
    }

    /** split */
    fun split(): Node {
        // if it has left-hand child then split the child
        if (left != null)
            left!!.split()
        // else if the left-hand data is >9 split it
        else if (dataLeft!! > 9) {
            val s = Node(dataLeft = dataLeft!!/2, dataRight = dataLeft!!/2+dataLeft!!%2)
            left = s
            dataLeft = null
            s.parent = this
        }
        // if it has right-hand child then split the child
        if (right != null)
            right!!.split()
        // else if the right-hand data is >9 split it
        else if (dataRight!! > 9) {
            val s = Node(dataLeft = dataRight!!/2, dataRight = dataRight!!/2+dataRight!!%2)
            right = s
            dataRight = null
            s.parent = this
        }
        return this
    }

    /** reduce */
    fun reduce(): Node {
        var reduced: Node = this
        while (reduced.mustExplode() || reduced.mustSplit()) {
            while (reduced.mustExplode())
                reduced = reduced.explode()
            if (reduced.mustSplit())
                reduced = reduced.split()
        }
        return reduced
    }

    fun mustExplode(): Boolean = false

    fun mustSplit(): Boolean {
        return false
    }

    /** explode */
    fun explode(): Node {
        myExplode(0, root = this)
        return this
    }

    private fun myExplode(level: Int, leftRight: Int = 0, root: Node) {
        println("    myExplode node is ${this}, level is ${level}")
        // explode left-hand side
        if (left != null) {
            left?.myExplode(level+1, -1, root)!!
        }
        // explode right-hand side
        if (right != null) {
            right?.myExplode(level + 1, 1, root)!!
        }
        if (left == null && right == null && level >= 4) {
            println("@@@@    calling explode now - node is ${this}, reached level ${level}")
            println("  @@    tree is ${root}")
            explodeNow(leftRight)
            println("@@@@    exploded item - tree is now ${root}\n")
        }
    }

    private fun explodeNow(leftRight: Int) {
        // remove this node and update left and right
        if (leftRight < 0) {    // the node that is exploded is a left-hand side node
            println("    ---- exploded $this updating left with ${dataLeft}, right with ${dataRight}")
            parent?.left = null
            parent?.dataLeft = 0
            println("    ---- updating right $dataRight on $parent ")
            updRightSide(parent, dataRight!!)   // we are on the left, so update the right-hand side of the immediate parent
            // go up the tree until we find the node that has child on the left (other than this one!)
            var node = parent
            while (node?.parent?.left == node) {
                if (node?.parent == null)
                    break
                node = node.parent
            }
            println("    ++++ updating left $dataLeft on ${node?.parent} ")
            if (node?.parent != null)
                updLeftSide(node.parent, dataLeft!!)
            else
                println("    ++++ no update left")
        }
        else {               // the node that is exploded is a right-hand side node
            println("    ---- exploded $this updating left with ${dataLeft}, right with ${dataRight}")
            parent?.right = null
            parent?.dataRight = 0
            println("    ---- updating left $dataLeft on $parent ")
            updLeftSide(parent, dataLeft!!)     // we are on the right, so update the left-hand side of the immediate parent
            // go up the tree until we find the node that has child on the right
            var node = parent
            println("^^^^^^ going up the tree to find the first node with data on the right")
            println("^^^^^^ starting with $node")
            while (node?.parent?.right == node) {
                if (node?.parent == null) {
                    println("    ^^^^^^ reached root - $node")
                    break
                }
                node = node.parent
                println("    ^^^^^^ trying $node")
            }
            println("^^^^^^ loop completed with node ${node} ")
            println("    ==== updating right $dataRight on ${node?.parent} ")
            println("^^^^^^ loop completed with node ${node} parent is ${node?.parent}")
            println("^^^^^^ full node info ${node?.info()}")
            if (node?.parent != null)
                updRightSide(node.parent, dataRight!!)
            else {
                println("    **** ==== no update right")
            }
        }
    }

    private fun updLeftSide(node: Node?, i: Int) {
        var p: Node? = node
        println("    <<<< updleft - node $p (${p?.info()})")
        // 1. if it's data on the left, update it - we are done
        if (p?.dataLeft != null) {
            p.dataLeft = p.dataLeft!! + i
            println("    left **** updleft - node had data on left - updated node $p (${p.info()})")
            return
        }
        // 2. if it's a child node on the left then go down the right-hand side
        p = p?.left
        while (p?.right != null) {
            p = p.right
        }
        println("    <<<< **** updleft - gone down to the right - we are on node $p (${p?.info()})")
        // 3. check if there is any data there
        if (p?.dataRight != null)
            // we have found it - update it
            p.dataRight = p.dataRight!! + i
        println("    <<<< !!!! updleft - node had data on right - updated node $p (${p?.info()})")
    }

    private fun updRightSide(node: Node?, i: Int) {
        var p: Node? = node
        println("    >>>> updright - node $p (${p?.info()})")
        // 1. if it's data on the right, update it - we are done
        if (p?.dataRight != null) {
            p.dataRight = p.dataRight!! + i
            println("    >>>> **** updright - node had data on right - updated node $p (${p.info()})")
            return
        }
        // 2. if it's a child node on the right then go down the left-hand side
        p = p?.right
        while (p?.left != null) {
            p = p.left
        }
        println("    >>>> **** updright - gone down to the left - we are on node $p (${p?.info()})")
        // 3. check if there is any data there
        if (p?.dataLeft != null)
        // we have found it - update it
            p.dataLeft = p.dataLeft!! + i
        println("    >>>> !!!! updright - node had data on left - updated node $p (${p?.info()})")
    }

    /** calculate the magnitude  = 3 x left + 2 x right */
    fun magnitude(): Int {
        val mLeft: Int
        val mRight: Int
        if (left == null)
            mLeft = dataLeft!!
        else
            mLeft = left!!.magnitude()
        if (right == null)
            mRight = dataRight!!
        else
            mRight = right!!.magnitude()
        return 3 * mLeft + 2 * mRight
    }

    /** calculate depth of the tree */
    fun depth(): Int {
        var lDepth = 0
        var rDepth = 0
        if (left != null)
            lDepth = left!!.depth()+1
        if (right != null)
            rDepth = right!!.depth()+1
        return max(lDepth,rDepth)
    }
}


