package com.revature.service;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.exception.AccountOverdraftException;
import com.revature.exception.AmountOutOfBoundsException;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repository.*;

public class Servicer {

  public Scanner myOb;
  public User user;
  public Account account;
  public UserDao users = new UserDaoPostgres();
  public AccountDao accountes = new AccountDaoPostgres();
  public TransactionDao transactiones = new TransactionDaoPostgres();
  public Transaction transact;
  private static Logger log = Logger.getLogger(Servicer.class);


  public Servicer() {
    super();
    myOb = new Scanner(System.in);
    user = null;
    account = null;
    transact = null;
  }

  public void loginOrRegister() {
    System.out.println(
        "Are you trying to \"login\" or \"register\" for a new bank account?    ||  type \"X\" to Exit Program");
    String ans = myOb.nextLine();

    if (!ans.equals("X")) {
      if (ans.equalsIgnoreCase("login")) {
        loginValidate();
      } else if (ans.equalsIgnoreCase("register")) {
        register();
      } else {
        System.out.println("You misspelled \"Login\" or \"Register\"");
        loginOrRegister();
      }
    } else {
      System.exit(0);
    }

  }

  public boolean loginValidate2(String username, String password) {

    for (User a : users.getUserByUsername(username)) {
      if (a.getUsername().equals(username)) {
        User b = a;
        user = a;

        if (b.getPassword().equals(password)) {
          List<Account> accounts = accountes.getAccountsByUserId(user.getId());
          account = accounts.get(0);
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    return false;
  }

  public void loginValidate() {
    System.out.println("What is your username?");
    String ans1 = myOb.nextLine();
    // UserDao users = new UserDaoPostgres();

    if (ans1.equals("X")) {
      System.exit(0);
    } else if (ans1.equals("Back")) {
      loginOrRegister();
    } else {

      for (User a : users.getAll()) {
        if (a.getUsername().equals(ans1)) {
          User b = a;
          user = a;
          System.out.println("What is your password?");
          String ans2 = myOb.nextLine();

          if (b.getPassword().equals(ans2)) {
            System.out.println("You succesfully logged in");
            loggedInMenu();
          } else {
            System.out.println("You entered the wrong password, try to log in again");
            loginValidate();
          }
        }
      }
      System.out.println("You entered a username that does not exist");
      loginValidate();
    }
  }

  public void loggedInMenu2(User user) {

    System.out.println("Your balance is: " + "$" + String.format("%.2f", account.getBalance()));
    //System.out.println("Do you want to make a \"Withdraw\" or \"Deposit\"");

  }

  public void loggedInMenu() {

    // AccountDao accountes = new AccountDaoPostgres();
    List<Account> accounts = accountes.getAccountsByUserId(user.getId());
    account = accounts.get(0);

    // let them choose an account?

    System.out
        .println("Your balance is: " + "$" + String.format("%.2f", accounts.get(0).getBalance()));
    System.out.println("Do you want to make a \"Withdraw\" or \"Deposit\"");
    String ans = myOb.nextLine();

    while (!ans.equals("X") && !ans.equals("Back")) {
      if (ans.toLowerCase().equalsIgnoreCase("Withdraw")) {
        withdraw();
      } else if (ans.toLowerCase().equalsIgnoreCase("Deposit")) {
        deposit();
      } else {
        System.out.println("You spelled Deposit or Withdraw wrong");
        loggedInMenu();
      }
    }
    if (ans.equals("X")) {
      System.exit(0);
      user = null;
      account = null;
    } else {
      loginOrRegister();
      user = null;
      account = null;
    }
  }


  public void withdraw2(Account account, String ans) {

    Double amount = Double.parseDouble(ans);
    try {
      account.withdraw(amount);
      transact = new Transaction(0, account.getId(), "Withdraw", amount);
      transactiones.save(transact);
      accountes.update(account);
    } catch (AccountOverdraftException e) {
      e.printStackTrace();
    } catch (AmountOutOfBoundsException e) {
      e.printStackTrace();
    }
  }

  public void withdraw() {
    System.out.println("How much do you want to withdraw?");
    String ans = myOb.nextLine();

    // TransactionDao transactiones = new TransactionDaoPostgres();
    // AccountDao accountes = new AccountDaoPostgres();

    while (!ans.equals("X") && !ans.equals("Back")) {
      Double amount = Double.parseDouble(ans);
      try {
        account.withdraw(amount);
        transact = new Transaction(0, account.getId(), "Withdraw", amount);
        transactiones.save(transact);
        accountes.update(account);
      } catch (AccountOverdraftException e) {
        e.printStackTrace();
      } catch (AmountOutOfBoundsException e) {
        e.printStackTrace();
      }

      // transactiones.save(new Transaction(0, account.getId(), "Deposit", amount));
      // accountes.update(account);
      loggedInMenu();
    }
    if (ans.equals("X")) {
      System.exit(0);
      user = null;
      account = null;
    } else {
      loggedInMenu();
    }
  }

  public void deposit2(Account account, String ans) {
    Double amount = Double.parseDouble(ans);
    try {
      account.deposit(amount);
      //account = acc;
      transact = new Transaction(0, account.getId(), "Deposit", amount);
      transactiones.save(transact);
      accountes.update(account);
    } catch (AmountOutOfBoundsException e) {
      e.printStackTrace();
    }
  }

  public void deposit() {
    System.out.println("How much do you want to Deposit?");
    String ans = myOb.nextLine();

    // AccountDao accountes = new AccountDaoPostgres();
    // TransactionDao transactiones = new TransactionDaoPostgres();

    while (!ans.equals("X") && !ans.toLowerCase().equals("Back")) {
      Double amount = Double.parseDouble(ans);
      try {
        account.deposit(amount);
        transact = new Transaction(0, account.getId(), "Withdraw", amount);
        transactiones.save(transact);
        accountes.update(account);
      } catch (AmountOutOfBoundsException e) {
        e.printStackTrace();
      }

      // transactiones.save(new Transaction(0, account.getId(), "Deposit", amount));
      // accountes.update(account);
      loggedInMenu();
    }
    if (ans.equals("X")) {
      System.exit(0);
      user = null;
      account = null;
    } else {
      loggedInMenu();
    }
  }

  /**
   * return null if username already exists
   * 
   * @param username
   * @return
   */
  public String register1(String username) {
    if (!users.getAll().isEmpty()) {
      for (User a : users.getAll()) {
        if (a.getUsername().equals(username)) {
          System.out.println("You already entered an existing username");
          return null;
        }
      }
    } else {
      return username;
    }
    return username;
  }

  public void register2(String userName, String password, String firstName, String lastName) {

    user = new User(0, firstName, lastName, userName, password);

    users.save(user);
  }

  public void register() {
    System.out.println("Please Create A Unique UserName");
    String usernameReg = myOb.nextLine();
    // UserDao users = new UserDaoPostgres();


    if (!users.getAll().isEmpty()) {
      for (User a : users.getAll()) {
        if (a.getUsername().equals(usernameReg)) {
          System.out.println("You already entered an existing username");
          register();
        }
      }
    }

    System.out.println("Please Create A Unique Password");
    String passwordReg = myOb.nextLine();
    System.out.println("Please Enter Your First Name");
    String firstNameReg = myOb.nextLine();
    System.out.println("Please Enter Your Last Name");
    String lastNameReg = myOb.nextLine();

    user = new User(0, firstNameReg, lastNameReg, usernameReg, passwordReg);

    users.save(user);
    accountRegistration();
  }

  public boolean accountRegistration2(String input, User user) {
    User createAccountFor;

    for (User a : users.getAll()) {
      if (user.getUsername().equals(a.getUsername())) {
        createAccountFor = a;

        accountes.save(new Account(0, createAccountFor.getId(), input.toUpperCase(), 0));
        return true;
        // loginValidate();
        // user = null;
      }
    }
    return false;
  }

  public void accountRegistration() {
    // UserDao users = new UserDaoPostgres();
    // AccountDao accountes = new AccountDaoPostgres();

    System.out.println("Do you want to create a \"Checkings\" or \"Savings\" account");
    String accTypeReg = myOb.nextLine();
    User createAccountFor;

    if (accTypeReg.equalsIgnoreCase("Checkings")) {
      for (User a : users.getAll()) {
        if (user.getUsername().equals(a.getUsername())) {
          createAccountFor = a;

          accountes.save(new Account(0, createAccountFor.getId(), "Checkings", 0));
          System.out.println("You have succesfully created a Checkings Account");
          loginValidate();
          // user = null;
        }
      }
    } else if (accTypeReg.equalsIgnoreCase("Savings")) {
      for (User a : users.getAll()) {
        if (user.getUsername().equals(a.getUsername())) {
          createAccountFor = a;

          accountes.save(new Account(0, createAccountFor.getId(), "Savings", 0));
          System.out.println("You have succesfully created a Savings Account");
          loginValidate();
          // user = null;
        }
      }
    } else {
      System.out.println("You misspelled \"Savings\" or \"Checkings\"");
      accountRegistration();
    }

  }

  // public void viewTransactions() {

  // }

  // public void transfer() {

  // }

}
