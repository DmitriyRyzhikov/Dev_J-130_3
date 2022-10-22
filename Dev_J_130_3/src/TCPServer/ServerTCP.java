
package TCPServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class ServerTCP {
    public static final int PORT = 15260;
    public void start(){
        try(ServerSocket server = new ServerSocket(PORT)){
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[256];
            int var = 0;
            while((var=is.read(buf))>-1){
                System.out.println("Читаем пакет");
                sb.append(new String(buf, 0, var));
                System.out.println("Закончили читать пакет");
            }
            String msg = "Принято сообщение: " + sb;
            System.out.println(msg);
            os.write(msg.getBytes());
            is.close();
            os.flush();
            os.close();
            socket.close();
        }catch(IOException ioe){}
    }
}