/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Server implements Runnable{
    // Server Socket 
    static ServerSocket serversocket1;
        
    // Clients
    Socket Client;
        
    public Server() throws IOException {
        
        Client = serversocket1.accept();
    }
    
    
    // lis of all items fill it by librarian
    public static HashMap<Integer, HashMap> List_of_Items = new HashMap<Integer , HashMap>();
    public static int ItemsCounter = 1;
    
    // list of all transaction in library fill it by system and librarian look to that list
    public static HashMap<Integer, HashMap> List_of_All_Transaction_Library = new HashMap<Integer , HashMap>();
    public static int TransactionLibraryCounter = 1;
    
    //           reserved like  ["Type-StudentORTeacherNAME:ItemNumber"] => ["S-Mohammad:10" , "T-Hamza:20"]
    public static Queue<String> WaitingList_StudentORTeacherNAME_And_ItemNumber = new LinkedList<>();
    //           reserved like  ["Type-StudentORTeacherNAME:ItemNumber"] => ["S-Mohammad:10" , "T-Hamza:20"]
    public static Queue<String> ReservedList_StudentORTeacherNAME_And_ItemNumber = new LinkedList<>();
    
    // add in List_of_Items
    static void SERVER_AddNewItem(int number , String category , boolean availability ,String expected_return_date) {
        
       
        HashMap<String, String> newItem = new HashMap<>();
        
        newItem.put("Number",Integer.toString(number));
        newItem.put("Category",category);
        newItem.put("Availability",Boolean.toString(availability));
        newItem.put("ExpectedReturnDate",expected_return_date);
           
            
        // insert new item to list of items
        List_of_Items.put(ItemsCounter++, newItem);
    }
    
    // add in List_of_All_Transaction_Library
    static void SERVER_AddNewTransaction(String Influencer,String Date , String Time , String Description ,String Secret) {
        
       
        HashMap<String, String> newTransation = new HashMap<>();
        
        newTransation.put("Influencer",Influencer);
        newTransation.put("Date",Date);
        newTransation.put("Time",Time);
        newTransation.put("Description",Description);
        newTransation.put("Secret",Secret);
           
            
        // insert new transation to list of transations
        List_of_All_Transaction_Library.put(TransactionLibraryCounter++, newTransation);
    }
    // function return Date Now Format DD/MM/YYYY
    public static String GetDateNow() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("DD/MM/YYYY");  
        LocalDateTime now = LocalDateTime.now();  
        return date.format(now);  
    }
     // function return Time Now Format HH:mm:ss
    public static String GetTimeNow() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return time.format(now);  
    }
    // All Items
    public static String AllItems() {
        return List_of_Items.toString();
    }
    // All Tranaction History
    public static String AllITranactionHistory() {
        return List_of_All_Transaction_Library.toString();
    }
    // All Items Spicfic Teacher
    public static String GetAllReservedList() {
        return ReservedList_StudentORTeacherNAME_And_ItemNumber.toString();
    }
    // All Items Spicfic Teacher
    public static String GetAllWaitingList() {
        return WaitingList_StudentORTeacherNAME_And_ItemNumber.toString();
    }
    // All Items Spicfic Teacher
    public static String ItemsSpicficTeacher(String TeacherName) {
        String OneString = "T-"+TeacherName;
        String List = "";
        for(String name : ReservedList_StudentORTeacherNAME_And_ItemNumber) {
            String [] parts = name.split(":");
            if(OneString.equals(parts[0])) {
                List += " "+name+" ";
            }
        }
        return List;
    }
    // All Items Spicfic Student
    public static String ItemsSpicficStudent(String StudentName) {
        String OneString = "S-"+StudentName;
        String List = "";
        for(String name : ReservedList_StudentORTeacherNAME_And_ItemNumber) {
            String [] parts = name.split(":");
            if(OneString.equals(parts[0])) {
                List += " "+name+" ";
            }
        }
        return List;
    }
    // ---------------------------------------------------------     Functions For Checking 
   
    // Check Item Number Is found Or Not In The List Of Items
    private static boolean CheckItemNumberFound(String ItemNumber) {
        
        boolean found = false;
        // loop for all list to find item number
        for (int index : List_of_Items.keySet()) {
            if(List_of_Items.get(index).get("Number").equals(ItemNumber)) {
                found = true;
            }
        }
        return found;
    } 
    // Check How Many Items The Student Or Teacher is Reserved And Return The Number
    private static int HowManyReservedItemTaken(String ClientType , String ClientName , String ItemNumber) {
        
        int ReservedItemCounter = 0;
        
        for (String ReservedItem: ReservedList_StudentORTeacherNAME_And_ItemNumber) {
           
           String [] ReservedItemParts = ReservedItem.split(":");
           String [] NameParts = ReservedItemParts[0].split("-");

           // which is S or T to know the type of client becouse STUDENT maximum 3 ReserveItem and TEACHER maximum 6 ReserveItem
           String ReservedClientType = NameParts[0];
           String ReservedClientName = NameParts[1];
           
           if(ReservedClientType.equals(ClientType) && ReservedClientName.equals(ClientName)) {
               ReservedItemCounter++;
           }
        }
        
        return ReservedItemCounter;
       
    } 
    // Check Availability For Item 
     private static boolean CheckItemIsAvailableOrNot(String ItemNumber) {
         boolean Check = false;
         for (int index : List_of_Items.keySet()) {
            if(List_of_Items.get(index).get("Number").equals(ItemNumber)) {
                String AvailabilityValue = List_of_Items.get(index).get("Availability").toString();
                if(AvailabilityValue.equals("true")) {
                    Check = true;
                } else {
                    Check = false;
                }
            }
        }
        return Check;
     }
     // Change The Availability For Specific Item
     private static void ChangeToggleTheAvailability(String ItemNumber) {
         for (int index : List_of_Items.keySet()) {
            if(List_of_Items.get(index).get("Number").equals(ItemNumber)) {
                String AvailabilityValue = List_of_Items.get(index).get("Availability").toString();
                if(AvailabilityValue.equals("true")) {
                    List_of_Items.get(index).put("Availability" , "false");
                    String DESC = "Change The Availability For Item Number " + ItemNumber + " From true To false ";
                    SERVER_AddNewTransaction("System" , GetDateNow() , GetTimeNow() ,DESC, "none");
                } else {
                    List_of_Items.get(index).put("Availability" , "true");
                }
            }
        }
     }
    // Check Items is taken in list of ReservedItems
    private static boolean ItemIsTakenByOtherOne(String ItemNumber) {
        
        boolean Taken = false;
        
        for (String ReservedItem: ReservedList_StudentORTeacherNAME_And_ItemNumber) {
           
           String [] ReservedItemParts = ReservedItem.split(":");
           String ReservedItemNumber = ReservedItemParts[1];
           
           if(ReservedItemNumber.equals(ItemNumber)) {
               Taken = true;
           }
        }
        
        return Taken;  
   } 
    
    
   // ---------------------------------------------------------     WaitingList Functions
    private static void AddToWaitingList(String NameAndItemNumber) {
        WaitingList_StudentORTeacherNAME_And_ItemNumber.add(NameAndItemNumber + ":" + GetDateNow());
    }
    
    private static void RemoveFromWaitingList(String NameAndItemNumber) {
        WaitingList_StudentORTeacherNAME_And_ItemNumber.remove(NameAndItemNumber);
    }
    
    private static String GetFirstOneInWaitingList() {
        return WaitingList_StudentORTeacherNAME_And_ItemNumber.peek();
    }
    private static boolean CheckInWaitingList( String NameAndItemNumber) {
        boolean Found = false;
        for( String NAIN : WaitingList_StudentORTeacherNAME_And_ItemNumber ) {
            if(NAIN.equals(NameAndItemNumber)) {
                Found = true;
            }
        }
        return Found;
    }
    private static String ReturnTheDateFromSpecificElementInWaitingList(String FormatWithoutDate) {
        
        String [] FormatParts = FormatWithoutDate.split(":");
        String [] NameParts = FormatParts[0].split("-");
        
        
        String FormatType = NameParts[0];
        String FormatName = NameParts[1];
        String FormatItemNumber = FormatParts[1];
        
        String Date = "Item Not Found";
        
        for (String Item : WaitingList_StudentORTeacherNAME_And_ItemNumber) {
            
            String [] ActualItemParts = Item.split(":");
            String [] ActualNameParts = ActualItemParts[0].split("-");
            String ActualType = ActualNameParts[0];
            String ActualName = ActualNameParts[1];
            String ActualItemNumber = ActualItemParts[1];
            String ActualDate = ActualItemParts[2];
            
            if(ActualType.equals(FormatType) && ActualName.equals(FormatName) && ActualItemNumber.equals(FormatItemNumber)) {
                Date = ActualDate;
            }
            
        }
        return Date;
    }
    
    // ---------------------------------------------------------     ReservedList Functions
    private static void AddToReservedList(String NameAndItemNumber) {
        
        ReservedList_StudentORTeacherNAME_And_ItemNumber.add(NameAndItemNumber + ":" + GetDateNow());
    }
    
    private static void RemoveFromReservedList(String NameAndItemNumber) {
        ReservedList_StudentORTeacherNAME_And_ItemNumber.remove(NameAndItemNumber);
    }
    
    private static String GetFirstOneInReservedList() {
        return ReservedList_StudentORTeacherNAME_And_ItemNumber.peek();
    }
    private static String ReturnTheDateFromSpecificElementInReservedList(String FormatWithoutDate) {
        
        String [] FormatParts = FormatWithoutDate.split(":");
        String [] NameParts = FormatParts[0].split("-");
        
        
        String FormatType = NameParts[0];
        String FormatName = NameParts[1];
        String FormatItemNumber = FormatParts[1];
        
        String Date = "Item Not Found";
        
        for (String Item : ReservedList_StudentORTeacherNAME_And_ItemNumber) {
            
            String [] ActualItemParts = Item.split(":");
            String [] ActualNameParts = ActualItemParts[0].split("-");
            String ActualType = ActualNameParts[0];
            String ActualName = ActualNameParts[1];
            String ActualItemNumber = ActualItemParts[1];
            String ActualDate = ActualItemParts[2];
            
            if(ActualType.equals(FormatType) && ActualName.equals(FormatName) && ActualItemNumber.equals(FormatItemNumber)) {
                Date = ActualDate;
            }
            
        }
        return Date;
    }
    
    
    
    // ---------------------------------------------------------     Librarian Functions
    static void GetNewItemFromLibrarian(String NewItem) {
        
       
        String NewItemFromLibrarian = NewItem;
        // Split the Message 
        String [] NewItemParts = NewItemFromLibrarian.split("/");
        int Number = Integer.parseInt(NewItemParts[0]);
        String Category = NewItemParts[1];
        boolean Availability = false;
        if(NewItemParts[2] == "true") {
            Availability = true;
        } else if(NewItemParts[2] == "false") {
            Availability = false;
        }
        String ExpectedReturnDate = NewItemParts[3];
        
        // here add new item from Librarian to the Server
        SERVER_AddNewItem(Number,Category,Availability,ExpectedReturnDate);
        String DESC = "Insert New Item To List Of Items ";
        SERVER_AddNewTransaction("Librarian" , GetDateNow() , GetTimeNow() ,DESC, "none");
    }
    
    static void GetRemoveItemFromLibrarian(String NewItem) {
        
        boolean Found = false;
        // loop for all list to find item number and make values removed which is removed from list
        for (int index : List_of_Items.keySet()) {
            if(List_of_Items.get(index).get("Number").equals(NewItem)) {
                Found = true;
                List_of_Items.get(index).put("Number","removed");
                List_of_Items.get(index).put("Category","removed");
                List_of_Items.get(index).put("Availability","removed");
                List_of_Items.get(index).put("ExpectedReturnDate","removed");
            }
        }
        if(Found) {
            String DESC = "Remove Item Number " + NewItem + " From List Of Items ";
            SERVER_AddNewTransaction("Librarian" , GetDateNow() , GetTimeNow() ,DESC, "none");
        } else {
            System.out.println("The Item Number " + NewItem + " Not Found To Remove It.");
        }
    }
    
    // ---------------------------------------------------------     Clients Funtions
    public static String GetRequsetReserveItem(String RequstInfo) {
    
        String [] RequstInfoParts = RequstInfo.split(":");
        String [] NameParts = RequstInfoParts[0].split("-");
        
        // which is S or T to know the type of client becouse STUDENT maximum 3 ReserveItem and TEACHER maximum 6 ReserveItem
        String ClientType = NameParts[0];
        String ClientName = NameParts[1];
        String ItemNumber = RequstInfoParts[1];
        
        
        // this trick added after write the "Code Section 1" bellow
        /******************************************************************/
        /*  Explanation For This: the system sent to the first patient in */
        /*  the list when item is available but the person in waiting list*/
        /*  for this for this reason remove from waiting list if found    */
        /******************************************************************/
         String Date = ReturnTheDateFromSpecificElementInWaitingList(RequstInfo);
        /**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/
        /**/                                                            /**/
        /**/    // check is found in waiting list                       /**/        
        /**/    if(CheckInWaitingList(RequstInfo)) {                    /**/
        /**/        // if the person in waiting list remove it          /**/
        /**/        RemoveFromWaitingList(RequstInfo+":"+Date);         /**/
        /**/    }                                                       /**/
        /**/                                                            /**/
        /**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/
        
        
        // Code Section 1
        // fist check is item number is found in the list or not
        if(CheckItemNumberFound(ItemNumber)) {
            if(ItemIsTakenByOtherOne(ItemNumber)) {
                //System.out.println("Sorry!! Item Is Taken With Onther One.");
                return "Sorry!! Item Is Taken With Onther One.";
            } else { // Item found & not taken 
                int ReservedItemsTaken = HowManyReservedItemTaken(ClientType , ClientName , ItemNumber);
                if(ClientType.equals("S")) {
                    if(ReservedItemsTaken < 3) {
                        // insert Student
                        String NewStudentToReservedList = ClientType+"-"+ClientName+":"+ItemNumber;
                        if(CheckItemIsAvailableOrNot(ItemNumber)) { //Item found & not taken & available
                            // add to reserved list
                            AddToReservedList(NewStudentToReservedList);
                            String DESC = "Student " + ClientName + " Take The Item Number " + ItemNumber + " And Save To Reserved List ";
                            SERVER_AddNewTransaction("Student-" + ClientName  , GetDateNow() , GetTimeNow() ,DESC, "none");
                            // when Item is taken now change the availability for the item
                            ChangeToggleTheAvailability(ItemNumber);
                        } else { //Item found & not taken & not available
                            // add to waiting list
                            AddToWaitingList(NewStudentToReservedList);
                            String DESC = "Student " + ClientName + " Added To The Waiting List Becouse The Item Number " + ItemNumber + " Is Not Available ";
                            SERVER_AddNewTransaction("Student-" + ClientName  , GetDateNow() , GetTimeNow() ,DESC, "none");
                        }
                    } else {
                        //System.out.println("Sorry!! Student Limit Reserved Items Is Maximum 3.");
                        return "Sorry!! Student Limit Reserved Items Is Maximum 3.";
                    }
                } else if(ClientType.equals("T")) {
                    if(ReservedItemsTaken < 6) {
                        // insert Teacher
                        String NewTeacherToReservedList = ClientType+"-"+ClientName+":"+ItemNumber;
                        if(CheckItemIsAvailableOrNot(ItemNumber)) { //Item found & not taken & available
                            // add to reserved list
                            AddToReservedList(NewTeacherToReservedList);
                            String DESC = "Teacher " + ClientName + " Take The Item Number " + ItemNumber + " And Save To Reserved List ";
                            SERVER_AddNewTransaction("Teacher-" + ClientName  , GetDateNow() , GetTimeNow() ,DESC, "none");
                            // when Item is taken now change the availability for the item
                            ChangeToggleTheAvailability(ItemNumber);
                        } else { //Item found & not taken & not available
                            // add to waiting list
                            AddToWaitingList(NewTeacherToReservedList);
                            String DESC = "Teacher " + ClientName + " Added To The Waiting List Becouse The Item Number " + ItemNumber + " Is Not Available ";
                            SERVER_AddNewTransaction("Teacher-" + ClientName  , GetDateNow() , GetTimeNow() ,DESC, "none");
                        }
                    } else {
                        System.out.println("Sorry!! Teacher Limit Reserved Items Is Maximum 6.");
                        return "Sorry!! Teacher Limit Reserved Items Is Maximum 6.";
                    }
                }
            }
        } else {
            System.out.println("Sorry!! Item Not Found.");
            return "Sorry!! Item Not Found.";
        }
        return "Item Reserved Successfully.. ";
    }
    public static String GetRequsetReturnItem(String RequstInfo) {
    
        String [] RequstInfoParts = RequstInfo.split(":");
        String [] NameParts = RequstInfoParts[0].split("-");
        
        // which is S or T to know the type of client becouse STUDENT maximum 3 ReserveItem and TEACHER maximum 6 ReserveItem
        String ClientType = NameParts[0];
        String ClientName = NameParts[1];
        String ItemNumber = RequstInfoParts[1];
        
        String [] DateNowParts = GetDateNow().split("/");
        int DAY_NOW = Integer.parseInt(DateNowParts[0]);
        int MONTH_NOW = Integer.parseInt(DateNowParts[1]);
        
        
        // Code Section 1
        // fist check is item number is found in the list or not
        if(CheckItemNumberFound(ItemNumber)) {
            if(ItemIsTakenByOtherOne(ItemNumber)) { // yes
                for (String Item : ReservedList_StudentORTeacherNAME_And_ItemNumber) {
            
                    String [] ItemParts = Item.split(":");
                    String [] TypeAndName = ItemParts[0].split("-");
                    String Type = TypeAndName[0];
                    String Name = TypeAndName[1];
                    String Item_Number = ItemParts[1];
                    
                    String [] DateParts = ItemParts[2].split("/");
                    int DAY_ITEM = Integer.parseInt(DateParts[0]);
                    int MONTH_ITEM = Integer.parseInt(DateParts[1]);
                    if(Item_Number.equals(ItemNumber)) { // yes return and send message and remove from list to any one re-register it
                        if(DAY_ITEM+5 != DAY_NOW) { // 5 days stell returned 
                            String Date = ReturnTheDateFromSpecificElementInReservedList(RequstInfo);
                            RemoveFromReservedList(RequstInfo+":"+Date);
                            // now the person not return item less than 5 days
                            return Type + "#Hi Thank You For Return Item Number " + ItemNumber;
                        } else if(DAY_ITEM+5+2 != DAY_NOW) { // 7 days stell returned 
                            String Date = ReturnTheDateFromSpecificElementInReservedList(RequstInfo);
                            RemoveFromReservedList(RequstInfo+":"+Date);
                            // now the person not return item less than 7 days
                            return Type + "#Hi Thank You For Return Item Number " + ItemNumber + " And The Price Of Item Will Be Triple ";
                        }
                    } else {
                        System.out.println("Sorry!! The Number From Client And Number in Reserved List Not Maching.");
                    }
                }
                
            } else {
                System.out.println("Sorry!! Item Not Registered.");
            }
        } else {
            System.out.println("Sorry!! Item Not Found.");
        }
            
        return ClientType + "#Not Available";
    }
    public static String CheckTheFirstItemInWaitingListIsAvailable() {
        
        // get the first client in the queue waiting list by peek() method
        String FirstItemInWaitingList = GetFirstOneInWaitingList();
        String [] FirstItemParts = FirstItemInWaitingList.split(":");
        String [] NameParts = FirstItemParts[0].split("-");
        
        String FirstType = NameParts[0];
        String FirstName = NameParts[1];
        String ItemNumber = FirstItemParts[1];
        
        String FirstTypeName = "Teacher";
        if(FirstType.equals("S")) {
            FirstTypeName = "Student";
        }
        if(CheckItemIsAvailableOrNot(ItemNumber)) { // yes available
            String DESC = "Send To " + FirstTypeName + " " + FirstName + " Is The Item Number " + ItemNumber + " Is Available To Register It ";
            SERVER_AddNewTransaction("System", GetDateNow() , GetTimeNow() ,DESC, "SENDmessageONdate:" + GetDateNow()+":" + FirstTypeName+":"+FirstName+":"+ItemNumber);
            return FirstType + "#Hi The Item Number " + ItemNumber + " Is Available Right Now Reregister It Maximum within one day, otherwise you will Lost It.";
        }
        return FirstType + "#Not Available";
    }
    // Track The Send Messaging
    private static void TrackingInWatingList() {
        String [] DateNowParts = GetDateNow().split("/");
        int DAY_NOW = Integer.parseInt(DateNowParts[0]);
        int MONTH_NOW = Integer.parseInt(DateNowParts[1]);
        
        for (int index : List_of_All_Transaction_Library.keySet()) {
            if(!(List_of_All_Transaction_Library.get(index).get("Secret").equals("none"))) {
                String [] SecretParts = List_of_All_Transaction_Library.get(index).get("Secret").toString().split(":");
                String [] DateSecretParts = SecretParts[1].split("/");
                String Type = SecretParts[2];
                String Name = SecretParts[3];
                String ItemNumber = SecretParts[4];
                int DAY_SECRET = Integer.parseInt(DateSecretParts[0]);
                int MONTH_SECRET = Integer.parseInt(DateSecretParts[1]);
                if(MONTH_NOW == MONTH_SECRET) {
                    if(DAY_SECRET+1 == DAY_NOW) { 
                        // Currently a day has passed since sending the message with the available item
                        // check the person now in waiting list
                        String OneString = Type + "-" + Name + ":" + ItemNumber;
                        if(CheckInWaitingList(OneString)) { 
                            // if found remove from list and sent message secound one in the queue
                            String Date = ReturnTheDateFromSpecificElementInWaitingList(OneString);
                            RemoveFromWaitingList(OneString+":"+Date);
                            String MSG = CheckTheFirstItemInWaitingListIsAvailable();
                            System.out.println(MSG);
                            String DESC = "Tracking To Check a day passed since sending the message";
                            SERVER_AddNewTransaction("System", GetDateNow() , GetTimeNow() ,DESC, "none");
                        }
                    }
                }
            }
        }
    }
    // Tracking The Return Items
    private static String TrackingInResevedList() {
        String GlobalType = "";
        String [] DateNowParts = GetDateNow().split("/");
        int DAY_NOW = Integer.parseInt(DateNowParts[0]);
        int MONTH_NOW = Integer.parseInt(DateNowParts[1]);
        // item like "S-Mohammad:3:11/22/3333"
        for (String Item : ReservedList_StudentORTeacherNAME_And_ItemNumber) {
            
            String [] ItemParts = Item.split(":");
            String [] TypeAndName = ItemParts[0].split("-");
            String Type = TypeAndName[0];
            String Name = TypeAndName[1];
            String ItemNumber = ItemParts[1];
            String [] DateParts = ItemParts[2].split("/");
            int DAY_ITEM = Integer.parseInt(DateParts[0]);
            int MONTH_ITEM = Integer.parseInt(DateParts[1]);
                  
            GlobalType = Type;
            if(DAY_ITEM+5 == DAY_NOW) { // 5 days stell returned 
                // now the person not return item less than 5 days
                return Type + "#Hi You Must Return Item Number " + ItemNumber;
            } else if(DAY_ITEM+5+2 == DAY_NOW) { // 7 days stell returned 
                // now the person not return item less than 7 days
                return Type + "#Hi If You Not Return The Item Number " + ItemNumber + " In 2 Days The Price Will Be Triple ";
            }
               
        }
        return GlobalType + "#Not Available";
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         System.out.println("------ SERVER ------");
        
        serversocket1 = new ServerSocket(4040);
        
        Thread T1 = new Thread(new Server());
        T1.start();
        
        Thread T2 = new Thread(new Server());
        T2.start();
        
        Thread T3 = new Thread(new Server());
        T3.start();
        
        
       
         
         
        
        // 1- Run Librariam To Add New Item To The System
         
      
        
       
        
    }

    @Override
    public void run() {
       
       while(true) {
        
            try {
                // Get Key
                DataInputStream GetKey = new DataInputStream(Client.getInputStream());
                String KEY_FOFRMAT = GetKey.readUTF();
                String [] KEY_FOFRMAT_PARTS = KEY_FOFRMAT.split("-");
                String FROM = KEY_FOFRMAT_PARTS[0];
                int KEY = Integer.parseInt(KEY_FOFRMAT_PARTS[1]);
                
                
                switch(FROM) {
                    // ---------------- From Librarian
                    case "L": {
                        switch(KEY) {
                            case 1: {
                                
                                    //DataInputStream to GetNewItem From Librarian
                                    DataInputStream GetNewItem = new DataInputStream(Client.getInputStream());
                                    String NewItem = GetNewItem.readUTF();
                                    String [] ItemParts = NewItem.split(",");
                                    int ItemNumber = Integer.parseInt(ItemParts[0]);
                                    String ItemCategory = ItemParts[1];
                                    boolean ItemAvailability;
                                    if(ItemParts[2].equals("true")) {
                                        ItemAvailability = true;
                                    } else {
                                        ItemAvailability = false;
                                    }
                                    String ExpectedReturnDate = ItemParts[3];
                                    // insert new item
                                    SERVER_AddNewItem(ItemNumber,ItemCategory,ItemAvailability,ExpectedReturnDate);
                                    System.out.println("List ##  " + List_of_Items);
                                    break;
                                
                            }
                            case 2: {
                                //DataInputStream to GetRemoveItem From Librarian
                                DataInputStream GetItemNumber = new DataInputStream(Client.getInputStream());
                                String ItemNumber = GetItemNumber.readUTF();
                                
                                GetRemoveItemFromLibrarian(ItemNumber);
                                System.out.println("List ##  " + List_of_Items);
                                break;
                            }
                            case 3: {
                                
                                DataOutputStream SendAllItems = new DataOutputStream(Client.getOutputStream());
                                SendAllItems.writeUTF(AllItems());
                                
                                break;
                            }
                            case 4: {
                                
                                DataInputStream GetTeacherName = new DataInputStream(Client.getInputStream());
                                String TeacherName = GetTeacherName.readUTF();
                                
                                DataOutputStream SendTeacherList = new DataOutputStream(Client.getOutputStream());
                                SendTeacherList.writeUTF(ItemsSpicficStudent(TeacherName));
                                
                                break;
                            }
                            case 5: {
                                
                                DataInputStream GetStudentName = new DataInputStream(Client.getInputStream());
                                String StudentName = GetStudentName.readUTF();
                                
                                DataOutputStream SendStudentList = new DataOutputStream(Client.getOutputStream());
                                SendStudentList.writeUTF(ItemsSpicficStudent(StudentName));
                                
                                break;
                            }
                            case 6: {
                                
                                DataOutputStream SendAllTranactionHistory = new DataOutputStream(Client.getOutputStream());
                                SendAllTranactionHistory.writeUTF(AllITranactionHistory());
                                
                                break;
                            }
                            case 7: {
                                DataInputStream GetItemNumber = new DataInputStream(Client.getInputStream());
                                String ItemNumber = GetItemNumber.readUTF();
                                
                                ChangeToggleTheAvailability(ItemNumber);
                                
                                break;
                            }
                            case 8: {
                                
                                DataOutputStream SendAllWaitingList = new DataOutputStream(Client.getOutputStream());
                                SendAllWaitingList.writeUTF(GetAllWaitingList());
                                
                                break;
                            }
                            case 9: {
                                
                                DataOutputStream SendAllReservedList = new DataOutputStream(Client.getOutputStream());
                                SendAllReservedList.writeUTF(GetAllReservedList());
                                
                                break;
                            }
                        }
                        break;
                    }
                    // ---------------- From Teacher
                    case "T": {
                        switch(KEY) {
                            case 1: {
                                System.out.println("hi");
                                //DataInputStream to GetNewRequsetReserveItem From Clients (Student OR Teacher)
                                DataInputStream GetNewReserveItem = new DataInputStream(Client.getInputStream());
                                String NewReserveItem = GetNewReserveItem.readUTF();
                                
                                String ReturnMessage = GetRequsetReserveItem(NewReserveItem);
                                
                                DataOutputStream SendReturnMessage = new DataOutputStream(Client.getOutputStream());
                                SendReturnMessage.writeUTF(ReturnMessage);
                                break;
                            }
                            case 2: {
                                
                                DataInputStream GetReturnItem = new DataInputStream(Client.getInputStream());
                                String ReturnItem = GetReturnItem.readUTF();
                                
                                String ReturnMessage = GetRequsetReserveItem(ReturnItem);
                                
                                DataOutputStream SendReturnMessage = new DataOutputStream(Client.getOutputStream());
                                SendReturnMessage.writeUTF(ReturnMessage);
                                
                                break;
                            }
                            case 3: {
                                
                                DataInputStream GetTeacherName = new DataInputStream(Client.getInputStream());
                                String TeacherName = GetTeacherName.readUTF();
                                
                                DataOutputStream SendTeacherList = new DataOutputStream(Client.getOutputStream());
                                SendTeacherList.writeUTF(ItemsSpicficTeacher(TeacherName));
                                
                                break;
                            }
                        }
                        
                        
                        
                        break;
                    }
                    // ---------------- From Student
                    case "S": {
                        switch(KEY) {
                            case 1: {
                                System.out.println("hi");
                                //DataInputStream to GetNewRequsetReserveItem From Clients (Student OR Teacher)
                                DataInputStream GetNewReserveItem = new DataInputStream(Client.getInputStream());
                                String NewReserveItem = GetNewReserveItem.readUTF();
                                System.out.println(NewReserveItem);
                                String ReturnMessage = GetRequsetReserveItem(NewReserveItem);
                                System.out.println(ReturnMessage);
                                DataOutputStream SendReturnMessage = new DataOutputStream(Client.getOutputStream());
                                SendReturnMessage.writeUTF(ReturnMessage);
                                break;
                            }
                            case 2: {
                                
                                DataInputStream GetReturnItem = new DataInputStream(Client.getInputStream());
                                String ReturnItem = GetReturnItem.readUTF();
                                
                                String ReturnMessage = GetRequsetReturnItem(ReturnItem);
                                
                                DataOutputStream SendReturnMessage = new DataOutputStream(Client.getOutputStream());
                                SendReturnMessage.writeUTF(ReturnMessage);
                                
                                break;
                            }
                            case 3: {
                                
                                DataInputStream GetStudentName = new DataInputStream(Client.getInputStream());
                                String StudentName = GetStudentName.readUTF();
                                
                                DataOutputStream SendStudentList = new DataOutputStream(Client.getOutputStream());
                                SendStudentList.writeUTF(ItemsSpicficStudent(StudentName));
                                
                                break;
                            }
                        }
                        
                       
                        break;
                    }
                    
                }   
                /* Send Message From System To Student */
                // Check The Item With The Fist Client In Waiting List Is Available And Send The Message To Client
                if(WaitingList_StudentORTeacherNAME_And_ItemNumber.isEmpty()) {
                    DataOutputStream SendAvailableMessage = new DataOutputStream(Client.getOutputStream());
                    SendAvailableMessage.writeUTF("Nothing..");
                } else {
                    String TYPE_1 = CheckTheFirstItemInWaitingListIsAvailable();
                    System.out.println("Type: "+TYPE_1);
                    String [] PARTS_1 = TYPE_1.split("#");
                    if(PARTS_1[0].equals("S")) {
                        if(!(PARTS_1[1].equals("Not Available"))) {

                        //DataOutputStream to send a message to Student from waiting list is the item available
                        DataOutputStream SendAvailableMessage = new DataOutputStream(Client.getOutputStream());
                        SendAvailableMessage.writeUTF(PARTS_1[1]);

                        } else {
                            DataOutputStream SendAvailableMessage = new DataOutputStream(Client.getOutputStream());
                            SendAvailableMessage.writeUTF("Nothing..");
                        }
                    } else if(PARTS_1[0].equals("T")){
                        if(!(PARTS_1[1].equals("Not Available"))) {

                        //DataOutputStream to send a message to Student from waiting list is the item available
                        DataOutputStream SendAvailableMessage = new DataOutputStream(Client.getOutputStream());
                        SendAvailableMessage.writeUTF(PARTS_1[1]);

                        } else {
                            DataOutputStream SendAvailableMessage = new DataOutputStream(Client.getOutputStream());
                            SendAvailableMessage.writeUTF("Nothing..");
                        }
                    } else { // for librarian
                        DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                        SendLateReturnMessage.writeUTF("Not Allowed For You");
                    }
                }
                
                
                // Check To Send Message To Client For The Late Return Item
                if(ReservedList_StudentORTeacherNAME_And_ItemNumber.isEmpty()) {
                    DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                    SendLateReturnMessage.writeUTF("Nothing..");
                } else {
                    String TYPE_2 = TrackingInResevedList();
                    String [] PARTS_2 = TYPE_2.split("#");
                    if(PARTS_2[0].equals("S")) {
                        if(!(PARTS_2[1].equals("Not Available"))) {
                            //DataOutputStream to send a message to student from reserved list to days for retutn item
                            DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                            SendLateReturnMessage.writeUTF(PARTS_2[1]);
                        } else {
                            DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                            SendLateReturnMessage.writeUTF("Nothing..");
                        }
                    } else if(PARTS_2[0].equals("T")) {
                        if(!(PARTS_2[1].equals("Not Available"))) {
                            //DataOutputStream to send a message to teacher from reserved list to days for retutn item
                            DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                            SendLateReturnMessage.writeUTF(PARTS_2[1]);
                        } else {
                            DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                            SendLateReturnMessage.writeUTF("Nothing..");
                        }
                    } else { //  for librarian
                        
                         DataOutputStream SendLateReturnMessage = new DataOutputStream(Client.getOutputStream());
                         SendLateReturnMessage.writeUTF("Not Allowed For You");
                    }
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       }
    }
    
}
