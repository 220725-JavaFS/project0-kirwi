package com.revature.models;

public class Student extends User {
	private final Course[] courses;
	
	public Student(String userName, String password, String firstName, String lastName, int id, String role, Course[] courses) {
		super(userName, password, firstName, lastName, id, role);
		this.courses = courses;
	}
}
