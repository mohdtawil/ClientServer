/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Server implements Runnable {

    static ServerSocket serversocket;
    static  Socket Voter;
    static int Candidate_1_Counter = 0;
    static   int Candidate_2_Counter = 0;
    static  int Candidate_3_Counter = 0;
    static  int Counter = 0;
    
    public Server() throws IOException {
      Voter = serversocket.accept();
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("------ Server ------");
        // TODO code application logic here
        serversocket = new ServerSocket(4040); 
        
        Thread T=new Thread(new Server());
        T.start();
        
        
        
        
        
        
        
    }
    @Override
    public void run() {
        while(Counter<=100) {
            try {
            DataInputStream GetVote = new DataInputStream(Voter.getInputStream());
            int VoteTo = GetVote.readInt();
            
            switch(VoteTo) {
                case 1: {
                    Candidate_1_Counter++;
                    break;
                }
                case 2: {
                    Candidate_2_Counter++;
                    break;
                }
                case 3: {
                    Candidate_3_Counter++;
                    break;
                }
            }
            Counter++;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        System.out.println("Candidate       Number of Votes");
            System.out.println("1               " + Candidate_1_Counter);
            System.out.println("2               " + Candidate_2_Counter);
            System.out.println("3               " + Candidate_3_Counter);
    }
}
