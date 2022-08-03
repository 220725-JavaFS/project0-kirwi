package com.revature.models;

public class Grade {
	Student student;
	Assignment assignment;
	double score;
	
	public Grade(Student student, Assignment assignment, double score) {
		super();
		this.student = student;
		this.assignment = assignment;
		this.score = score;
	}
}
