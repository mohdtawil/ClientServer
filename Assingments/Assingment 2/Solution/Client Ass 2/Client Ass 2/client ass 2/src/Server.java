
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serversocket1 = new ServerSocket(3343);
        Socket client1 = serversocket1.accept();
        Socket client2 = serversocket1.accept();
        Socket client3 = serversocket1.accept();
       boolean x = true;
        System.out.println("client 1 , 2 , 3  is connected");
        
        
        // read form 1
            DataInputStream cli1_msg = new DataInputStream(client1.getInputStream());
            String msg = cli1_msg.readUTF();
            System.out.println("message : " + msg);

            DataInputStream cli1_num = new DataInputStream(client1.getInputStream());
            int number = cli1_num.readInt();
            System.out.println("delever to client : " + number);
            
            
            
        while(x) {
            System.out.println("========================================");
            if(msg.equals("stop") || msg.equals("end")) {
                     x=false;
                     cli1_msg.close();
                     cli1_num.close();
                     client1.close();
                     client2.close();
                     client3.close();
                     number=0;
                    break;
                 }

            if(number == 2) {
                
                // send to 2
                DataOutputStream for_clinet_2 = new DataOutputStream(client2.getOutputStream());
                for_clinet_2.writeUTF(msg);
                DataOutputStream for_clinet_2n = new DataOutputStream(client2.getOutputStream());
                for_clinet_2n.writeInt(number);
                
                
                // read form 2
                DataInputStream cli2_msg = new DataInputStream(client2.getInputStream());
                msg = cli2_msg.readUTF();
                System.out.println("message : " + msg);
                DataInputStream cli2_num = new DataInputStream(client2.getInputStream());
                number = cli2_num.readInt();
                System.out.println("to : " + number);

                 
            }
            if(msg.equals("stop") || msg.equals("end")) {
                     x=false;
                     client1.close();
                     client2.close();
                     client3.close();
                     number=0;
                    break;
                 }
            if(number == 1) {
                    DataOutputStream for_clinet_1 = new DataOutputStream(client1.getOutputStream());
                    for_clinet_1.writeUTF(msg);

                    DataOutputStream for_clinet_1n = new DataOutputStream(client1.getOutputStream());
                    for_clinet_1n.writeInt(number);
                    
                    DataInputStream cli1_msg11 = new DataInputStream(client1.getInputStream());
                     msg = cli1_msg11.readUTF();
                    System.out.println("message : " + msg);

                    DataInputStream cli1_num11 = new DataInputStream(client1.getInputStream());
                     number = cli1_num11.readInt();
                    System.out.println("to : " + number);
                    
                    
                } 
            if(msg.equals("stop") || msg.equals("end")) {
                     x=false;
                     client1.close();
                     client2.close();
                     client3.close();
                     number=0;
                    break;
                 }
                if(number == 3) {
                    DataOutputStream for_clinet_3 = new DataOutputStream(client3.getOutputStream());
                    for_clinet_3.writeUTF(msg);

                    DataOutputStream for_clinet_1n = new DataOutputStream(client3.getOutputStream());
                    for_clinet_1n.writeInt(number);
                    
                    
                    DataInputStream cli2_msg33 = new DataInputStream(client3.getInputStream());
                    msg = cli2_msg33.readUTF();
                    System.out.println("message : " + msg);
                    DataInputStream cli2_num33 = new DataInputStream(client3.getInputStream());
                    number = cli2_num33.readInt();
                    System.out.println("to : " + number);
                    
                    
                }

            
            
            
            
            
            
            
            
            }
            
       
        }
        

}
