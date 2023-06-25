package networkService

import java.net.{DatagramSocket, DatagramPacket, InetAddress, SocketAddress, InetSocketAddress}

class UDPClient(nodeAddress: Address):
    val broadcastAddress: InetAddress = InetAddress.getByName("127.0.0.1")
    val broadcastPort: Int = 8888 // TODO: check if the port is available at runtime
    val udpSocket: DatagramSocket = new DatagramSocket(nodeAddress.port, InetAddress.getByName(nodeAddress.host))
    val BUFFER_SIZE: Int = 1024 // TODO: This is being used as a placeholder. Need to figure out the right buffer size.
    
    def broadcastMessage(message: String): Unit = {
        val messageBytes: Array[Byte] = message.getBytes()
        val packet: DatagramPacket = new DatagramPacket(messageBytes, messageBytes.length, this.broadcastAddress, this.broadcastPort)
        this.udpSocket.setBroadcast(true)
        this.udpSocket.send(packet)
        this.udpSocket.close() // TODO: Check if this is needed or if the DatagramSocket is closed automatically.
    }

    def receiveMessage(): String = {
        val buffer: Array[Byte] = new Array[Byte](this.BUFFER_SIZE)
        val packet: DatagramPacket = new DatagramPacket(buffer, buffer.length)
        
    }
