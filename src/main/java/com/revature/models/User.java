package com.revature.models;

public class User {
	private final String userName;
	private final String password;
	private final String firstName;
	private final String lastName;
	private final int id;
	private final Role role;
	
	public User(String userName, String password, String firstName, String lastName, int id, String role) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.role = Role.valueOf(role);
	}
}