
package TCPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTCP {
    
    public static final int PORT = 15260;
    public static final String HOST = "127.0.0.1";
    
    public void start(){
      while(true) { 
        try(Socket socket = new Socket(HOST, PORT); 
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream())
           {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "cp1251"));
            System.out.println("Введите сообщение: \n");
            String msgOut = reader.readLine();            
            os.write(msgOut.getBytes());
            socket.shutdownOutput();
            if(msgOut.equalsIgnoreCase("end")){
               socket.close();
               break;
               }
            System.out.println("Сообщение отправлено на сервер. Ожидание подтверждения получения...");
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[256];
            int var;
            while((var=is.read(buf))>-1)
                 {
                  sb.append(new String(buf, 0, var));
                 }
            String msgIn = sb.toString();
            System.out.println("Подтверждение доставки на сервер получено. Время получения сообщения сервером: " + msgIn);    
            socket.close();
           }
        catch(IOException ioe){}
      } 
    }
}
