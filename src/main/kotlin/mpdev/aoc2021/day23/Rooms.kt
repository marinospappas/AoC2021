package mpdev.aoc2021.day23

class Node (var id: String, type: Char) {
    var connectsTo = mutableListOf<Connection>()
}

class Connection (var nodeId: String, cost: Int)

class Network {
    var grid = mutableMapOf<String,Node>()
}