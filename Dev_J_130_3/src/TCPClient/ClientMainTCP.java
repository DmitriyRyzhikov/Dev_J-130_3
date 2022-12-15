
package TCPClient;

public class ClientMainTCP {

    public static void main(String[] args) {
        ClientTCP client = new ClientTCP();
        System.out.println("Клиент запущен.");
        client.start();
        System.out.println("Клиент завершил работу.");
    }
    
}
