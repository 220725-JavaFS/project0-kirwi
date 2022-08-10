package com.revature.daos;

import java.util.List;
import java.util.Map;

import com.revature.models.Assignment;
import com.revature.models.Course;
import com.revature.models.Grade;
import com.revature.models.User;

public interface CourseDAO {

	Course getCourseById(int courseId);
	
	Map<Integer, User> getStudentsInCourse(Course course);
	
	Map<Integer, Course> getCoursesForStudent(User student);
	
	Map<Integer, Assignment> getAssignmentsForCourse(Course course);
	
	Grade getGradeForStudent(int assignmentId, Course course, User student);
	
	Map<Integer, Course> getCoursesForInstructor(User instructor);
	
	void addAssignmentToCourse(Assignment assignment, Course course);
	
	void deleteAssignment(Assignment assignment);
	
	List<Double> getGradesForAssignment(Assignment assignment);
	
}
