package com.revature.controllers;

import java.util.Map;
import java.util.Scanner;

import com.revature.layout.utils.Utils;
import com.revature.models.Course;
import com.revature.models.User;
import com.revature.services.AdminService;
import com.revature.services.UserService;

public class AdminController {

	
	
	private AdminService as = new AdminService();
	private UserService us = new UserService();
	private Scanner scan = new Scanner(System.in);
	private String largeBorder = Utils.stringMult("-", 50);
	private String smallBorder = Utils.stringMult("-", 20);
	
	public void home() {
		Map<Integer, User> instructors = as.getInstructors();
		Map<Integer, Course> courses = as.getAllCourses();
		
		String welcomeString = "Welcome to the administrator page";
		
		System.out.println(largeBorder);
		System.out.println(welcomeString);
		System.out.println(largeBorder);
		System.out.println();
		System.out.println();
		
		System.out.println("Instructors");
		System.out.println(smallBorder);
		instructors.forEach((k,v) -> {System.out.println(v); System.out.println();});
		System.out.println();
		
		System.out.println("Courses");
		System.out.println(smallBorder);
		courses.forEach((k,v) -> {System.out.println(v); System.out.println();});
		System.out.println();
		System.out.println();
		
		String answer = "";
		while (!answer.equalsIgnoreCase("exit")) {
			System.out.println("Type 'ADD' to add a course to the university catalog;");
			System.out.println("Type 'EXIT' to log out;");
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("exit")) {
				LoginController lg = new LoginController();
				lg.login();
			} else if (answer.equalsIgnoreCase("add")) {
				System.out.println("Enter a course title.");
				String title = scan.nextLine();
				System.out.println("Enter a course description.");
				String description = scan.nextLine();
				System.out.println("Enter the instructor id for the professor teaching the course.");
				int userId = Integer.parseInt(scan.nextLine());
				User instructor = us.getInstructorById(userId);
				if (instructor != null) {
					as.addCourse(title, description, userId);
					home();
				} else {
					System.out.println("Please make sure you are entering a valid instructor id.");
				}
			} else {
				System.out.println("That was not a valid choice. Please try again.");
				continue;
			}
		}
	}
	
	public AdminController() {
		super();
	}
}
