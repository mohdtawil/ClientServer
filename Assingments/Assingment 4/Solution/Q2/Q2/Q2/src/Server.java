
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.Math; 
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.InputMismatchException;
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
 //static String board="";
    /**
     * @param boardchar
     */
    public static void printBoard(String [][] boardchar) {
        String board = boardchar[0][0] + "  " + boardchar[0][1] + "  " + boardchar[0][2] + "\n" 
               + boardchar[1][0] + "  " + boardchar[1][1] + "  " + boardchar[1][2] + "\n"
               + boardchar[2][0] + "  " + boardchar[2][1] + "  " + boardchar[2][2] + "\n";
       System.out.println(board); 
    }
    public static String return_printBoard(String [][] boardchar) {
        String board = boardchar[0][0] + "  " + boardchar[0][1] + "  " + boardchar[0][2] + "\n" 
               + boardchar[1][0] + "  " + boardchar[1][1] + "  " + boardchar[1][2] + "\n"
               + boardchar[2][0] + "  " + boardchar[2][1] + "  " + boardchar[2][2] + "\n";
        return board;
    }
    public static String check_winner(String [][] boardchar) {
        
        if(boardchar[0][0].equals("X") && boardchar[0][1].equals("X") && boardchar[0][2].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[1][0].equals("X") && boardchar[1][1].equals("X") && boardchar[1][2].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[2][0].equals("X") && boardchar[2][1].equals("X") && boardchar[2][2].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[0][0].equals("X") && boardchar[1][0].equals("X") && boardchar[2][0].equals("X")){
            return "client 1 is won.";
        }  else if(boardchar[0][1].equals("X") && boardchar[1][1].equals("X") && boardchar[2][1].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[0][2].equals("X") && boardchar[1][2].equals("X") && boardchar[2][2].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[0][0].equals("X") && boardchar[1][1].equals("X") && boardchar[2][2].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[0][2].equals("X") && boardchar[1][1].equals("X") && boardchar[2][0].equals("X")){
            return "client 1 is won.";
        } else if(boardchar[0][0].equals("O") && boardchar[0][1].equals("O") && boardchar[0][2].equals("O")){
            return "client 2 is won.";
        } else if(boardchar[1][0].equals("O") && boardchar[1][1].equals("O") && boardchar[1][2].equals("O")){
            return "client 2 is won.";
        } else if(boardchar[2][0].equals("O") && boardchar[2][1].equals("O") && boardchar[2][2].equals("O")){
            return "client 2 is won.";
        } else if(boardchar[0][0].equals("O") && boardchar[1][0].equals("O") && boardchar[2][0].equals("O")){
            return "client 2 is won.";
        }  else if(boardchar[0][1].equals("O") && boardchar[1][1].equals("O") && boardchar[2][1].equals("O")){
            return "client 2 is won.";
        } else if(boardchar[0][2].equals("O") && boardchar[1][2].equals("O") && boardchar[2][2].equals("O")){
            return "client 2 is won.";
        } else if(boardchar[0][0].equals("O") && boardchar[1][1].equals("O") && boardchar[2][2].equals("O")){
            return "client 2 is won.";
        } else if(boardchar[0][2].equals("O") && boardchar[1][1].equals("O") && boardchar[2][0].equals("O")){
            return "client 2 is won.";
        } else {
            return "nth";
        }
    } 
    
    public static void main(String[] args) throws IOException {
        try (ServerSocket serversocket1 = new ServerSocket(4040); 
                Socket client1 = serversocket1.accept()) {
                Socket client2 = serversocket1.accept();
//        Socket client3 = serversocket1.accept();
            String [][] boardchar = new String[3][3]; 
 
 

 
        //Scanner in = new Scanner(System.in); 
        int x=1;
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               String num = new Integer(x).toString();
             boardchar[i][j] = num;
             x++;
           }
       }
 

boolean FLAG = true;
System.out.println("client 1 , 2 , 3  is connected");


while (FLAG) {
    System.out.println("*****");
    
    // send board to 1
    DataOutputStream send_board1 = new DataOutputStream(client1.getOutputStream());
    send_board1.writeUTF(return_printBoard(boardchar));
    
    // get board from 1
    DataInputStream get_board1 = new DataInputStream(client1.getInputStream());
    int newBoardNumber1 = get_board1.readInt();
    
    
    switch(newBoardNumber1) {
        case 1: {
            boardchar[0][0] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
               
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 2: {
            boardchar[0][1] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 3: {
             boardchar[0][2] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 4: {
             boardchar[1][0] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 5: {
             boardchar[1][1] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 6: {
             boardchar[1][2] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 7: {
             boardchar[2][0] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 8: {
             boardchar[2][1] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 9: {
             boardchar[2][2] = "X";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 1 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client1.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // send board to 2
    DataOutputStream send_board2 = new DataOutputStream(client2.getOutputStream());
    send_board2.writeUTF(return_printBoard(boardchar));
    
    // get board from 2
    DataInputStream get_board2 = new DataInputStream(client2.getInputStream());
    int newBoardNumber2 = get_board2.readInt();
   switch(newBoardNumber2) {
        case 1: {
            boardchar[0][0] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 2: {
            boardchar[0][1] = "O";
            printBoard(boardchar);
           if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 3: {
             boardchar[0][2] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 4: {
             boardchar[1][0] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 5: {
             boardchar[1][1] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 6: {
             boardchar[1][2] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 7: {
             boardchar[2][0] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 8: {
             boardchar[2][1] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
        case 9: {
             boardchar[2][2] = "O";
            printBoard(boardchar);
            if(check_winner(boardchar).equals("client 2 is won.")) {
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF("Congrats, You Won");
                
            } else if(check_winner(boardchar).equals("nth")){
                DataOutputStream send_board1_after = new DataOutputStream(client2.getOutputStream());
                send_board1_after.writeUTF(return_printBoard(boardchar));
            }
            break;
        }
    }
    
}

System.out.println("out of while");
 
       
        }
//        client2.close();
//       client3.close();
        }
        
}
