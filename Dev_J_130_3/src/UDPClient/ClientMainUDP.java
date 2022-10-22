
package UDPClient;

public class ClientMainUDP {

    public static void main(String[] args) {
        ClientUDP client = new ClientUDP();
        System.out.println("Client started");
        client.start();
    }   
}

