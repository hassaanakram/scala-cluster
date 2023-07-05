package networkService

import java.net.{DatagramSocket, DatagramPacket, InetAddress, SocketAddress, InetSocketAddress, MulticastSocket}

class UDPClient(nodeAddress: Address):
    val broadcastAddress: InetAddress = InetAddress.getByName("230.0.0.0")
    val broadcastPort: Int = 4321 // TODO: check if the port is available at runtime
    val BUFFER_SIZE: Int = 1024 // TODO: This is being used as a placeholder. Need to figure out the right buffer size.
    
    def broadcastMessage(message: String): Unit = {
        val udpSocket: DatagramSocket = new DatagramSocket(null)

        val messageBytes: Array[Byte] = message.getBytes()
        val packet: DatagramPacket = new DatagramPacket(messageBytes, messageBytes.length, this.broadcastAddress, this.broadcastPort)
        
        udpSocket.send(packet)
        udpSocket.close()
    }

    def receiveMessage(): String = {
        val udpMulticastSocket: MulticastSocket = new MulticastSocket(this.broadcastPort)
        udpMulticastSocket.joinGroup(this.broadcastAddress)

        val buffer: Array[Byte] = new Array[Byte](this.BUFFER_SIZE)
        val packet: DatagramPacket = new DatagramPacket(buffer, buffer.length)
        udpMulticastSocket.receive(packet)

        println("Received data from: " + packet.getAddress().toString() + ":" + packet.getPort())

        udpMulticastSocket.leaveGroup(this.broadcastAddress)
        udpMulticastSocket.close()
        
        return new String(packet.getData())
    }
