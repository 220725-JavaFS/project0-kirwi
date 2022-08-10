package com.revature.services;

import java.util.List;
import java.util.Map;

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
	
	public Map<Integer, User> getStudentsInCourse(Course course) {
		return courseDao.getStudentsInCourse(course);
	}
	
	public Map<Integer, Course> getCoursesForStudent(User student) {
		return courseDao.getCoursesForStudent(student);
	}
	
	public Map<Integer, Assignment> getAssignmentsForCourse(Course course) {
		return courseDao.getAssignmentsForCourse(course);
	}
	
	public Grade getGradeForStudent(int assignmentId, Course course, User student) {
		return courseDao.getGradeForStudent(assignmentId, course, student);
	}
	
	public Map<Integer, Course> getCoursesForInstructor(User instructor) {
		return courseDao.getCoursesForInstructor(instructor);
	}
	
	public void addAssignmentToCourse(Assignment assignment, Course course) {
		courseDao.addAssignmentToCourse(assignment, course);
	}
	
	public void deleteAssignment(Assignment assignment) {
		courseDao.deleteAssignment(assignment);
	}
	
	public List<Double> getGradesForAssignment(Assignment assignment)  {
		return courseDao.getGradesForAssignment(assignment);
	}
}
