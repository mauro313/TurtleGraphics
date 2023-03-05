/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package turtlegraphics;

import java.util.HashMap;
import java.util.Scanner;
        

public class TurtleGraphics {
    static int [][] board = new int [20][20];
    static int positionx = 0;
    static int positiony = 0;
    static int direction = 0; //0 = right,1 = down, 2 = left, 3 = up
    static int sizeOfStep = 2;
    static boolean penDown = false;
    
    public static void main(String[] args) throws Exception{    
      Scanner entry = new Scanner(System.in);
      String command = null;
      System.out.printf("Introduce a command(1-7):\n"
              + "1-PenDown\n"
              + "2-PenUp\n"
              + "3-turnLeft\n"
              + "4-turnRight\n"
              + "5-moveAlong\n"
              + "6-print\n"
              + "7-dataEnd\n");
      
      do{
          System.out.printf("Your command: ");
          int number = entry.nextInt();
          if(number>=1 && number<=7){
            if(number == 5){
              System.out.printf("\nIntroduce the size of step(0-19):");  
              int size = entry.nextInt();
                sizeOfStep = size;
                System.out.println();
            }  
            command = commandSelection(number);
            if("Failure".equals(command)){
              System.out.println("\nImpossible action\n");
            }
          }
          else{
            System.out.println();
            System.out.println("invalid command");    
            command = "Invalid Command";
          }
      }while(!"Finalization".equals(command));
    }
    
    /**
     * static method to select a command based in an index.
     * @param number
     * @return
     * @throws Exception 
     */
    public static String commandSelection(int number) throws Exception{
      String[] commands = {"penDown","penUp","turnLeft","turnRight","moveAlong","print","dataEnd"};
      return TurtleGraphics.class.getMethod(commands[number-1]).invoke(null).toString();
    }
    
    /**
     * change the status of the pen (penDown = true)
     * @return 
     */
    public static String penDown(){
      penDown = true;
      return "Success";
    }
    
    /**
     * change the status of the pen (penDown = false)
     * @return 
     */
    public static String penUp(){
      penDown = false; 
      return "Success";
    }
    
    /**
     * the turtle turn to the right.
     * @return 
     */
    public static String turnRight(){
      String returned = null;
      if((positiony==0 && direction==1) || (positionx==0 && direction == 2) || 
      (positionx==19 && direction== 0) || (positiony== 19 && direction== 3) ){
        returned = "Failure";
      }  
      else{
        HashMap<Integer,Integer> rightCommands =new HashMap<>();
        rightCommands.put(0, 1);
        rightCommands.put(1, 2);
        rightCommands.put(2, 3); 
        rightCommands.put(3, 0);  

        direction = rightCommands.get(direction);
        returned = "Success";
      }
      return returned;
    }
    
    /**
     * the turtle turn to the left.
     * @return 
     */
    public static String turnLeft(){
      String returned = null;
      if((positionx==0 && direction==0) || (positiony==0 && direction == 3) || 
      (positionx==19 && direction== 2) || (positiony== 19 && direction== 1) ){
        returned = "Failure";
      }  
      else{
        HashMap<Integer,Integer> leftCommands =new HashMap<>();
        leftCommands.put(0, 3);
        leftCommands.put(1, 0);
        leftCommands.put(2, 1); 
        leftCommands.put(3, 2);
         
        direction = leftCommands.get(direction);
        returned = "Success"; 
      }  
      return returned; 
    }
    
    /**
     * print the board.
     * @return 
     */
    public static String print(){
      for(int i=0;i<20;i++){
        for(int j=0;j<20;j++){
          if(board[i][j]==0){
            System.out.printf("  ");        
          }
          else{
            if(board[i][j]==1){
              System.out.printf("* ");    
            }  
          }
          if(j==20-1){
            System.out.println();    
          }
        }    
      } 
      return "Success";  
    }
    
    /**
     * set the finalization of the turtlegraphics.
     * @return 
     */
    public static String dataEnd(){
      return "Finalization"; 
    }
    
    /**
     * method that allows the turtle moves depend on the current direction.
     * @return
     * @throws Exception 
     */
    public static String  moveAlong() throws Exception{
      String[] movements ={"moveRight","moveDown","moveLeft","moveUp"};
      return TurtleGraphics.class.getMethod(movements[direction]).invoke(null).toString();
    }
    
    /**
     * the turtle moves to the right.
     * @return 
     */
    public static String moveRight(){
      String returned = null;
      if(sizeOfStep <= 19-positiony){
        if(penDown){
          for(int i=positiony;i<positiony+sizeOfStep;i++){
            board[positionx][i] = 1;              
          }    
        }   
        positiony = positiony + sizeOfStep;
        returned = "Success";
      }   
      else{
        returned = "Failure";     
      }  
      return returned;
    }
    
    /**
     * the turtle moves down
     * @return 
     */
    public static String moveDown(){
      String returned = null;
      if(sizeOfStep <= 19-positionx){
        if(penDown){
          for(int i=positionx;i<positionx + sizeOfStep;i++){
            board[i][positiony] = 1;              
          }  
        }   
        positionx  = positionx + sizeOfStep;
        returned = "Success";
      }   
      else{
        returned = "Failure";     
      }  
      return returned;
    }
    
    /**
     * the turtle moves to the left.
     * @return 
     */
    public static String moveLeft(){
      String returned = null;
      if(sizeOfStep <= positiony){
        if(penDown){
          for(int i=positiony;i>positiony-sizeOfStep;i--){
            board[positionx][i] = 1;              
          }      
        }    
        positiony = positiony - sizeOfStep;
        returned = "Success";
      }   
      else{
        returned = "Failure";     
      }  
      return returned;
    }
    
    /**
     * the turtle moves up.
     * @return 
     */
    public static String moveUp(){
      String returned = null;
      if(sizeOfStep <= positionx){
        if(penDown){
          for(int i=positionx;i>positionx-sizeOfStep;i--){
            board[i][positiony] = 1;              
          }    
        }   
        positionx = positionx - sizeOfStep;
        returned = "Success";
      }   
      else{
        returned = "Failure";     
      } 
      return returned;
    } 
}// end of TurtleGraphics class
