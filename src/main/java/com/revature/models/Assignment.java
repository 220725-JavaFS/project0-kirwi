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
	public Course getCourseId() {
		return course;
	}
	public double getPointsPossible() {
		return pointsPossible;
	}
	
	public Assignment(int assignmentId, String title, Course course, double pointsPossible) {
		super();
		this.assignmentId = assignmentId;
		this.title = title;
		this.course = course;
		this.pointsPossible = pointsPossible;
	}
}
