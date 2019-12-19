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
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.userId = userId;
		this.transactionType = transactionType;
		this.amount = amount;
	}

}
