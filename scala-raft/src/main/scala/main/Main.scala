package main
import node.Node

// TODO for the whole program: Add exception handling

@main def run(): Unit = {
    var node: Node = new Node()
    node.joinCluster()
    println(node.nodeId)
    println(node.nodeAddress.host)
    println(node.nodeAddress.port)
}