package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Event;


public interface EventDAO {

public boolean save(Event event); 
	
	public boolean update(Event event);
	
	public boolean delete(int event_id);
	
	public Event get(int event_id);
	
	public Event getName(String name);
	
	public List<Event> list();
	

}

