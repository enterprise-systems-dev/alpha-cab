/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//to add
// - some sexy print function to print nice formatted ascii tables cos fuck writing that kind of function out AGAIN

// just some shit I found myself writing over and over again, still kinda in its infancy, but the pause function is DELIGHTFUL, SIMPLY DELICIOUS
public class MyShit {
    
    private Scanner in = new Scanner(System.in);
    
    public MyShit(){
        
    }
    
    //file IO
    public void generateValueFile(String fileName){
        try {
            File file = new File(fileName);
            
            //create file if it doesn't already exist
            if (file.exists() == false){
                file.createNewFile();
            }
            //clear file if exists
            else{
                new PrintWriter(file).close();
            }
        }
        catch(IOException e){
            System.out.println("Error creating file.");
        }
        
    }
    
    //input handling functoins
    public int intInputHandler(int upperBound){
        int userInput = 0;
        
        boolean inputInvalid = true;
        while (inputInvalid == true){
            userInput = 0;
            try{
                userInput = in.nextInt();
                if (userInput > 0 && userInput < upperBound + 1)
                    inputInvalid = false;
                else
                    System.out.println("Input invalid - out of range");
            }
            catch(Exception e){
                System.out.println("Input invalid - not a number");
                in.nextLine();
            }
        }
        
        return userInput;
    } //1 to upper
    
    public int intInputHandler(int lowerBound, int upperBound){
        int userInput = 0;
        
        boolean inputInvalid = true;
        while (inputInvalid == true){
            userInput = 0;
            try{
                userInput = in.nextInt();
                if (userInput >= lowerBound && userInput < upperBound + 1)
                    inputInvalid = false;
                else
                    System.out.println("Input invalid - out of range");
            }
            catch(Exception e){
                System.out.println("Input invalid - not a number");
                in.nextLine();
            }
        }
        
        return userInput;
    } //lower to upper
    
    //debug functions
    public void pause(){
        
        //print "pause" to console
        System.out.println("Pause");
        
        //wait for any input
        try{
            System.in.read();
        }
        catch(Exception e){}
    }
    
    public void pause(String statement){
        //print statement
        System.out.println(statement);

        //print "pause" to console
        System.out.println("Pause");
        
        //wait for any input
        try{
            System.in.read();
        }
        catch(Exception e){}
    }
    
    public void out(String print){
        System.out.print(print);
    }
    
    //VERY LAZY - PURE SLOTH
    public void p(){
        pause();
    }
    
    public void p(String statement){
        pause(statement);
    }
    
    public void o(String print){
        out(print);
    }
}
