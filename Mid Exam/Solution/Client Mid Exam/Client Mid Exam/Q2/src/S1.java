
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class S1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        System.out.println("------ S1 ------");
        Boolean x = true;
        while(x) {
        Socket socket1 = new Socket("localhost" , 1010);
        
        int min = 0;
        int max = 10;
        int random = (int)(Math.random() * (max - min + 1) + min);
        
        DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
        in.writeInt(random);
        
        ServerSocket serversocket1 = new ServerSocket(4040); 
        Socket s4 = serversocket1.accept();
        Socket s2 = serversocket1.accept();
        
        
        DataInputStream out = new DataInputStream(s4.getInputStream());
        int number_from_s4 = out.readInt();
        
         //System.out.println(number_from_s4);
        if(number_from_s4>30 && number_from_s4 >25) {
            DataOutputStream in_stop_to_2 = new DataOutputStream(s2.getOutputStream());
            in_stop_to_2.writeUTF("stop");
            System.out.println("Summation sumbers from S1,S2,S3 and S4 : " + number_from_s4);
            
            socket1.close();
            in.close();
            s4.close();
            s2.close();
            in_stop_to_2.close();
            x=false;
            break;
        } else if(number_from_s4 > 30 && number_from_s4 < 25) {
            DataOutputStream in_continue_to_2 = new DataOutputStream(s2.getOutputStream());
            in_continue_to_2.writeUTF("continue");
            x=true;
            continue;
        }
        
        
        }
        //in_continue_to_2.close();
    }
    
}
