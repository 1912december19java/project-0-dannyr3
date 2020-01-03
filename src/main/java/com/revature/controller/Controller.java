package com.revature.controller;

import java.util.Scanner;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.Servicer;

public class Controller {

  Servicer services = new Servicer();
  public Scanner myOb = new Scanner(System.in);

  public Controller() {
    super();

    // services.loginOrRegister();

    System.out.println(
        "Are you trying to \"login\" or \"register\" for a new bank account?    ||  type \"X\" to Exit Program");
    String ans = myOb.nextLine();

    if (!ans.equals("X")) {
      if (ans.equalsIgnoreCase("login")) {
        services.account = null;
        services.user = null;

        String ans2;
        String ans3;

        do {
          System.out.println("What is your username?");
          ans2 = myOb.nextLine();
          System.out.println("What is your password?");
          ans3 = myOb.nextLine();

          if (services.loginValidate2(ans2, ans3) == false) {
            System.out.println("You have failed to login");
          }

        } while (!services.loginValidate2(ans2, ans3));
        System.out.println("You succesfully logged in");

        String ans4;
        do {

          System.out.println("type \"Logout\" to Logout ||  type \"X\" to Exit Program");
          services.loggedInMenu2(services.user);
          //System.out.println("Your balance is: " + "$" + String.format("%.2f", services.account.getBalance()));
          System.out.println("Do you want to make a \"Withdraw\" or \"Deposit\"");
          ans4 = myOb.nextLine();
          
          if (ans4.toLowerCase().equalsIgnoreCase("Withdraw")) {
            
            System.out.println("How much do you want to Withdraw?");
            String ans5 = myOb.nextLine();
            if (!ans5.isEmpty()) {
            services.withdraw2(services.account, ans5);
            }
            
          } else if (ans4.toLowerCase().equalsIgnoreCase("Deposit")) {
            
            System.out.println("How much do you want to Deposit?");
            String ans6 = myOb.nextLine();
            if (!ans6.isEmpty()) {
            services.deposit2(services.account, ans6);
            }
            
          } else {
            
          }
        } while (!ans4.equals("X") && !ans4.equals("Logout"));

        if (ans4.equals("X")) {
          System.exit(0);
        } else {
          
          new Controller();
        }

      } else if (ans.equalsIgnoreCase("register")) {


        String reg;
        String userName;
        do {
          System.out.println("Please Create A Unique UserName");
          reg = myOb.nextLine();
          userName = services.register1(reg);
        } while (userName == null);

        System.out.println("Please Create A Unique Password");
        String passwordReg = myOb.nextLine();
        System.out.println("Please Enter Your First Name");
        String firstNameReg = myOb.nextLine();
        System.out.println("Please Enter Your Last Name");
        String lastNameReg = myOb.nextLine();

        services.register2(userName, passwordReg, firstNameReg, lastNameReg);

        String accTypeReg;
        do {
          System.out.println("Do you want to create a \"Checkings\" or \"Savings\" account");
          accTypeReg = myOb.nextLine();
        } while (!accTypeReg.equalsIgnoreCase("Checkings")
            && !accTypeReg.equalsIgnoreCase("Savings"));

        if (services.accountRegistration2(accTypeReg, services.user)) {
          System.out.println("You have successfully created an Account");
          services.account = null;
          services.user = null;
          new Controller();
        } else {
          System.out.println("You were unsuccessful creating an Account");
          new Controller();
        }


      } else {
        new Controller();
      }
    } else {
      System.exit(0);
    }
  }

}

