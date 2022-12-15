
package TCPServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerTCP {
    
    public static final int PORT = 15260;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String timeReceiving;
    
    public void start() throws IOException{
        
        try (ServerSocket server = new ServerSocket(PORT)) {            
            while(true) {
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                byte[] buf = new byte[256];
                int var;
                StringBuilder sb = new StringBuilder();
                
                while((var=is.read(buf))>-1)
                    {
                    sb.append(new String(buf, 0, var));
                    }
                timeReceiving = formatter.format(LocalTime.now());
                String msgIn = sb.toString();
                if(msgIn.equalsIgnoreCase("end")) {
                    System.out.println("Получено сообщение о завершении работы.\nСервер закончил работу");
                    socket.close();
                    server.close();
                    break;
                }
                System.out.println("В " + timeReceiving + " получено сообщение: \"" + msgIn + "\". Адрес клиента: " + socket.getInetAddress().getHostAddress());
                String msgOut = timeReceiving;
                os.write(msgOut.getBytes());
                os.flush();               
                socket.shutdownOutput();
                System.out.println("Время получения сообщения сервером, отправлено отправителю.");
            } 
        }
    }
}