package com.revature.model;

/**
 * @author Claw
 *
 */
public class Transaction {

  public int id;
  public int accountId;
  public String type;
  public double amount;

  public Transaction(int id, int accountId, String transactionType, double amount) {

    // random unique transaction Id
    this.id = id;
    this.accountId = accountId;
    this.type = transactionType;
    this.amount = amount;
  }

  public void deposit(Account account, double amount) {

    if (amount > 0) {
      account.setBalance(account.getBalance() + amount);
    }
  }

  public void withdraw(Account account, double amount) {
    if (amount > 0) {
      account.setBalance(account.getBalance() - amount);
    }
  }

  public int getId() {
    return this.id;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getTransactionType() {
    return type;
  }

  public void setTransactionType(String transactionType) {
    this.type = transactionType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Transaction [id=" + id + ", accountId=" + accountId + ", type=" + type + ", amount="
        + amount + "]";
  }
  
  

}
