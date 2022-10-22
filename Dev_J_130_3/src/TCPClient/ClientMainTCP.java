
package TCPClient;

public class ClientMainTCP {

    public static void main(String[] args) {
        ClientTCP client = new ClientTCP();
        System.out.println("Client started");
        client.start();
        System.out.println("Client finished");
    }
    
}
