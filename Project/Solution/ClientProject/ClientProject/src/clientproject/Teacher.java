/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientproject;


import static clientproject.Student.STUDENT_RequsetToReserveAnItem;
import static clientproject.Student.STUDENT_RequsetToReturnItem;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Teacher {

   
     public static String TEACHER_RequsetToReserveAnItem() {
        
        //read TeacherName
        System.out.print("Enter Your Name : ");
        Scanner input_TeacherName = new Scanner(System.in);
        String TeacherName = input_TeacherName.nextLine();
        
        //read ItemNumber
        System.out.print("Enter Item Number You Want To Take It : ");
        Scanner input_ItemNumber = new Scanner(System.in);
        String ItemNumber = input_ItemNumber.nextLine();
        
        
        return "T-" + TeacherName + ":" + ItemNumber;
    }
     public static String TEACHER_RequsetToReturnItem() {
        
        //read StudentName
        System.out.print("Enter Your Name : ");
        Scanner input_TeacherName = new Scanner(System.in);
        String TeacherName = input_TeacherName.nextLine();
        
        //read ItemNumber
        System.out.print("Enter Item Number You Want To Return It : ");
        Scanner input_ItemNumber = new Scanner(System.in);
        String ItemNumber = input_ItemNumber.nextLine();
        
        
        return "T-" + TeacherName + ":" + ItemNumber;
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("------ TEACHER ------");
        
        // Socket
        Socket socket1 = new Socket("localhost" , 4040);
        
        while(true) {
        // List Of Features TEACHER Can Do
        System.out.println("Your Features:");
        System.out.println("1- Make Requset To Reserve Item");
        System.out.println("2- Make Requset To Return Item");
        System.out.println("3- Show My List");
        System.out.println("----------------------------------------------");
        
        System.out.print("Enter A Number Of Choice : ");
        Scanner readkey = new Scanner(System.in);
        String key = readkey.nextLine();
        
        DataOutputStream SendKey = new DataOutputStream(socket1.getOutputStream());
        SendKey.writeUTF("T-"+key);
        
        switch(key) {
            //------------- Add New Item
            case "1": {
                System.out.println("************ Requset To Reserve Item ************");
                
                String NewReserveItem = TEACHER_RequsetToReserveAnItem();
                
                //DataOutputStream to send Requset To Reserve Item to Server 
                DataOutputStream SendNewReserveItem = new DataOutputStream(socket1.getOutputStream());
                SendNewReserveItem.writeUTF(NewReserveItem);
       
                DataInputStream GetReturnMessage = new DataInputStream(socket1.getInputStream());
                String ReturnMessage = GetReturnMessage.readUTF();
                
                System.out.println("Return Message )>  " + ReturnMessage);
                
                System.out.println("Done");
                
                break; 
            }
            //------------- Remove Spicfic Item
            case "2": {
                System.out.println("************ Requset To Return Item ************");
                
                String NewReturnItem = TEACHER_RequsetToReturnItem();
                
                //DataOutputStream to send Requset To Return Item to Server 
                DataOutputStream SendNewReturnItem = new DataOutputStream(socket1.getOutputStream());
                SendNewReturnItem.writeUTF(NewReturnItem);
                
                DataInputStream GetReturnMessage = new DataInputStream(socket1.getInputStream());
                String ReturnMessage = GetReturnMessage.readUTF();
                
                System.out.println("Return Message )>  " + ReturnMessage);
                
                System.out.println("Done");
                
                break;
            }
            //------------- Show All Items
            case "3": {
                System.out.println("************ Show My List ************");
                
                System.out.print("Enter Your Name : ");
                Scanner readYourName = new Scanner(System.in);
                String YourName = readYourName.nextLine();
                
                DataOutputStream SendTeacherName = new DataOutputStream(socket1.getOutputStream());
                SendTeacherName.writeUTF(YourName);
                
                DataInputStream GetTeacherList = new DataInputStream(socket1.getInputStream());
                String TeacherList = GetTeacherList.readUTF();
                
                System.out.println("Teacher List )>  " + TeacherList);
                
                
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
        
        
        /* GetLate Return Message from system */
        DataInputStream GetLateReturnMessage = new DataInputStream(socket1.getInputStream());
        String LateReturnMessage = GetLateReturnMessage.readUTF();
                
        
        System.out.println("Late Return Message From System )>  " + LateReturnMessage);
        System.out.println("---------------");
        }
        
        /*
        //DataOutputStream to send Requset To Reserve Item to Server 
        DataOutputStream SendRequsetReserveItem = new DataOutputStream(socket1.getOutputStream());
        String RequsetReserveItem = TEACHER_RequsetToReserveAnItem();
        SendRequsetReserveItem.writeUTF(RequsetReserveItem);
        */
    }
    
}
