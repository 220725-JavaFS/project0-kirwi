package com.revature.controllers;

import java.util.Map;
import java.util.Scanner;

import com.revature.models.Course;
import com.revature.models.User;
import com.revature.services.AdminService;

public class AdminController {

	private final Map<Integer, User> instructors;
	private final Map<Integer, Course> courses;
	
	private AdminService as = new AdminService();
	private Scanner scan = new Scanner(System.in);
	
	public void home() {
		
	}
	
	public AdminController() {
		super();
		this.instructors = as.getInstructors();
		this.courses = as.getAllCourses();
	}
}
