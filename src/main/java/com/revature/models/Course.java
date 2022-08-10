package com.revature.models;

public class Course {
	
	private final int courseId;
	private final String title;
	private final String description;
	private final User instructor;
	
	public int getCourseId() {
		return courseId;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public User getInstructor() {
		return instructor;
	}
	
	@Override
	public String toString() {
		String id = "ID:\t" + courseId; 
		String title = "Title:\t" + this.title +" | " + description;
		String instructorName = "Prof:\t" + instructor.getLastName()+", " + instructor.getFirstName();
		return id + '\n' + title + '\n' + instructorName;
	}
	
	public Course(int courseId, String title, String description, User instructor) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.instructor = instructor;
	}
}
