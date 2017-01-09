package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Comment;


@SuppressWarnings("deprecation")
@Repository(value = "BlogDAO")

public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean save(Blog blog) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Blog blog) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(int blog_id) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(blog_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public Blog get(int blog_id) {
		// TODO Auto-generated method stub
		String hql = "from Blog where blog_id='" + blog_id + "'";

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("blog retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Blog getName(String name) {
		// TODO Auto-generated method stub
		String hql = "from Blog where blog_id=" + "'" + name + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("blogname retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public List<Blog> list() {
		// TODO Auto-generated method stub
		String hql = " from Blog";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
	@Transactional
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<Comment> listComment(int id) {
		// TODO Auto-generated method stub
		String hql = " from Comment where blog_id=" + id;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}

	@Transactional
	public List<Comment> listOfAllComment() {
		// TODO Auto-generated method stub
		String hql = " from Comment";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}



}
