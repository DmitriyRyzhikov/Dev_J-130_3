
package UDPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientUDP {
    
    private static final int PORT = 15250;
    private static final int DATA_LENGTH = 256;
    private DatagramPacket packetToSend;
    private DatagramPacket packetToReceive;
      
    public void start() throws IOException{
        try (DatagramSocket socket = new DatagramSocket()) {
            packetToSend = new DatagramPacket(new byte[DATA_LENGTH], DATA_LENGTH, InetAddress.getByName("127.0.0.1"), PORT);
            packetToReceive = new DatagramPacket(new byte[DATA_LENGTH], DATA_LENGTH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "cp1251"));
            while(true){
                System.out.println("Введите сообщение: \n");
                String msgOut = reader.readLine();
                byte[]temp = msgOut.getBytes();
                for(int i=0 ; i<temp.length ; i+=DATA_LENGTH-1){
                    int length = DATA_LENGTH-1;
                    int len = (i + length)<temp.length ? length : temp.length-i;
                    packetToSend.setData(temp, i, len); 
                    socket.send(packetToSend); }
                if(msgOut.equalsIgnoreCase("end")) {
                    System.out.println("Клиент закончил работу");
                    break;}
                System.out.println("Сообщение отправлено на сервер. Ожидание подтверждения получения...");
                socket.receive(packetToReceive);
                String msgIn = new String(packetToReceive.getData(), 0, packetToReceive.getLength());
                System.out.println("Подтверждение доставки на сервер получено. Время получения сообщения сервером: " + msgIn);                    
            }
        }
    }
}