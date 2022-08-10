package com.revature.services;

import java.util.Map;

import com.revature.daos.AdminDAO;
import com.revature.daos.AdminDAOImpl;
import com.revature.models.Course;
import com.revature.models.User;

public class AdminService {
	
	private AdminDAO adminDao = new AdminDAOImpl();
	
	public Map<Integer, Course> getAllCourses() {
		return adminDao.getAllCourses();
	}
	
	public void addCourse(String title, String description, int instructorId) {
		adminDao.addCourse(title, description, instructorId);
	}
	
	public Map<Integer, User> getInstructors() {
		return adminDao.getInstructors();
	}

}
