package com.revature.model;

import com.revature.exception.AccountOverdraftException;
import com.revature.exception.AmountOutOfBoundsException;

public class Account {

  public int id;
  public int userId;
  public String type;
  public double balance;

  public Account(int id, int userId, String type, double balance) {

    // random and unique AccountId
    this.id = id;

    this.userId = userId;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getType() {
    return type;
  }

  public void setType(String accountType) {
    this.type = accountType;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double accountBalance) {
    this.balance = accountBalance;
  }

  public void withdraw(double amount) throws AmountOutOfBoundsException, AccountOverdraftException{
    if (amount < 0.00 || amount > 10000.00) {
      throw new AmountOutOfBoundsException();
    }   
    if ((this.balance - amount) < 0){
      throw new AccountOverdraftException();
    }
    
    this.setBalance(this.getBalance() - amount);
    
  }

  public void deposit(double amount) throws AmountOutOfBoundsException{
    if (amount < 0.00 || amount > 10000.00) {
      throw new AmountOutOfBoundsException();
    } 
    
    this.setBalance(this.getBalance() + amount);
  }

  @Override
  public String toString() {
    return "Account [id=" + id + ", userId=" + userId + ", type=" + type + ", balance=" + balance
        + "]";
  }
  
  

}
