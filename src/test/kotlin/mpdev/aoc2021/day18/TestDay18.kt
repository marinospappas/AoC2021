package mpdev.aoc2021.day18

import mpdev.aoc2021.day01.processInputPart1
import mpdev.aoc2021.day16.processInput
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 18 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay18 {

    @Test
    @Order(1)
    fun `Test Node Exceptions`() {
        var exception = assertFailsWith<SNumberException> { Node(1, "2") }
        println(exception.message)
        exception = assertFailsWith<SNumberException> { Node(1.5, 2) }
        println(exception.message)
        exception = assertFailsWith<SNumberException> { Node('1', 2) }
        println(exception.message)
        var node = Node(null, null)
        println ("node: $node")
        assertEquals("[null,null]", node.toString())
        node = Node(1, 2)
        println ("node: $node")
        assertEquals("[1,2]", node.toString())
        node = Node(1, Node(2,3))
        println ("node: $node")
        assertEquals("[1,[2,3]]", node.toString())
        node = Node(Node(1, 2),3)
        println ("node: $node")
        assertEquals("[[1,2],3]", node.toString())
        node = Node(Node(1, 2), Node(3,4))
        println ("node: $node")
        assertEquals("[[1,2],[3,4]]", node.toString())
    }

    @Test
    @Order(2)
    fun `Test BTree add`() {
        val n1 = Node(dataLeft = 1, dataRight = 2)
        val n2 = Node(dataLeft = 3, dataRight = 4)
        val n3 = Node(dataLeft = 5, dataRight = 6)
        val n4 = Node(dataLeft = 7, dataRight = 8)
        val sum = n1.add(n2).add(n3).add(n4)
        println("sum = $sum")
        assertEquals("[[[[1,2],[3,4]],[5,6]],[7,8]]", sum.toString())
    }

    @Test
    @Order(3)
    fun `Test BTree Add`() {
        val t1 = Node(dataLeft = 1, dataRight = 2)
        println("t1 = $t1")
        val t2 = Node(dataLeft = 3, dataRight = 4)
        println("t2 = $t2")
        var tsum = t1.add(t2)
        println("sum $tsum")
        tsum = tsum.add(Node(dataLeft = 5, dataRight = 6))
        println("sum $tsum")
        println("right ${tsum?.right}")
        println("left ${tsum?.left}")
        println("left-right ${tsum?.left?.right}")
        println("left-right-parent ${tsum?.left?.right?.parent}")
        println("left-right-parent-parent ${tsum?.left?.right?.parent?.parent}")
    }

    @Test
    @Order(4)
    fun `Test BTree explode`() {
        // [[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]
        val s = Input().scan("[[[[[4,[5,6]],4],4],[7,[[8,4],9]]],[1,1]]")
        println("number = $s")
        val exp = s.explode()
        println("exploded = $exp")
        assertEquals("[[[[0,7],4],[15,[0,13]]],[1,1]]", exp.toString())
    }

    @Test
    @Order(5)
    fun `Test BTree split`() {
        // [[[[0,7],4],[15,[0,13]]],[1,1]]
        var n1 = Node(dataLeft = 0, dataRight = 7)
        var n2 = Node(dataLeft = null, dataRight = 4)
        var s1 = n2.add(n1)
        var n3 = Node(dataLeft = 15, dataRight = null)
        var n4 = Node(dataLeft = 0, dataRight = 13)
        var s2 = n3.add(n4)
        var s = s1.add(s2).add(Node(dataLeft = 1, dataRight = 1))
        println("number = $s")
        var spl = s.split()
        println("split = $spl")
        assertEquals("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]", spl.toString())
        // [[[[0,7],4],[12,[0,10]]],[1,1]]
        n1 = Node(dataLeft = 0, dataRight = 7)
        n2 = Node(dataLeft = null, dataRight = 4)
        s1 = n2.add(n1)
        n3 = Node(dataLeft = 12, dataRight = null)
        n4 = Node(dataLeft = 0, dataRight = 10)
        s2 = n3.add(n4)
        s = s1.add(s2).add(Node(dataLeft = 1, dataRight = 1))
        println("number = $s")
        spl = s.split()
        println("split = $spl")
        assertEquals("[[[[0,7],4],[[6,6],[0,[5,5]]]],[1,1]]", spl.toString())
    }

    @Test
    @Order(6)
    fun `Test BTree explode and split`() {
        /*
        [[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]
        after explode:  [[[[0,7],4],[15,[0,13]]],[1,1]]
        after split:    [[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]
        after explode:  [[[[0,7],4],[[7,8],[6,0]]],[8,1]]
         */
        val n = Node(Input().scan("[[[[4,3],4],4],[7,[[8,4],9]]]"))
        val s = n.add(Node(1,1))
        println("root info: ${s.info()}")
        println("left info: ${s.left?.info()}")
        println("right info: ${s.right?.info()}")
        var res = s.explode()
        println("res1 (after explode): $res")
        res = s.split()
        println("res2 (after split): $res")
        res = s.explode()
        println("result (after explode): $res, depth = ${res.depth()}")
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", res.toString())
    }

    @Test
    @Order(7)
    fun `Test Input`() {
        var node = Input().scan("[1,2]")
        println("node: $node")
        assertEquals("[1,2]", node.toString())
        node = Input().scan("[[1,2],3]")
        println("node: $node")
        assertEquals("[[1,2],3]", node.toString())
        node = Input().scan("[1,[2,3]]")
        println("node: $node")
        assertEquals("[1,[2,3]]", node.toString())
        node = Input().scan("[[1,2],[3,4]]")
        println("node: $node")
        assertEquals("[[1,2],[3,4]]", node.toString())
        node = Input().scan("[[[[5,0],[7,4]],[5,5]],[6,6]]")
        println("node: $node")
        assertEquals("[[[[5,0],[7,4]],[5,5]],[6,6]]", node.toString())
        node = Input().scan("[[[[2,[9,0]],[[4,8],[3,4]]],[[[6,5],0],[[3,3],[4,3]]]],[[[[4,4],[9,7]],[[4,8],7]],[[5,[6,6]],0]]]")
        println("node: $node")
        assertEquals("[[[[2,[9,0]],[[4,8],[3,4]]],[[[6,5],0],[[3,3],[4,3]]]],[[[[4,4],[9,7]],[[4,8],7]],[[5,[6,6]],0]]]", node.toString())
    }

    @Test
    @Order(8)
    fun `Test Magnitude`() {
        var node = Input().scan("[[1,2],[[3,4],5]]")
        var magn = node.magnitude()
        println("node: $node magnitude: $magn")
        assertEquals(143, magn)
        node = Input().scan("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
        magn = node.magnitude()
        println("node: $node magnitude: $magn")
        assertEquals(1384, magn)
        node = Input().scan("[[[[1,1],[2,2]],[3,3]],[4,4]]")
        magn = node.magnitude()
        println("node: $node magnitude: $magn")
        assertEquals(445, magn)
        node = Input().scan("[[[[3,0],[5,3]],[4,4]],[5,5]]")
        magn = node.magnitude()
        println("node: $node magnitude: $magn")
        assertEquals(791, magn)
        node = Input().scan("[[[[5,0],[7,4]],[5,5]],[6,6]]")
        magn = node.magnitude()
        println("node: $node magnitude: $magn")
        assertEquals(1137, magn)
        node = Input().scan("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")
        magn = node.magnitude()
        println("node: $node magnitude: $magn")
        assertEquals(3488, magn)
    }

    @Test
    @Order(9)
    fun `Test Tree Depth`() {
        var node = Input().scan("[1,2]")
        var depth = node.depth()
        println("node: $node depth: $depth")
        assertEquals(0, depth)
        node = Input().scan("[[1,2],3]")
        depth = node.depth()
        println("node: $node depth: $depth")
        assertEquals(1, depth)
        node = Input().scan("[1,[2,3]]")
        depth = node.depth()
        println("node: $node depth: $depth")
        assertEquals(1, depth)
        node = Input().scan("[[1,2],[3,4]]")
        depth = node.depth()
        println("node: $node depth: $depth")
        assertEquals(1, depth)
        node = Input().scan("[[[[5,0],[7,4]],[5,5]],[6,6]]")
        depth = node.depth()
        println("node: $node depth: $depth")
        assertEquals(3, depth)
        node = Input().scan("[[[[2,[9,0]],[[4,8],[3,4]]],[[[6,5],0],[[3,3],[4,3]]]],[[[[4,4],[9,7]],[[4,8],7]],[[5,[6,6]],0]]]")
        depth = node.depth()
        println("node: $node depth: $depth")
        assertEquals(4, depth)
    }

    @Test
    @Order(99)
    @Disabled
    fun `Test Repeated Reduce`() {
        val node1 = Input().scan("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]")
        val node2 = Input().scan("[2,9]")
        var node = Node(null,null)
        node = node.add(node1)
        node = node.add(node2)
        //var node = Node(node1, node2)
        //var node = Input().scan("[[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]],[0,0]]")
        //var node = Input().scan("[[[3,7],[4,3]],[[6,3],[8,8]]]]")
                //node = Node(14, node)
                //node = Node(Node(0,0), node)
                //node = node.split()
        println("0 node: $node depth: ${node.depth()} magnitude: ${node.magnitude()}")
        println("0 node full info: ${node.info()}")
        println("0 left full info: ${node.left?.info()}")
        println("0 right full info: ${node.right?.info()}")

        for (i in 1..8) {
            node = node.explode()
            println("$i node: $node depth: ${node.depth()} magnitude: ${node.magnitude()}")
            node = node.split().split()
            println("$i node: $node depth: ${node.depth()} magnitude: ${node.magnitude()}")
        }
    }
}



