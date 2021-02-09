
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
public class Client1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket socket1 = new Socket("localhost" , 4040);
            boolean FLAG = true;
//            do
            OUTER:
            while (FLAG) {
                System.out.print("Enter a one of these operation ( +  ,  -  ,  *  ,  /  ,  sqr  ,  sqrroot ) OR stop to exit : ");
                Scanner input3 = new Scanner(System.in);
                String operation = input3.nextLine();
                switch (operation) {
                    case "sqr":
                    case "sqrroot":
                        {
                            // send operation
                            DataOutputStream out_operation = new DataOutputStream(socket1.getOutputStream());
                            out_operation.writeUTF(operation);
                            // read number
                            System.out.print("Now you must enter a single number : ");
                            Scanner input = new Scanner(System.in);
                            String number = input.nextLine();
                            double n = Double.parseDouble(number);
                            // send number
                            DataOutputStream out_number = new DataOutputStream(socket1.getOutputStream());
                            out_number.writeDouble(n);
                            break;
                        }
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        {
                            // send operation
                            DataOutputStream out_operation = new DataOutputStream(socket1.getOutputStream());
                            out_operation.writeUTF(operation);
                            System.out.println("Now you must enter two numbers");
                            System.out.println("------------------------------");
                            // read number 1
                            System.out.print("Enter number 1 : ");
                            Scanner input1 = new Scanner(System.in);
                            String number1 = input1.nextLine();
                            double n1 = Double.parseDouble(number1);
                            // send number 1
                            DataOutputStream out_number_1 = new DataOutputStream(socket1.getOutputStream());
                            out_number_1.writeDouble(n1);
                            // read number 2
                            System.out.print("Enter number 2 : ");
                            Scanner input2 = new Scanner(System.in);
                            String number2 = input2.nextLine();
                            double n2 = Double.parseDouble(number2);
                            // send number 2
                            DataOutputStream out_number_2 = new DataOutputStream(socket1.getOutputStream());
                            out_number_2.writeDouble(n2);
                            break;
                        }
                    case "stop":
                        FLAG = false;
                        break OUTER;
                    default:
                        break;
                }
                DataInputStream in = new DataInputStream(socket1.getInputStream());
                double result_income = in.readDouble();
                System.out.println("The result is : " + result_income);
            }
            
           
           
            
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
