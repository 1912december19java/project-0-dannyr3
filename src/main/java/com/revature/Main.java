package com.revature;

import java.util.Scanner;

import com.revature.controller.Controller;
import com.revature.model.Transaction;
import com.revature.model.User;


/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) {
		
		Controller control = new Controller();
		
		// Create a Scanner object
	    Scanner myObj = new Scanner(System.in);
	    
	    //does the user wants to login or register?
	    System.out.println("Are you trying to \"login\" or \"register\" for a new bank account?");
	    String ans = myObj.nextLine();
	    
	    //user types login
	    if (ans.equalsIgnoreCase("login")) {
	    	
	    	//user needs to enter Username
	    	System.out.println("Please Enter Your Bank UserName");
	    	String usernameAns = myObj.nextLine();
	    	
	    	//validate Username
	    	if (usernameAns.equals("dannyr3")) {
	    		
	    		//prompt user to type in password if username is true
	    		System.out.println("Please Enter your Account Password");
	    		String passwordAns = myObj.nextLine();
	    		
	    		//Validate Password
	    		if (passwordAns.equals("dannyr3")) {
	    			
	    			//take us to see account information (view balance, withdraw, deposit (view transactions, transfer money to another account)
	    			
	    			
	    		} else {
	    			
	    			//wrong password typed in
	    			System.out.println("The password you typed is incorrect");
	    		}
	    		
	    	} else {
	    		System.out.println("You typed in the wrong username");
	    	}
	    	
	    	//registration process
	    } else if(ans.equalsIgnoreCase("register")) {
	    	
	    	
	    	System.out.println("Please Create A Unique UserName");
	    	String usernameReg = myObj.nextLine();
	    	
	    	//validate username is unique
	    	if (!usernameReg.equals("dannyr3")) {
	    		
	    		System.out.println("Please Create A Unique Password");
		    	String passwordReg = myObj.nextLine();
		    	System.out.println("Please Enter Your First Name");
		    	String firstNameReg = myObj.nextLine();
		    	System.out.println("Please Enter Your Last Name");
		    	String lastNameReg = myObj.nextLine();
		    	
		    	User a = new User(firstNameReg, lastNameReg, usernameReg, passwordReg);
		    	
		    	//what account do you want to create Checking or Savings?
		    	System.out.println("Please Enter Your Last Name");
		    	String accTypeReg = myObj.nextLine();
		    	if (accTypeReg == "Checkings") {
		    		System.out.println("Congrats You have created a Checkings Account");
		    		Transaction tranCheck = new Transaction();
		    	} else if (accTypeReg == "Savings") {
		    		System.out.println("Congrats You have created a Savings Account");
		    	} else {
		    		System.out.println("You typed Savings or Checkings wrong");
		    	}
		    	
		    	//a.userId, Create Account based on account type and userId default balance 0
	    		
	    	} else {
	    		System.out.println("You already entered an existing username");
	    	}
	    	
	    } else {
	    	System.out.println("You misspelled login or register");
	    }
	}
}
