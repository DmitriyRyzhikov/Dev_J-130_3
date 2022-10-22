package UDPServer;

public class ServerMainUDP {

    public static void main(String[] args) {
        ServerUDP server = new ServerUDP();
        System.out.println("Server started");
        server.start();
    }
    
}