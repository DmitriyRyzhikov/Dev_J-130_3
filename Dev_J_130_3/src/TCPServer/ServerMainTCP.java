
package TCPServer;

public class ServerMainTCP {

    public static void main(String[] args) {
        ServerTCP server = new ServerTCP();
        System.out.println("Server started");
        server.start();
        System.out.println("Server finished");
    }   
}
