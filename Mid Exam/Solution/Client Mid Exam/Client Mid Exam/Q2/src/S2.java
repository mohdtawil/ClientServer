
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
public class S2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
       System.out.println("------ S2 ------");
       Boolean x = true;
       while(x) {
        ServerSocket serversocket1 = new ServerSocket(1010); 
        Socket s1 = serversocket1.accept();
        Socket s3 = serversocket1.accept();
        
        DataInputStream out = new DataInputStream(s1.getInputStream());
        int number_from_s1 = out.readInt();
        
        int min = 0;
        int max = 10;
        int random = (int)(Math.random() * (max - min + 1) + min);
        //System.out.println(random + number_from_s1);
        Socket socket1 = new Socket("localhost" , 2020);
        
        DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
        System.out.println(random + number_from_s1);
        in.writeInt(random + number_from_s1);
        
        // read stop msg and sent
        DataInputStream out_stop_from_1 = new DataInputStream(s1.getInputStream());
        if(out_stop_from_1.readUTF().equals("stop")) {
            System.out.println(out_stop_from_1.readUTF());
            DataOutputStream in_stop_to_3 = new DataOutputStream(s3.getOutputStream());
            in_stop_to_3.writeUTF(out_stop_from_1.readUTF());
        
            socket1.close();
            in.close();
            s1.close();
            s3.close();
            out_stop_from_1.close();
            in_stop_to_3.close();
            x=false;
            break;
        } else if(out_stop_from_1.readUTF().equals("continue")) {
            x=true;
            continue;
        }
        
    }
        
    }
    
}
