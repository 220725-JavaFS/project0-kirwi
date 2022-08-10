package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	private UserDAO userDao = new UserDAOImpl();

	public User getSingleUser(int userId) {
		return userDao.getUserById(userId);
	}
	
	public User getUserWithPassword(int userId, String password) {
		return userDao.getUserWithPassword(userId, password);
	}
	
	public void addUser(User user, String password) {
		userDao.insertUser(user, password);
	}
}
