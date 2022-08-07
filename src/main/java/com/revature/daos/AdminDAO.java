package com.revature.daos;

import java.util.List;

import com.revature.models.Course;
import com.revature.models.User;

public interface AdminDAO {

	List<Course> getAllCourses();
	
	void enrollStudentInCourse(User student, Course course);
	
	void addCourse(Course course);
	
	List<User> getInstructors();
}
