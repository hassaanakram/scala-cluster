package networkService
import scala.util.{Try, Using}
import java.net.ServerSocket

// TODO: Right now, the messages are being handled as formatted strings. Need to create a defined schema and add methods to 
// marshal and un-marshal the messages to and from the defined schema.

class NetworkService:
    val nodeAddress: Address = new Address("127.0.0.1", this.getFreePort().get)
    val udpClient: UDPClient = new UDPClient(this.nodeAddress)

    def getFreePort(): Try[Int] = {
        Using(new ServerSocket(0)) (_.getLocalPort())
    }
