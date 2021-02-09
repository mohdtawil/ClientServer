
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
            Socket socket3 = new Socket("localhost" ,3343);
            
            String Massage1;
            boolean x = true;
//            do
            while(x){
            
            DataInputStream in1 = new DataInputStream(socket3.getInputStream());
            String msg_income = in1.readUTF();
            System.out.println("message : " + msg_income);
            
            DataInputStream in2 = new DataInputStream(socket3.getInputStream());
            int number_income = in2.readInt();
            System.out.println("income from  : " + number_income);
            
            System.out.println("--------------------------------------------");
            DataOutputStream out1 = new DataOutputStream(socket3.getOutputStream());
            System.out.println("Enter a massage");
            Scanner input1 = new Scanner(System.in);
            Massage1 = input1.nextLine();
            out1.writeUTF(Massage1);
            
            DataOutputStream out2 = new DataOutputStream(socket3.getOutputStream());
            System.out.println("Enter a number");
            Scanner input2 = new Scanner(System.in);
            String number1 = input2.nextLine();
            int n = Integer.parseInt(number1);
            out2.writeInt(n);
            
            
            if(Massage1.equals("stop") || Massage1.equals("end")) {
                x=false;
                out1.close();
                out2.close();
                in1.close();
                in2.close();
                socket3.close();
                break;
            }
            
            
           }
            
        } catch (IOException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
