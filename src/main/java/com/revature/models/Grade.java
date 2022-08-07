package com.revature.models;

public class Grade {
	 
	private final Assignment assignment;
	private final double pointsEarned;
	private final User student;
	
	public Assignment getAssignment() {
		return assignment;
	}
	
	public double getPointsEarned() {
		return pointsEarned;
	}
	
	public User getStudent() {
		return student;
	}
	
	public Grade(Assignment assignment, double pointsEarned, User student) {
		super();
		this.assignment = assignment;
		this.pointsEarned = pointsEarned;
		this.student = student;
	}
}
