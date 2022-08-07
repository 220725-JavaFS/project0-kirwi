package com.revature.services;

import java.util.List;

import com.revature.daos.AdminDAO;
import com.revature.daos.AdminDAOImpl;
import com.revature.models.Course;
import com.revature.models.User;

public class AdminService {
	
	private AdminDAO adminDao = new AdminDAOImpl();
	
	public List<Course> listAllCourses() {
		return adminDao.getAllCourses();
	}
	
	public void enrollStudentInCourse(User student, Course course) {
		adminDao.enrollStudentInCourse(student, course);
	}
	
	public void addCourse(Course course) {
		adminDao.addCourse(course);
	}
	
	public List<User> getInstructors() {
		return adminDao.getInstructors();
	}
	
	

}
