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
		this.accountId = accountId;
		this.userId = userId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

}
