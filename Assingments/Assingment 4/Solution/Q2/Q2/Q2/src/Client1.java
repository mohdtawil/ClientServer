
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Client1 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Socket socket1 = new Socket("localhost" , 4040);
            boolean FLAG = true;
//            do

            while (FLAG) {
                
                DataInputStream get_board = new DataInputStream(socket1.getInputStream());
                System.out.println("This is a Board");
                //System.out.println("Done");
                System.out.println(get_board.readUTF());
                System.out.print("Enter a number you want to change to X : ");
                Scanner input1 = new Scanner(System.in);
                String number = input1.nextLine();
                int int_number = Integer.parseInt(number);
                DataOutputStream send_board = new DataOutputStream(socket1.getOutputStream());
                send_board.writeInt(int_number);
                DataInputStream get_board_after = new DataInputStream(socket1.getInputStream());
                System.out.println("This is a Board Now");
                System.out.println(get_board_after.readUTF());
                
              
                
                
                
            }
           
           socket1.close();
           
           
            
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
