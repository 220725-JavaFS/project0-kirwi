package com.revature.models;

public class Assignment {
	Course course;
	private final String title;
	private final String assignDate;
	private final String dueDate;
	private final double pointsPossible;
	private final AssignmentType assignmentType;
	
	public Assignment(Course course, String title, String assignDate, String dueDate, double pointsPossible, String assignmentType) {
		super();
		this.course = course;
		this.title = title;
		this.assignDate = assignDate;
		this.dueDate = dueDate;
		this.pointsPossible = pointsPossible;
		this.assignmentType = AssignmentType.valueOf(assignmentType);
	}
}
