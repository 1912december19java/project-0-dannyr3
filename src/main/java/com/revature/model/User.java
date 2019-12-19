package com.revature.model;

public class User {
	
	public int userId;
	public String firstName;
	public String lastName;
	public String username;
	public String password;
	public boolean isLoggedIn;
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String username, String password) {
		
		//userId will be unique but random
		this.userId = userId;
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isLoggedIn = false;
	}
	
	

}
