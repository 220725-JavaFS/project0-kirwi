package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	private UserDAO userDao = new UserDAOImpl();

	public User getSingleUser(int userId) {
		return userDao.getUserById(userId);
	}
	
	public void addUser(User user) {
		userDao.insertUser(user);
	}
}
