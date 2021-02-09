/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientproject;

import static clientproject.Server.List_of_Items;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Librarian {

    public static String LIBRARIAN_AddToItemsList() {
        
        //read Number
        System.out.print("Enter New Item Number : ");
        Scanner input_Number = new Scanner(System.in);
        String Number = input_Number.nextLine();
        
        
        //read Category
        System.out.print("Enter New Item Category : ");
        Scanner input_Category = new Scanner(System.in);
        String Category = input_Category.nextLine();
        
        
         //read Availability
        System.out.print("Enter New Item Availability Type true OR false : ");
        Scanner input_Availability = new Scanner(System.in);
        String Availability = input_Availability.nextLine();
        
        
         //read ExpectedReturnDate
        System.out.print("Enter New Item Expected Return Date Type DD/MM/YYYY : ");
        Scanner input_ExpectedReturnDate = new Scanner(System.in);
        String ExpectedReturnDate = input_ExpectedReturnDate.nextLine();
        
        String ALL_0NE_STRING = Number + "," + Category + "," + Availability + "," + ExpectedReturnDate;
        
        return  ALL_0NE_STRING;
    }
    public static String LIBRARIAN_RemoveFromItemFromList() {
        
        //read Number
        System.out.print("Enter Item Number For Remove : ");
        Scanner input_Number = new Scanner(System.in);
        String Number = input_Number.nextLine();
        
        return Number;
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("------ LIBRARIAN ------");
        
        // Socket
        Socket socket1 = new Socket("localhost" , 4040);
        
        while(true) {
        // List Of Features LIBRARIAN Can Do
        System.out.println("Your Features:");
        System.out.println("1- Add New Item");
        System.out.println("2- Remove Spicfic Item");
        System.out.println("3- Show All Items");
        System.out.println("4- Show Spicfic Teacher List");
        System.out.println("5- Show Spicfic Student List");
        System.out.println("6- Show All Transactions History");
        System.out.println("7- Change The Availabilty For Spicfic Item");
        System.out.println("8- Show All Waiting List");
        System.out.println("9- Show All Reserved List");
        System.out.println("----------------------------------------------");
        
        System.out.print("Enter A Number Of Choice : ");
        Scanner readkey = new Scanner(System.in);
        String key = readkey.nextLine();
        
        DataOutputStream SendKey = new DataOutputStream(socket1.getOutputStream());
        SendKey.writeUTF("L-"+key);
        
        switch(key) {
            //------------- Add New Item
            case "1": {
                System.out.println("************ Add New Item ************");
                
                String NewItem = LIBRARIAN_AddToItemsList();
                
                DataOutputStream SendNewItem = new DataOutputStream(socket1.getOutputStream());
                SendNewItem.writeUTF(NewItem);
       
                System.out.println("Done");
                
                break; 
            }
            //------------- Remove Spicfic Item
            case "2": {
                System.out.println("************ Remove Spicfic Item ************");
                
                String RemoveItemNumber = LIBRARIAN_RemoveFromItemFromList();
                
                DataOutputStream SendRemoveItemNumber = new DataOutputStream(socket1.getOutputStream());
                SendRemoveItemNumber.writeUTF(RemoveItemNumber);
                
                System.out.println("Done");
                
                break;
            }
            //------------- Show All Items
            case "3": {
                System.out.println("************ Show All Items ************");
                
                DataInputStream GetAllItems = new DataInputStream(socket1.getInputStream());
                String AllItems = GetAllItems.readUTF();
                
                System.out.println("All Items )>  " + AllItems);
                
                System.out.println("Done");
                
                break;
            }
            //------------- Show Spicfic Teacher List
            case "4": {
                System.out.println("************ Show Spicfic Teacher List ************");
                
                System.out.print("Enter Teacher Name : ");
                Scanner readTeacherName = new Scanner(System.in);
                String TeacherName = readTeacherName.nextLine();
                
                DataOutputStream SendTeacherName = new DataOutputStream(socket1.getOutputStream());
                SendTeacherName.writeUTF(TeacherName);
                
                DataInputStream GetTeacherList = new DataInputStream(socket1.getInputStream());
                String TeacherList = GetTeacherList.readUTF();
                
                System.out.println("Teacher List )>  " +TeacherList);
                
                System.out.println("Done");
                
                break;
            }
            //------------- Show Spicfic Student List
            case "5": {
                 System.out.println("************ Show Spicfic Student List ************");
                
                System.out.print("Enter Student Name : ");
                Scanner readStudentName = new Scanner(System.in);
                String StudentName = readStudentName.nextLine();
                
                DataOutputStream SendStudentName = new DataOutputStream(socket1.getOutputStream());
                SendStudentName.writeUTF(StudentName);
                
                DataInputStream GetStudentList = new DataInputStream(socket1.getInputStream());
                String StudentList = GetStudentList.readUTF();
                
                System.out.println("Student List )>  " +StudentList);
                
                System.out.println("Done");
                
                break;
            }
            //------------- Show All Transactions History
            case "6": {
                 System.out.println("************ Show All Transactions History ************");
                 
                DataInputStream GetAllTranactionHistory = new DataInputStream(socket1.getInputStream());
                String AllTranactionHistory = GetAllTranactionHistory.readUTF();
                
                System.out.println("All Tranaction History )>  " +AllTranactionHistory);
                 
                 System.out.println("Done");
                 
                break;
            }
            //------------- Change The Availabilty For Spicfic Item
            case "7": {
                System.out.println("************ Change The Availabilty For Spicfic Item ************");
                
                System.out.print("Enter Item Number : ");
                Scanner readItemNumber = new Scanner(System.in);
                String ItemNumber = readItemNumber.nextLine();
                
                DataOutputStream SendItemNumber = new DataOutputStream(socket1.getOutputStream());
                SendItemNumber.writeUTF(ItemNumber);
                
                System.out.print("The Availabilty Will Toggle Automaticlly ...");
                
                
                System.out.println("Done");
                break;
            }
            //------------- Show All Waiting List
            case "8": {
                System.out.println("************ Show All Waiting List ************");
                
                DataInputStream GetAllWaitingList = new DataInputStream(socket1.getInputStream());
                String AllWaitingList = GetAllWaitingList.readUTF();
                
                System.out.println("All Waiting List )>  " + AllWaitingList);
                 
                 System.out.println("Done");
                 
                break;
            }
            //------------- Show All Reserved List
            case "9": {
                System.out.println("************ Show All Reserved List ************");
               
                DataInputStream GetAllReservedList = new DataInputStream(socket1.getInputStream());
                String AllReservedList = GetAllReservedList.readUTF();
                
                System.out.println("All Reserved List )>  " +AllReservedList);
                 
                System.out.println("Done");
                
                break;
            }
            default: {
                System.out.println("Your Chice Incorrect !!!!!");
                break;
            }
        }
        /* Get return Available message from system */
        DataInputStream GetReturnAvailableMessages = new DataInputStream(socket1.getInputStream());
        String ReturnAvailableMessages = GetReturnAvailableMessages.readUTF();
                
        System.out.println("---------------");
        System.out.println("Return Available Message From System )>  " + ReturnAvailableMessages);
        
        
        /* Get Late return message from system */
        DataInputStream GetLateReturnMessage = new DataInputStream(socket1.getInputStream());
        String LateReturnMessage = GetLateReturnMessage.readUTF();
                
        
        System.out.println("Late Return Message From System )>  " + LateReturnMessage);
        System.out.println("---------------");
        
        }
        
        
       

    }
    
}
