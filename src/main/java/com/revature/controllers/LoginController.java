package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.User;
import com.revature.services.UserService;

public class LoginController {
	
	private Scanner scan = new Scanner(System.in);
	private UserService userService = new UserService();
	
	public void login() {
		System.out.println("Welcome to Revature University. Please log in with your user id.");
		int userId = Integer.parseInt(scan.nextLine());
		System.out.println("Please enter your password.");
		String password = scan.nextLine();
		
		User user = userService.getUserWithPassword(userId, password);
		
		switch (user.getRole()) {
		case STUDENT: {
			StudentController studentController = new StudentController(user);
			studentController.home();
			break;
		}
		case INSTRUCTOR: {
			InstructorController instructorController = new InstructorController(user);
			instructorController.home();
			break;
		}
//		case ADMIN: {
//			AdminController adminController = new AdminController();
//			adminController.home();
//			break;
//		}
		default: {
			System.out.println("No user by that id found. Please check your selection and try again.");
			login();
		}
		
		}
	}
}
