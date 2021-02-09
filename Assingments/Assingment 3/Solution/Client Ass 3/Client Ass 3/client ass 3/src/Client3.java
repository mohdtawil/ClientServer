
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
public class Client3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket socket3 = new Socket("localhost" , 4040);
            boolean FLAG = true;
//            do
            while(FLAG){
            System.out.println("--------------------------------------------");
            
            DataOutputStream out2 = new DataOutputStream(socket3.getOutputStream());
            System.out.println("Enter a guess number");
            Scanner input2 = new Scanner(System.in);
            String number1 = input2.nextLine();
            int n = Integer.parseInt(number1);
            out2.writeInt(n);
            
            
            DataInputStream in1 = new DataInputStream(socket3.getInputStream());
            String msg_income = in1.readUTF();
            System.out.println("message form server, " + msg_income);
            
             if(msg_income.equals("your number is correct")) {
                FLAG=false;
                out2.close();
                in1.close();
                socket3.close();
                break;
            }
     
           }
           
           
            
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
