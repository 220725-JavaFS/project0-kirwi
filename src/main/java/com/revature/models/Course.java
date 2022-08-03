package com.revature.models;

public class Course {
	
	private final String title;
	private final int courseNumber;
	private final Instructor instructor;
	private final String startDate;
	private final String endDate;
	private final Student[] students;
	
	public Course(String title, int courseNumber, Instructor instructor, String startDate, String endDate, Student[] students) {
		super();
		this.title = title;
		this.courseNumber = courseNumber;
		this.instructor = instructor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.students = students;		
	}
}
