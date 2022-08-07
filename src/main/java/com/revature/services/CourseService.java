package com.revature.services;

import java.util.List;

import com.revature.daos.CourseDAO;
import com.revature.daos.CourseDAOImpl;
import com.revature.models.Assignment;
import com.revature.models.Course;
import com.revature.models.Grade;
import com.revature.models.User;

public class CourseService {

	private CourseDAO courseDao = new CourseDAOImpl();
	
	public Course getCourseById(int courseId) {
		return courseDao.getCourseById(courseId);
	}
	
	public List<User> getStudentsInCourse(Course course) {
		return courseDao.getStudentsInCourse(course);
	}
	
	public List<Assignment> getAssignmentsForCourse(Course course) {
		return courseDao.getAssignmentsForCourse(course);
	}
	
	public Grade getGradeForStudent(Assignment assignment, User student) {
		return courseDao.getGradeForStudent(assignment, student);
	}
	
	public void addAssignmentToCourse(Assignment assignment, Course course) {
		courseDao.addAssignmentToCourse(assignment, course);
	}
	
	public void deleteAssignment(Assignment assignment) {
		courseDao.deleteAssignment(assignment);
	}
}
