package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.revature.models.Course;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public Map<Integer, Course> getAllCourses() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT c.course_id as cid, c.title as t, "
						+"c.description as d, u.user_id as uid, "
						+"u.first_name as fn, u.last_name as ln, u.user_role as ur "
						+"FROM courses c INNER JOIN users u "
						+"ON c.instructor_id = u.user_id;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, Course> courses = new HashMap<>();
			while (result.next()) {
				User instructor = new User(
								result.getInt("uid"),
								result.getString("fn"),
								result.getString("ln"),
								result.getString("ur"));
				Course course = new Course(
								result.getInt("cid"),
								result.getString("t"),
								result.getString("d"),
								instructor);
				courses.put(result.getInt("cid"), course);
			}
			
			return courses;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addCourse(String title, String description, int instructorId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO courses (title, description, instructor_id) "
						+"VALUES (?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, title);
			statement.setString(++count, description);
			statement.setInt(++count, instructorId);
			statement.execute();
			System.out.println("Course added!");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Map<Integer, User> getInstructors() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_role = 'INSTRUCTOR';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, User> instructors = new HashMap<>();
			
			while (result.next()) {
				User instructor = new User(
									result.getInt("user_id"),
									result.getString("first_name"),
									result.getString("last_name"),
									result.getString("user_role")
									);
				instructors.put(result.getInt("user_id"), instructor);
			}
			
			return instructors;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, User> getStudents() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_role = 'STUDENT';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Map<Integer, User> students = new HashMap<>();
			
			while (result.next()) {
				User student = new User (
								result.getInt("user_id"),
								result.getString("first_name"),
								result.getString("last_name"),
								result.getString("user_role")
								);
				students.put(result.getInt("user_id"), student);
			
			}
			return students;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
