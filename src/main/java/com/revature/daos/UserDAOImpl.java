package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.utils.ConnectionUtil;

import com.revature.models.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getUserById(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = "+userId+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				User user = new User(
						result.getInt("user_id"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("user_role")
						);
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User getInstructorById(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = "+userId+" "
						+"AND user_role = 'INSTRUCTOR';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				User user = new User(
						result.getInt("user_id"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("user_role")
						);
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User getUserWithPassword(int userId, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = "+userId+" "
						+"AND pword = '"+password+"';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				User user = new User(
						result.getInt("user_id"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("user_role")
						);
			
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertUser(User user, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (user_id, first_name, last_name, role, pword)"
					+ "		VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setInt(++count, user.getUserId());
			statement.setString(++count, user.getFirstName());
			statement.setString(++count, user.getLastName());
			statement.setString(++count, user.getRole().toString());
			statement.setString(++count, password);
			if (statement.execute()) System.out.println("User successfully added!");
			else System.out.println("There was an error adding the user. Please try again.");
			
		} catch (SQLException e)  {
			e.printStackTrace();
		}
	}

}
