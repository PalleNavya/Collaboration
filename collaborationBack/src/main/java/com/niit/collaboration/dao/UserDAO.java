package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.User;


public interface UserDAO {
	
	public boolean save(User user); 
	
	public boolean update(User user);
	
	public boolean delete(int user_id);
	
	public User get(int user_id);
	
	public User getName(String name);
	
	public List<User> list();
	
	public User authenticate(String email, String password);
public User isValidUser(String email, String password);
	
	public void setOnline(int user_id);
	
	public void setOffline(int user_id);

	

}
