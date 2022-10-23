package UDPServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMainUDP {

    public static void main(String[] args) {
        ServerUDP server = new ServerUDP();
        System.out.println("Server started");
        try {
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerMainUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}