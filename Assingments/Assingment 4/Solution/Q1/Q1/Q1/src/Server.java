
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.Math; 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket serversocket1 = new ServerSocket(4040); 
                Socket client1 = serversocket1.accept()) {
            //        Socket client2 = serversocket1.accept();
//        Socket client3 = serversocket1.accept();
boolean FLAG = true;
System.out.println("client 1 , 2 , 3  is connected");
while (FLAG) {
    System.out.println("*****");
    DataInputStream operation = new DataInputStream(client1.getInputStream());
    String operation_income = operation.readUTF();
    System.out.println(operation_income);
    switch (operation_income) {
        case "sqr":
        {
            DataInputStream number = new DataInputStream(client1.getInputStream());
            double number_sqrt_income = number.readDouble();
            
            double result = Math.sqrt(number_sqrt_income);
            System.out.println(result);
            DataOutputStream out_sqrt_number = new DataOutputStream(client1.getOutputStream());
            out_sqrt_number.writeDouble(result);
            
            
            break;
        }
        case "sqrroot":
        {
            DataInputStream number = new DataInputStream(client1.getInputStream());
            double number_sqrroot_income = number.readDouble();
            
            double result = Math.cbrt(number_sqrroot_income);
            
            DataOutputStream out_sqrroot_number = new DataOutputStream(client1.getOutputStream());
            out_sqrroot_number.writeDouble(result);
            
            
            break;
        }
        case "+":
        {
            
            DataInputStream number1 = new DataInputStream(client1.getInputStream());
            double number1_income = number1.readDouble();
            
            DataInputStream number2 = new DataInputStream(client1.getInputStream());
            double number2_income = number2.readDouble();
            
            double result = number1_income + number2_income;
            
            DataOutputStream out_plus_numbers = new DataOutputStream(client1.getOutputStream());
            out_plus_numbers.writeDouble(result);
            
            
            break;
        }
        case "-":
        {
            DataInputStream number1 = new DataInputStream(client1.getInputStream());
            double number1_income = number1.readDouble();
            
            DataInputStream number2 = new DataInputStream(client1.getInputStream());
            double number2_income = number2.readDouble();
            
            double result = number1_income - number2_income;
            
            DataOutputStream out_plus_numbers = new DataOutputStream(client1.getOutputStream());
            out_plus_numbers.writeDouble(result);
            
            
            break;
        }
        case "*":
        {
            DataInputStream number1 = new DataInputStream(client1.getInputStream());
            double number1_income = number1.readDouble();
            
            DataInputStream number2 = new DataInputStream(client1.getInputStream());
            double number2_income = number2.readDouble();
            
            double result = number1_income * number2_income;
            
            DataOutputStream out_plus_numbers = new DataOutputStream(client1.getOutputStream());
            out_plus_numbers.writeDouble(result);
            
            
            break;
        }
        case "/":
        {
            DataInputStream number1 = new DataInputStream(client1.getInputStream());
            double number1_income = number1.readDouble();
            
            DataInputStream number2 = new DataInputStream(client1.getInputStream());
            double number2_income = number2.readDouble();
            
            double result = number1_income / number2_income;
            
            DataOutputStream out_plus_numbers = new DataOutputStream(client1.getOutputStream());
            out_plus_numbers.writeDouble(result);
            
            
            break;
        }
        case "stop":
            FLAG = false;
            break;
        default:
            break;
    }
    
}
//       client2.close();
//       client3.close();
        }
        }
        

}
