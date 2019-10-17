package com.vastika.userinfo.dao;
//this is my git pratice 

import java.util.List;

import com.vastika.userinfo.model.User;

public interface UserDao {
	int saveUser(User user);
	
	int updateUser(User user);
	
	List<User> getAllUserInfo();
	
	void deleteUserInfo(int id);

}
