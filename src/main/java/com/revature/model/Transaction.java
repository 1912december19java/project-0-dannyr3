package com.revature.model;

public class Transaction {
	
	public int transactionId;
	public int accountId;
	public int userId;
	public String transactionType;
	public double amount;
	
	public Transaction() {
		super();
	}
	
	public Transaction(int transactionId, int accountId, int userId, String transactionType, double amount) {
		
		//random unique transaction Id
		this.transactionId = transactionId;
		
		this.accountId = accountId;
		this.userId = userId;
		this.transactionType = transactionType;
		this.amount = amount;
	}
	
	public void deposit(Account account, double amount) {
		account.setBalance(account.getBalance()+amount);
	}
	
	public void withdraw(Account account, double amount) {
		if (amount > 0) {
			account.setBalance(account.getBalance()-amount);
		}
	}

}
