package UDPServer;

import java.io.IOException;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerUDP {
    static final int PORT = 15250;
    static final int DATA_LENGTH = 256;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DatagramPacket packetToReceive;
    private DatagramPacket packetToSend;
    private String timeReceiving;
    
    public void start() throws IOException{
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            packetToReceive = new DatagramPacket(new byte[DATA_LENGTH], DATA_LENGTH);
            while(true){
                socket.receive(packetToReceive);
                String msgIn = new String(packetToReceive.getData(), 0, packetToReceive.getLength());
                if(msgIn.equalsIgnoreCase("end")) {
                    System.out.println("Получено сообщение о завершении работы.\nСервер закончил работу");
                    break;}
                timeReceiving = formatter.format(LocalTime.now());
                System.out.println("В " + timeReceiving + " получено сообщение: \"" + msgIn + "\". Адрес клиента: " + packetToReceive.getAddress().getHostAddress());
                packetToSend = new DatagramPacket(new byte[DATA_LENGTH], DATA_LENGTH, packetToReceive.getAddress(), packetToReceive.getPort());
                String msgOut = timeReceiving;
                byte[]temp = msgOut.getBytes();
                for(int i=0 ; i<temp.length ; i+=DATA_LENGTH-1){
                    int length = DATA_LENGTH-1;
                    int len = (i + length)<temp.length ? length : temp.length-i;
                    packetToSend.setData(temp, i, len);
                    socket.send(packetToSend);}
                System.out.println("Время получения сообщения сервером, отправлено отправителю.");               
            }
        }
    }
}