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
			String sql = "SELECT * FROM users WHERE user_id = " + userId + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				User user = new User(
						result.getInt("user_id"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("role")
						);
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void insertUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (user_id, first_name, last_name, role)"
					+ "		VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setInt(++count, user.getUserId());
			statement.setString(++count, user.getFirstName());
			statement.setString(++count, user.getLastName());
			statement.setString(++count, user.getRole().toString());
			if (statement.execute()) System.out.println("User successfully added!");
			else System.out.println("There was an error adding the user. Please try again.");
			
		} catch (SQLException e)  {
			e.printStackTrace();
		}
	}

}
