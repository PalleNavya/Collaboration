package com.niit.collaboration.dao;

import com.niit.collaboration.model.Chat;

public interface ChatDAO {
	
	public boolean save(Chat chat);
	public boolean update(Chat chat);
	public boolean delete(Chat chat);

}
