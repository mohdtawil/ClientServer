/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Cashier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         System.out.println("------ Cashier ------");
        Socket socket1 = new Socket("localhost" , 4040);
        
        double Total = 0;
        
        System.out.print("Enter UserName : ");
        Scanner input_username = new Scanner(System.in);
        String username = input_username.nextLine();
        
        System.out.print("Enter Password : ");
        Scanner input_password = new Scanner(System.in);
        String password = input_password.nextLine();
        
        
        DataOutputStream Send = new DataOutputStream(socket1.getOutputStream());
        Send.writeUTF(username+":"+password);
        
        while(true) {
        
        System.out.println("Cashier Features : ");
        System.out.println("1- Add Product To cart");
        System.out.println("2- Remove Product From cart");
        System.out.println("3- checkout");
        System.out.print("Enter Choice : ");
        Scanner input_Choice = new Scanner(System.in);
        String Choice = input_Choice.nextLine();
        
        DataOutputStream Send_Choice = new DataOutputStream(socket1.getOutputStream());
        Send_Choice.writeUTF(Choice);
        
        switch(Choice) {
            case "1": {
                System.out.println("Add Product To cart ");
                System.out.print("Enter product number : ");
                Scanner input_number = new Scanner(System.in);
                String number = input_number.nextLine();

                System.out.print("Enter product quantity : ");
                Scanner input_quantity = new Scanner(System.in);
                String quantity = input_quantity.nextLine();
                
                 DataOutputStream Send_Product = new DataOutputStream(socket1.getOutputStream());
                 Send_Product.writeUTF(number+":"+quantity);
                 
                 DataInputStream Get = new DataInputStream(socket1.getInputStream());
                 Total += Get.readDouble();
                break;
            }
            case "2": {
                 System.out.println("Remove Product From cart ");
                System.out.print("Enter product number : ");
                Scanner input_number = new Scanner(System.in);
                String number = input_number.nextLine();

                System.out.print("Enter product quantity : ");
                Scanner input_quantity = new Scanner(System.in);
                String quantity = input_quantity.nextLine();
                
                 DataOutputStream Send_Product = new DataOutputStream(socket1.getOutputStream());
                 Send_Product.writeUTF(number+":"+quantity);
                 
                 DataInputStream Get = new DataInputStream(socket1.getInputStream());
                 Total -= Get.readDouble();
                break;
            }
            case "3": {
                System.out.println("checkout ");
                System.out.print("Enter total paid : ");
                Scanner input_paid = new Scanner(System.in);
                String n = input_paid.nextLine();
                double paid = Double.parseDouble(n);
                
                DataOutputStream Send_paid = new DataOutputStream(socket1.getOutputStream());
                Send_paid.writeDouble(paid);
                
                DataOutputStream Send_total= new DataOutputStream(socket1.getOutputStream());
                Send_total.writeDouble(Total);
                
                DataInputStream Get = new DataInputStream(socket1.getInputStream());
                System.out.println(Get.readUTF());
                break;
            }
        }
        }
        
        
    }
    
}
