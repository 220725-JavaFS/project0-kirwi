package com.revature.daos;

import com.revature.models.User;

public interface UserDAO {

	User getUserById(int userId);
	
	void insertUser(User user);
}
