
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
public class Client2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("------ CLIENT 2 ------");
        Socket socket1 = new Socket("localhost" , 3333);
        Boolean x = true;
        while(x)
        {
            int min = 1;
            int max = 3;
            int random = (int)(Math.random() * (max - min + 1) + min);
           
            
            switch(random)
            {
                case 1:
                {
                   
                    DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
                    in.writeUTF("scissors");
                    break;
                }
                case 2:
                {
                   
                    DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
                    in.writeUTF("stone");
                    break;
                }
                case 3:
                {
                   
                    DataOutputStream in = new DataOutputStream(socket1.getOutputStream());
                    in.writeUTF("paper");
                    break;
                }
                
            }
            
            DataInputStream out = new DataInputStream(socket1.getInputStream());
           String get_from_server = out.readUTF();
            if(get_from_server.equals("the guess is the same continue")) {
                x=true;
                continue;
            } else {
                System.out.println(get_from_server);
                x=false;
                out.close();
                socket1.close();
                break;
            }
        }
        
    }
    
}
