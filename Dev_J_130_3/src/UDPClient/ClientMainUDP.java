
package UDPClient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMainUDP {

    public static void main(String[] args) {
        ClientUDP client = new ClientUDP();
        System.out.println("Client started");
        try {
            client.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientMainUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}

