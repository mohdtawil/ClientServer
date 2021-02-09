
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
        ServerSocket serversocket1 = new ServerSocket(4040);
        Socket client1 = serversocket1.accept();
        Socket client2 = serversocket1.accept();
        Socket client3 = serversocket1.accept();
        boolean x = true;
        System.out.println("client 1 , 2 , 3  is connected");
        
        // make a random number 
        int min = 1;
        int max = 100;
        System.out.print("Random value in int from "+min+" to "+max+ ":");
        int random = (int)(Math.random() * (max - min + 1) + min);
        Integer server_rand = new Integer(random);
        System.out.println(random);
        
        
        // read form 1
            
            
            
            
            
        while(x) {
          
            DataInputStream cli1_num = new DataInputStream(client1.getInputStream());
            int number1 = cli1_num.readInt();
            Integer client1_rand = new Integer(number1);
            
            if(server_rand.equals(client1_rand)){
                DataOutputStream for_clinet_1 = new DataOutputStream(client1.getOutputStream());
                for_clinet_1.writeUTF("you quest yor number is correct");
                x = false;
                 cli1_num.close();
                for_clinet_1.close();
                break;
            } else if(client1_rand < server_rand) {
                DataOutputStream for_clinet_1 = new DataOutputStream(client1.getOutputStream());
                for_clinet_1.writeUTF("not quess yor number is Lower");
            } else if(client1_rand > server_rand) {
                DataOutputStream for_clinet_1 = new DataOutputStream(client1.getOutputStream());
                for_clinet_1.writeUTF("not quess yor number is Higher");
            }
            
            
            
           
            
            DataInputStream cli2_num = new DataInputStream(client2.getInputStream());
            int number2 = cli2_num.readInt();
            Integer client2_rand = new Integer(number2);
            DataOutputStream for_clinet_2 = new DataOutputStream(client2.getOutputStream());
            if(server_rand.equals(client2_rand)){
                for_clinet_2.writeUTF("you quest yor number is correct");
                x = false;
                 cli1_num.close();
                for_clinet_2.close();
                break;
            } else if(client2_rand < server_rand) {
                for_clinet_2.writeUTF("not quess yor number is Lower");
            } else if(client2_rand > server_rand) {
                for_clinet_2.writeUTF("not quess yor number is Higher");
            }
            
            
            
            DataInputStream cli3_num = new DataInputStream(client3.getInputStream());
            int number3 = cli3_num.readInt();
            Integer client3_rand = new Integer(number3);
            DataOutputStream for_clinet_3 = new DataOutputStream(client3.getOutputStream());
            if(server_rand.equals(client3_rand)){
                for_clinet_3.writeUTF("your number is correct");
                x = false;
                cli1_num.close();
                for_clinet_3.close();
                break;
            } else if(client3_rand < server_rand) {
                for_clinet_3.writeUTF("not quess yor number is Lower");
            } else if(client3_rand > server_rand) {
                for_clinet_3.writeUTF("not quess yor number is Higher");
            }
           
            
            
            
            
            
            
            
            
            }
            
       client1.close();
       client2.close();
       client3.close();
       serversocket1.close();
        }
        

}
