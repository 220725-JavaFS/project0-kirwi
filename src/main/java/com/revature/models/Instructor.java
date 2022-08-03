package com.revature.models;

public class Instructor extends User {
	
	Course[] courses;
	
	public Instructor(String userName, String password, String firstName, String lastName, int id, String role, Course[] courses) {
		super(userName, password, firstName, lastName, id, role);
		this.courses = courses;
	}
}
