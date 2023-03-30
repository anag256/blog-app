package com.projects.blogs.services;

import java.util.List;



import com.projects.blogs.bean.UserBean;


public interface UserService {
	
	UserBean createUser(UserBean user);
	UserBean updateUser(UserBean user,Integer userId);
	UserBean getUserById(Integer userId);
	List<UserBean> getAllUsers();
	void deleteUser(Integer userId);
}
