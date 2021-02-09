
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        System.out.println("------ SERVER ------");
        ServerSocket serversocket1 = new ServerSocket(3333); 
        Socket client1 = serversocket1.accept();
        Socket client2 = serversocket1.accept();
        Boolean x = true;
        while(x)
        {
             DataInputStream out_c1 = new DataInputStream(client1.getInputStream());
             String guess1 = out_c1.readUTF();
             
             DataInputStream out_c2 = new DataInputStream(client2.getInputStream());
             String guess2 = out_c2.readUTF();
             
             if(guess1.equals("scissors") && guess2.equals("scissors")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("the guess is the same continue");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("the guess is the same continue");
                x=true;
                continue;
             }
             if(guess1.equals("paper") && guess2.equals("paper")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("the guess is the same continue");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("the guess is the same continue");
                x=true;
                continue;
             }
             if(guess1.equals("stone") && guess2.equals("stone")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("the guess is the same continue");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("the guess is the same continue");
                x=true;
                continue;
             }
             
             if(guess1.equals("scissors") && guess2.equals("paper")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("you winner");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("you not winner");
                
                x=false;
                break;
             }
             
             if(guess1.equals("paper") && guess2.equals("scissors")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("you not winner");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("you winner");
                x=false;
                break;
             }
             
             if(guess1.equals("scissors") && guess2.equals("stone")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("you not winner");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("you winner");
                x=false;
                break;
             }
             
             if(guess1.equals("stone") && guess2.equals("scissors")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("you winner");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("you not winner");
                x=false;
                break;
             }
             
             if(guess1.equals("stone") && guess2.equals("paper")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("you not winner");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("you winner");
                x=false;
                break;
             }
             
             if(guess1.equals("paper") && guess2.equals("stone")){
                DataOutputStream in1 = new DataOutputStream(client1.getOutputStream());
                in1.writeUTF("you winner");
                
                DataOutputStream in2 = new DataOutputStream(client2.getOutputStream());
                in2.writeUTF("you not winner");
                x=false;
                break;
             }
             
        }
        client1.close();
        client2.close();
       
    }
    
}
