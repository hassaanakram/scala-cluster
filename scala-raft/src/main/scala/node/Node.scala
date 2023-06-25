package node

import networkService.{Address, NetworkService}

class Node:
    val netSvc: NetworkService = new NetworkService()

    var nodeId: String = System.currentTimeMillis().toString()
    var nodeAddress: Address = this.netSvc.nodeAddress
    var nodeRole: NodeRole = NodeRole.FOLLOWER
    var nodeAddressMap: scala.collection.mutable.Map[String, Address] = scala.collection.mutable.Map()
    var currentLeader: String = ""
    var currentTerm: Int = 0
    var nodeVotesCount: Int = 0
    var logs: List[String] = List()

    val CLUSTER_JOIN_MESSAGE: String = f"CLUSTER_JOIN|${this.nodeId}|${this.nodeAddress.host}|${this.nodeAddress.port}"

    def joinCluster(): Unit = {
        this.netSvc.udpClient.broadcastMessage(this.CLUSTER_JOIN_MESSAGE)
    }


enum NodeRole:
    case FOLLOWER, CANDIDATE, LEADER