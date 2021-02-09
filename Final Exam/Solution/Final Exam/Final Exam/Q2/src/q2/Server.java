/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Server {

   
    
   public static HashMap<Integer, HashMap> List_of_Products = new HashMap<Integer , HashMap>();
    public static int ProductsCounter = 1;
    
    public static void createProducts() {
        
        HashMap<String, String> newProduct1 = new HashMap<>();
        newProduct1.put("Name","Shampoo");
        newProduct1.put("Number","122");
        newProduct1.put("Unit","C");
        newProduct1.put("Count","1");
        newProduct1.put("KGM","none");
        newProduct1.put("Price","5.99");
        newProduct1.put("Status","none");
        List_of_Products.put(ProductsCounter++, newProduct1);
        
        HashMap<String, String> newProduct2 = new HashMap<>();
        newProduct2.put("Name","Tomato");
        newProduct2.put("Number","123");
        newProduct2.put("Unit","K");
        newProduct2.put("Count","none");
        newProduct2.put("KGM","1");
        newProduct2.put("Price","0.89");
        newProduct2.put("Status","none");
        List_of_Products.put(ProductsCounter++, newProduct2);
        
        HashMap<String, String> newProduct3 = new HashMap<>();
        newProduct3.put("Name","Rice");
        newProduct3.put("Number","124");
        newProduct3.put("Unit","C");
        newProduct3.put("Count","1");
        newProduct3.put("KGM","none");
        newProduct3.put("Price","3.99");
        newProduct3.put("Status","none");
        List_of_Products.put(ProductsCounter++, newProduct3);
        
        HashMap<String, String> newProduct4 = new HashMap<>();
        newProduct4.put("Name","Potato");
        newProduct4.put("Number","125");
        newProduct4.put("Unit","K");
        newProduct4.put("Count","none");
        newProduct4.put("KGM","1");
        newProduct4.put("Price","0.79");
        newProduct4.put("Status","none");
        List_of_Products.put(ProductsCounter++, newProduct4);
        
        HashMap<String, String> newProduct5 = new HashMap<>();
        newProduct5.put("Name","Sugar");
        newProduct5.put("Number","126");
        newProduct5.put("Unit","C");
        newProduct5.put("Count","1");
        newProduct5.put("KGM","none");
        newProduct5.put("Price","2.99");
        newProduct5.put("Status","none");
        List_of_Products.put(ProductsCounter++, newProduct5);
        
        HashMap<String, String> newProduct6 = new HashMap<>();
        newProduct6.put("Name","Banana");
        newProduct6.put("Number","127");
        newProduct6.put("Unit","K");
        newProduct6.put("Count","none");
        newProduct6.put("KGM","1");
        newProduct6.put("Price","0.99");
        newProduct6.put("Status","none");
        List_of_Products.put(ProductsCounter++, newProduct6);
    }
    private static boolean CheckLogin(String username , String password) {
          String Username = "Mohammad";
          String Password = "118256";
        boolean check = false;
        if(Username.equals(username) && Password.equals(password)) {
            check = true;
        }
        return check;
    }
    private static double CalculateProduct(String number , String quantity) {
        double Return = -1;
        for (int index : List_of_Products.keySet()) {
            if(List_of_Products.get(index).get("Number").equals(number)) {
                int Quntity = Integer.parseInt(quantity);
                String price = List_of_Products.get(index).get("Price").toString();
                double Price = Double.parseDouble(price);
                Return = Quntity * Price;
                List_of_Products.get(index).put("Status", "buyed");
            }
        }
        return Return;
    }
    
    
    public static void main(String[] args) throws IOException {
         System.out.println("------ Server ------");
        // TODO code application logic here
       ServerSocket serversocket1 = new ServerSocket(4040); 
       Socket socket1 = serversocket1.accept();
       createProducts();
       
       DataInputStream GetAuth = new DataInputStream(socket1.getInputStream());
       String NaemAndPassword  = GetAuth.readUTF();
       String [] GetParts = NaemAndPassword.split(":");
       String Uname = GetParts[0];
       String Pass = GetParts[1];
       
       if(CheckLogin(Uname , Pass)) {
          while(true) {
           
           DataInputStream  Get_Choice = new DataInputStream (socket1.getInputStream());
           String Choice = Get_Choice.readUTF();
           
           switch(Choice) {
            case "1": {
                DataInputStream GetPro = new DataInputStream(socket1.getInputStream());
                String NumberAndQuantity = GetPro.readUTF();
                String [] NumberAndQuantityParts = NumberAndQuantity.split(":");
                String Number = NumberAndQuantityParts[0];
                String Quantity = NumberAndQuantityParts[1];

                double ProductCost = CalculateProduct(Number ,Quantity);
 System.out.println(ProductCost);
 System.out.println(Number);
 System.out.println(Quantity);
                DataOutputStream Send = new DataOutputStream(socket1.getOutputStream());
                Send.writeDouble(ProductCost);
                
                break;
            }
            case "2": {
                 
                DataInputStream GetPro = new DataInputStream(socket1.getInputStream());
                String NumberAndQuantity = GetPro.readUTF();
                String [] NumberAndQuantityParts = NumberAndQuantity.split(":");
                String Number = NumberAndQuantityParts[0];
                String Quantity = NumberAndQuantityParts[1];
               
                double ProductCost = CalculateProduct(Number ,Quantity);
 System.out.println(ProductCost);
                DataOutputStream Send = new DataOutputStream(socket1.getOutputStream());
                Send.writeDouble(ProductCost);
                break;
            }
            case "3": {
                DataInputStream Getpaid = new DataInputStream(socket1.getInputStream());
                double paid = Getpaid.readDouble();
                
                DataInputStream Gettotal = new DataInputStream(socket1.getInputStream());
                double total = Gettotal.readDouble();
                
                double cost = paid - total;
                String Final = "";
                String msg = "\tSE_118256\t" + "\n" + "You Paid : " + paid + "\n" + "Your total : " + total  + "\n" +  "Your redraw : " + cost + "\n";
                
                String Items = "\n";
                 for (int index : List_of_Products.keySet()) {
                    if(List_of_Products.get(index).get("Status").equals("buyed")) {
                        Items+=List_of_Products.get(index).toString() + "\n";
                    }
                }
                Final =  msg + "\n \n All Items Buyed \n \n" +  Items;
                DataOutputStream Send = new DataOutputStream(socket1.getOutputStream());
                Send.writeUTF(Final);
                
                break;
            }
        }
           
          }
           
           
       } else {
           System.out.println("Login Faild");
       }
        
    }
    
}
