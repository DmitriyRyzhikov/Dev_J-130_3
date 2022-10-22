
package TCPClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTCP {
    public static final int PORT = 15260;
    public static final String HOST = "127.0.0.1";
    public void start(){
        try(Socket socket = new Socket(HOST, PORT)){
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            String msg = "Hello world";
            os.write(msg.getBytes());
            socket.shutdownOutput();
            System.out.println("Сообщение отправили");
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[256];
            int var = 0;
            while((var=is.read(buf))>-1){
                sb.append(new String(buf, 0, var));
            }
            System.out.println(sb);
            os.close();
            is.close();
            socket.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
