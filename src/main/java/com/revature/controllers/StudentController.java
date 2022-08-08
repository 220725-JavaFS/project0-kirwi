package com.revature.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Course;
import com.revature.models.User;
import com.revature.services.CourseService;
import com.revature.layout.*;

public class StudentController {

	private final User student;
	private final List<Course> courses;
	
	private Scanner scan = new Scanner(System.in);
	private CourseService cs = new CourseService();
	
	public void home() {
		String welcomeString = "Welcome "+student.getFirstName()+" "+student.getLastName();
		System.out.println(welcomeString);
		
		List<Card> courseCards = new LinkedList<>();
		
		for (Course c : courses) {
			User instructor = c.getInstructor();
			String profString = "Inst.: "+instructor.getFirstName()+" "+instructor.getLastName();
			String[] contents = new String[] {c.getTitle(), c.getDescription(), profString};
			Card card = new Card(""+c.getCourseId(), contents);
			courseCards.add(card);
		}
		Card[] cardsToPrint = courseCards.toArray(new Card[courseCards.size()]);
		Card.rowOfCards(cardsToPrint);
		
		String answer = "";
		while(!answer.equalsIgnoreCase("exit")) {
			System.out.println("Enter the course id for the course homepage. Type 'EXIT' to log out.");
			
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("exit")) {
				LoginController log = new LoginController();
				log.login();
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(answer);
				}catch(NumberFormatException e) {
					System.out.println("That is not a valid course id, please try again");
					continue;
				}
				Course courseChoice = cs.getCourseById(id);
				coursePage(courseChoice);
				
			}
		}
	}
	
	public void course(Course course) {
		
	}
	
	public StudentController(User student) {
		super();
		this.student = student;
		this.courses = cs.getCoursesForStudent(student);
	}
}
