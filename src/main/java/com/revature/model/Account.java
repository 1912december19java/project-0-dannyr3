package com.revature.model;

public class Account {
	
	public int accountId;
	public int userId;
	public String accountType;
	public double accountBalance;
	
	public Account() {
		super();
	}
	
	public Account(int accountId, int userId, String accountType, double accountBalance) {
		
		//random and unique AccountId
		this.accountId = accountId;
		
		
		this.userId = userId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	
	public double getBalance() {
		return this.accountBalance;
	}
	
	public void setBalance(double amount) {
		this.accountBalance = amount;
	}

}
