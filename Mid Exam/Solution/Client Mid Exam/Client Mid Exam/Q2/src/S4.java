
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
public class S4 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        System.out.println("------ S4 ------");
        Boolean x = true;
        while(x){
        ServerSocket serversocket1 = new ServerSocket(3030); 
        Socket s1 = serversocket1.accept();
        Socket s3 = serversocket1.accept();
        
        DataInputStream out = new DataInputStream(s1.getInputStream());
        int number_from_s3 = out.readInt();
        
        
        Socket socket1 = new Socket("localhost" , 1010);
        
        
        
 
        int min = 0;
        int max = 10;
        int random = (int)(Math.random() * (max - min + 1) + min);
        
        
        DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
        System.out.println(random + number_from_s3);
        in.writeInt(random + number_from_s3);
        
        
        // read stop msg and sent
        DataInputStream out_stop_from_3 = new DataInputStream(s3.getInputStream());
        if(out_stop_from_3.equals("continue")) {
        System.out.println(out_stop_from_3.readUTF());
        
        
            socket1.close();
            in.close();
            s1.close();
            s3.close();
            out_stop_from_3.close();
            x=false;
            break;
        } else if(out_stop_from_3.equals("continue")) {
            x=true;
            continue;
        }
    }
          
    }
    
}
