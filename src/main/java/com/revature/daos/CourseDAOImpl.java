package com.revature.daos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Assignment;
import com.revature.models.Course;
import com.revature.models.Grade;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class CourseDAOImpl implements CourseDAO {

	@Override
	public Course getCourseById(int courseId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT c.title as t, c.description as d, c.instructor_id as iid, u.first_name as fn, u.last_name as ln, u.user_role as ur "
						+"FROM courses c INNER JOIN users u "
						+"ON c.instructor_id = u.user_id "
						+"WHERE course_id = "+courseId+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				Course course = new Course(
								courseId,
								result.getString("t"),
								result.getString("d"),
								new User(
										result.getInt("iid"),
										result.getString("fn"),
										result.getString("ln"),
										result.getString("ur")
										)
								);
				
				return course;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, User> getStudentsInCourse(Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT u.user_id as uid, u.first_name as fn, u.last_name as ln, u.user_role as ur " 
						+"FROM users u INNER JOIN student_schedule ss "
						+"ON u.user_id = ss.student_id "
						+"WHERE ss.course_id = "+course.getCourseId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, User> students = new HashMap<>();
			
			while (result.next()) {
				User student = new User(
								result.getInt("uid"),
								result.getString("fn"),
								result.getString("ln"),
								result.getString("ur")
								);
				students.put(student.getUserId(), student);
			}
			
			return students;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<Integer, Course> getCoursesForStudent(User student) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT c.course_id as cid, c.title as t, c.description as d, c.instructor_id as iid, u.first_name as fn, u.last_name as ln, u.user_role as ur "
						+"FROM courses c INNER JOIN student_schedule ss "
						+"ON c.course_id = ss.course_id "
						+"INNER JOIN users u "
						+"ON c.instructor_id = u.user_id "
						+"WHERE ss.student_id = "+student.getUserId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, Course> courses = new HashMap<>();
			
			while (result.next()) {
				User instructor = new User(
									result.getInt("iid"),
									result.getString("fn"),
									result.getString("ln"),
									result.getString("ur")
									);
				Course course = new Course(
								result.getInt("cid"),
								result.getString("t"),
								result.getString("d"),
								instructor
								);
				courses.put(result.getInt("cid"), course);
			}
			
			return courses;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Map<Integer, Assignment> getAssignmentsForCourse(Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM assignments WHERE course_id = "+course.getCourseId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, Assignment> assignments = new HashMap<>();
			
			while (result.next()) {
				Assignment assignment = new Assignment(
										result.getInt("assignment_id"),
										result.getString("title"),
										course,
										result.getDouble("points_possible")
										);
				assignments.put(assignment.getAssignmentId(), assignment);
			}
			
			return assignments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Grade getGradeForStudent(int assignmentId, Course course, User student) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
		
			String sql = "SELECT a.title as t, a.points_possible as pp, g.points_earned as pe "
						+"FROM assignments a INNER JOIN grades g "
						+"ON a.assignment_id = g.assignment_id "
						+"WHERE a.assignment_id = "+assignmentId
						+"AND a.course_id = "+course.getCourseId()
						+"AND g.student_id = "+student.getUserId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				Assignment assignment = new Assignment(
										assignmentId,
										result.getString("t"),
										course,
										result.getDouble("pp")
										);
				Grade grade = new Grade(
								assignment,
								result.getDouble("pe"),
								student
								);
				return grade;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<Integer, Course> getCoursesForInstructor(User instructor) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM courses WHERE instructor_id = "+instructor.getUserId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, Course> courses = new HashMap<>();
			
			while (result.next()) {
				Course course = new Course(
								result.getInt("course_id"),
								result.getString("title"),
								result.getString("description"),
								instructor
								);
				courses.put(course.getCourseId(), course);
			}	
			return courses;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addAssignmentToCourse(Assignment assignment, Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO assignments (assignment_id, title, course_id, points_possible)"
						+"VALUES (?, ?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setInt(++count, assignment.getAssignmentId());
			statement.setString(++count, assignment.getTitle());
			statement.setInt(++count, course.getCourseId());
			statement.setDouble(++count, assignment.getPointsPossible());
			if (statement.execute()) System.out.println("Assignment added!");
			else System.out.println("There was an error submitting the assignment. Please try again.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAssignment(Assignment assignment) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			int assId = assignment.getAssignmentId();
			String sql = "BEGIN;"
						+"DELETE FROM grades WHERE assignment_id = "+assId+";"
						+"DELETE FROM assignments WHERE assignment_id = "+assId+";"
						+"COMMIT;";
			PreparedStatement statement = conn.prepareStatement(sql);
			if (statement.execute()) System.out.println("The assignment was deleted!");
			else System.out.println("There was an error deleting the assignment. Please try again.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Double> getGradesForAssignment(Assignment assignment) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT points_earned FROM grades WHERE assignment_id = "+assignment.getAssignmentId();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Double> grades = new LinkedList<>();
			
			while (result.next()) {
				Double grade = result.getDouble("points_earned");
				grades.add(grade);
			}
			return grades;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
