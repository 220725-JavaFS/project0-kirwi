package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Course;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public List<Course> getAllCourses() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT (c.course_id, c.title, c.description, u.user_id, u.first_name, u.last_name, u.user_role)"
						+"FROM courses c INNER JOIN users u"
						+"ON c.instructor_id = u.user_id";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Course> courses = new LinkedList<>();
			while (result.next()) {
				User instructor = new User(
								result.getInt("u.user_id"),
								result.getString("u.first_name"),
								result.getString("u.last_name"),
								result.getString("u.user_role"));
				Course course = new Course(
								result.getInt("c.course_id"),
								result.getString("c.title"),
								result.getString("c.decription"),
								instructor);
				courses.add(course);
			}
			
			return courses;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void enrollStudentInCourse(User student, Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "BEGIN;"
						+"INSERT INTO users (user_id, first_name, last_name, user_role)"
						+"VALUES (?, ?, ?, ?)"
						+"INSERT INTO courses (course_id, title, description, instructor_id)"
						+"VALUES (?, ?, ?, ?)"
						+"INSERT INTO student_schedule (sutdent_id, course_id)"
						+"VALUES (?, ?)"
						+"COMMIT;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setInt(++count, student.getUserId());
			statement.setString(++count, student.getFirstName());
			statement.setString(++count, student.getLastName());
			statement.setString(++count, student.getRole().toString());
			statement.setInt(++count, course.getCourseId());
			statement.setString(++count, course.getTitle());
			statement.setString(++count, course.getDescription());
			statement.setInt(++count, course.getInstructor().getUserId());
			statement.setInt(++count, student.getUserId());
			statement.setInt(++count, course.getCourseId());
			
			if (statement.execute()) System.out.println("Student Enrolled");
			else System.out.println("There was an error enrolling the student. Please try again.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void addCourse(Course course) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO courses (course_id, title, description, instructor_id)"
						+"VALUES (?, ?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setInt(++count, course.getCourseId());
			statement.setString(++count, course.getTitle());
			statement.setString(++count, course.getDescription());
			statement.setInt(++count, course.getInstructor().getUserId());
			
			if (statement.execute()) System.out.println("Course added!");
			else System.out.println("There was an error adding the course. Please try again.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getInstructors() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_role = 'INSTRUCTOR';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<User> instructors = new LinkedList<>();
			
			while (result.next()) {
				User instructor = new User(
									result.getInt("user_id"),
									result.getString("first_name"),
									result.getString("last_name"),
									result.getString("user_role")
									);
				instructors.add(instructor);
			}
			
			return instructors;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
