package com.revature.daos;

import java.util.LinkedList;
import java.util.List;

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
			String sql = "SELECT (c.title, c.descripton, c.instructor_id, u.first_name, u.last_name, u.role)"
						+"FROM courses c INNER JOIN users u"
						+"ON c.instructor_id = u.user_id"
						+"WHERE course_id = "+courseId+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				Course course = new Course(
								courseId,
								result.getString("c.title"),
								result.getString("c.description"),
								new User(
										result.getInt("c.instructor_id"),
										result.getString("u.first_name"),
										result.getString("u.last_name"),
										result.getString("u.role")
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
	public List<User> getStudentsInCourse(Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT (u.user_id, u.first_name, u.last_name, u.role)" 
						+"FROM users u INNER JOIN student_schedule ss"
						+"ON u.user_id = ss.student_id"
						+"WHERE ss.course_id = "+course.getCourseId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<User> students = new LinkedList<>();
			
			while (result.next()) {
				User student = new User(
								result.getInt("u.user_id"),
								result.getString("u.first_name"),
								result.getString("u.last_name"),
								result.getString("u.role")
								);
				students.add(student);
			}
			
			return students;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Course> getCoursesForStudent(User student) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT (c.course_id, c.title, c.descripton, c.instructor_id, u.first_name, u.last_name, u.user_role)"
						+"FROM courses c INNER JOIN student_schedule ss"
						+"ON c.course_id = ss.course_id"
						+"INNER JOIN users u"
						+"ON ss.instructor_id = u.user_id"
						+"WHERE ss.student_id = "+student.getUserId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Course> courses = new LinkedList<>();
			
			while (result.next()) {
				User instructor = new User(
									result.getInt("c.instructor_id"),
									result.getString("u.first_name"),
									result.getString("u.last_name"),
									result.getString("u.user_role")
									);
				Course course = new Course(
								result.getInt("c.course_id"),
								result.getString("c.title"),
								result.getString("c.description"),
								instructor
								);
				courses.add(course);
			}
			
			return courses;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Assignment> getAssignmentsForCourse(Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM assignments WHERE course_id = "+course.getCourseId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Assignment> assignments = new LinkedList<>();
			
			while (result.next()) {
				Assignment assignment = new Assignment(
										result.getInt("assignment_id"),
										result.getString("title"),
										course,
										result.getDouble("points_possible")
										);
				assignments.add(assignment);
			}
			
			return assignments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Grade getGradeForStudent(Assignment assignment, User student) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
		
			String sql = "SELECT (a.points_earned)" 
						+"FROM assignments a INNER JOIN grades g"
						+"ON a.assignment_id = g.assignment_id"
						+"WHERE a.assignment_id = "+assignment.getAssignmentId()
						+"AND g.student_id = "+student.getUserId()+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				Grade grade = new Grade(
								assignment,
								result.getDouble("a.points_earned"),
								student);
				
				return grade;
				
			}
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

}
