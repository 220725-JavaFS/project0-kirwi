package com.revature.models;

public class User {
	
	private final int userId;
	private final String firstName;
	private final String lastName;
	private final Role role;
	
	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Role getRole() {
		return role;
	}
	
	@Override
	public String toString() {
		return "" + userId + " | " + lastName + ", " + firstName;
	}
	

	public User(int userId, String firstName, String lastName, String role) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = Role.valueOf(role);
	}
}