
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
public class S3 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("------ S3 ------");
        Boolean x = true;
        while(x) {
        ServerSocket serversocket1 = new ServerSocket(2020); 
        Socket s2 = serversocket1.accept();
        Socket s4 = serversocket1.accept();
        
        DataInputStream out = new DataInputStream(s2.getInputStream());
        int number_from_s2 = out.readInt();
        
        System.out.println(number_from_s2);
        Socket socket1 = new Socket("localhost" , 3030);
        
    
        int min = 0;
        int max = 10;
        int random = (int)(Math.random() * (max - min + 1) + min);
        
        
        DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
        System.out.println(random + number_from_s2);
        in.writeInt(random + number_from_s2);
        
        
        // read stop msg and sent
        DataInputStream out_stop_from_2 = new DataInputStream(s2.getInputStream());
        if(out_stop_from_2.readUTF().equals("stop")) {
        System.out.println(out_stop_from_2.readUTF());
        DataOutputStream in_stop_to_4 = new DataOutputStream(s4.getOutputStream());
        in_stop_to_4.writeUTF(out_stop_from_2.readUTF());
        
        
            socket1.close();
            in.close();
            s4.close();
            s2.close();
            out_stop_from_2.close();
            in_stop_to_4.close();
            x=false;
            break;
        } else if(out_stop_from_2.readUTF().equals("continue")) {
             x=true;
            continue;
        }
        
    }
    }
    
}
