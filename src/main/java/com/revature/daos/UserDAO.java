package com.revature.daos;

import com.revature.models.User;

public interface UserDAO {

	User getUserById(int userId);
	
	User getUserWithPassword(int userId, String password);
	
	void insertUser(User user, String password);
}
