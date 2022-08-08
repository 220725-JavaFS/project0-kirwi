package com.revature.daos;

import java.util.List;

import com.revature.models.Assignment;
import com.revature.models.Course;
import com.revature.models.Grade;
import com.revature.models.User;

public interface CourseDAO {

	Course getCourseById(int courseId);
	
	List<User> getStudentsInCourse(Course course);
	
	List<Course> getCoursesForStudent(User student);
	
	List<Assignment> getAssignmentsForCourse(Course course);
	
	Grade getGradeForStudent(Assignment assignment, User student);
	
	void addAssignmentToCourse(Assignment assignment, Course course);
	
	void deleteAssignment(Assignment assignment);
}
