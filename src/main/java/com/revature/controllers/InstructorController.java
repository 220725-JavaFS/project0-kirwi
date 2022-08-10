package com.revature.controllers;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.layout.Histogram;
import com.revature.layout.utils.Utils;
import com.revature.models.Assignment;
import com.revature.models.Course;
import com.revature.models.User;
import com.revature.services.CourseService;

public class InstructorController {
	
	private final User instructor;
	
	
	private Scanner scan = new Scanner(System.in);
	private CourseService cs = new CourseService();
	
	private String border = Utils.stringMult("-", 50);
	
	public void home() {
		Map<Integer, Course> courses = cs.getCoursesForInstructor(instructor);
		
		String welcomeString = "Welcome, professor "+instructor.getFirstName()+" "+instructor.getLastName()+".";
		
		System.out.println();
		System.out.println();
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
					System.out.println("That is not a valid course id. Please try again");
					continue;
				}
				Course courseChoice = courses.get(id);
				coursePage(courseChoice); 
			}
		}
	}
	public void coursePage(Course course) {
		Map<Integer, Assignment> assignments = cs.getAssignmentsForCourse(course);
		Map<Integer, User> students = cs.getStudentsInCourse(course);
		
		System.out.println();
		System.out.println(border);
		System.out.println(course);
		System.out.println("No.:\t" + students.size());
		System.out.println(border);
		System.out.println();
		System.out.println(border);
		System.out.println("Assignment List");
		System.out.println(border);
		assignments.forEach((k,v) -> System.out.println(v));
		
		String answer = "";
		while (!answer.equalsIgnoreCase("back")) {
			System.out.println("Enter the assignment id for the assignment page.");
			System.out.println("Type 'BACK' to go back to the home page.");
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("back")) {
				home();
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.out.println("That is not a valid entry. Please try again.");
					continue;
				}
				Assignment assignmentChoice = assignments.get(id);
				assignmentPage(assignmentChoice);
			}
		}
	}
	
	public void assignmentPage(Assignment assignment) {
		
		List<Double> grades = cs.getGradesForAssignment(assignment);
		
		int[] distribution = new int[10];
		for (Double grade : grades) {
			int idx = (int) Math.ceil(grade/assignment.getPointsPossible() * 10) - 1;
			distribution[idx]++;
		}
		
		System.out.println("Grade Distribution for " + assignment.getTitle());
		System.out.println();
		System.out.println();
		System.out.println();
		Histogram hist = new Histogram(distribution);
		System.out.println(hist.histogram());
		System.out.println(hist.xAxis());
		System.out.println(hist.xTicks());
		
		String answer = "";
		while (!answer.equalsIgnoreCase("back")) {
			System.out.println("Type 'BACK' to go back to the course home page.");
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("back")) {
				coursePage(assignment.getCourse());
			} else {
				System.out.println("That is not a valid entry. Please try again.");
				continue;
			}
		}
	}
	
	public InstructorController(User instructor) {
		super();
		this.instructor = instructor;
	}

}
