package com.revature.controllers;

import java.util.Map;
import java.util.Scanner;

import com.revature.models.Assignment;
import com.revature.models.Course;
import com.revature.models.Grade;
import com.revature.models.User;
import com.revature.services.CourseService;
import com.revature.layout.utils.Utils;

public class StudentController {

	private final User student;
	
	private Scanner scan = new Scanner(System.in);
	private CourseService cs = new CourseService();
	private String border = Utils.stringMult("-", 50);
	
	public void home() {
		
		Map<Integer, Course> courses = cs.getCoursesForStudent(student);
	
		String welcomeString = "Welcome "+student.getFirstName()+" "+student.getLastName();
		
		System.out.println(border);
		System.out.println(welcomeString);
		System.out.println(border);
		System.out.println();
		courses.forEach((k, v) -> {System.out.println(v); System.out.println();});
		System.out.println();
		
		String answer = "";
		while (!answer.equalsIgnoreCase("exit")) {
			System.out.println("Enter the course id for the course homepage. Type 'EXIT' to log out.");
			
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("exit")) {
				LoginController log = new LoginController();
				log.login();
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(answer);
				} catch(NumberFormatException e) {
					System.out.println("That is not a valid course id, please try again");
					continue;
				}
				Course courseChoice = cs.getCourseById(id);
				coursePage(courseChoice);
				
			}
		}
	}
	
	public void coursePage(Course course) {
		Map<Integer, Assignment> assignments = cs.getAssignmentsForCourse(course);
		String welcomeString = "Information for course " + course.getTitle() + ": " + course.getDescription();
		
		System.out.println(border);
		System.out.println(welcomeString);
		System.out.println(border);
		System.out.println();
		assignments.forEach((k, v) -> {System.out.println(v); System.out.println();});
		System.out.println();
		
		String answer = "";
		while (!answer.equalsIgnoreCase("home")) {
			System.out.println("Enter the assignment id for scores. Type 'HOME' to return the course home page");
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("home")) {
				home();
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.out.println("That is not a valid assignment id, please try again.");
					continue;
				}
				Assignment choice = assignments.get(id);
				assignmentPage(choice);
			}
		}
	}
	
	public void assignmentPage(Assignment assignment) {
		Grade grade = cs.getGradeForStudent(assignment.getAssignmentId(), assignment.getCourse(), student);
		String welcomeString = "Information for " + assignment.getTitle();

		System.out.println(border);
		System.out.println(welcomeString);
		System.out.println(border);
		System.out.println();
		System.out.println(grade);
		System.out.println();
		
		
		String answer = "";
		while (!answer.equalsIgnoreCase("back") ) {
			System.out.println("Type 'BACK' to go back to the course page.");
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("back")) {
				coursePage(assignment.getCourse());
			} else {
				System.out.println("That is not a valid option. Please try again.");
				continue;
			}
		}
		
	}
	
	public StudentController(User student) {
		super();
		this.student = student;
	}
}
