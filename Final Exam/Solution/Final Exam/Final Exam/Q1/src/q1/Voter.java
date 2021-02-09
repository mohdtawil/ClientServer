/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author USER
 */
public class Voter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        System.out.println("------ Client ------");
        // TODO code application logic here
        Socket socket1 = new Socket("localhost" , 4040);
        int counter = 0;
        while(counter<=100) {
            // make a random number 
            int min = 1;
            int max = 3;
            int random = (int)(Math.random() * (max - min + 1) + min);
            Integer Random_Number = new Integer(random);

            DataOutputStream Send = new DataOutputStream(socket1.getOutputStream());
            Send.writeInt(Random_Number);
            
            counter++;
        }
        socket1.close();
        
        
        
        
    }
    
}
