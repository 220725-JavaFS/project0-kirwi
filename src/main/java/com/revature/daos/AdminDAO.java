package com.revature.daos;

import java.util.Map;

import com.revature.models.Course;
import com.revature.models.User;

public interface AdminDAO {

	Map<Integer, Course> getAllCourses();
	
	void addCourse(String title, String description, int instructorId);
	
	Map<Integer, User> getInstructors();
	
	Map<Integer, User> getStudents();
}
