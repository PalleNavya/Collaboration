package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Comment;


public interface BlogDAO {

	public boolean save(Blog blog); 
	
	public boolean update(Blog blog);
	
	public boolean delete(int blog_id);
	
	public Blog get(int blog_id);
	
	public Blog getName(String name);
	
	public List<Blog> list();
public boolean addComment(Comment blogcomment);
	
	public List<Comment> listComment(int id);
	
	public List<Comment> listOfAllComment();

}