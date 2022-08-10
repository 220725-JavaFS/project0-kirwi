package com.revature.models;

public class Assignment {
	
	private final int assignmentId;
	private final String title;
	private final Course course;
	private final double pointsPossible;
	
	public int getAssignmentId() {
		return assignmentId;
	}
	public String getTitle() {
		return title;
	}
	public Course getCourse() {
		return course;
	}
	public double getPointsPossible() {
		return pointsPossible;
	}
	
	@Override
	public String toString() {
		String id = "ID:\t" + assignmentId;
		String title = "Title:\t" + this.title;
		String points = "Points:\t" + pointsPossible;
		return id + '\n' + title + '\n' + points;
	}
	
	public Assignment(int assignmentId, String title, Course course, double pointsPossible) {
		super();
		this.assignmentId = assignmentId;
		this.title = title;
		this.course = course;
		this.pointsPossible = pointsPossible;
	}
}
