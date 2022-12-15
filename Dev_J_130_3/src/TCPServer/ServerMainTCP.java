
package TCPServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMainTCP {

    public static void main(String[] args) {
        ServerTCP server = new ServerTCP();
        System.out.println("Сервер запущен.");
        try {
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerMainTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
