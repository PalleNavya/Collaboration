package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.User;



@SuppressWarnings("deprecation")
@Repository(value = "UserDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean save(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(int user_id) {
		// TODO Auto-generated method stub
		try {
			System.out.println(user_id);
			sessionFactory.getCurrentSession().delete(user_id);
			System.out.println("working");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public User get(int user_id) {
		// TODO Auto-generated method stub
		String hql = "from User where user_id=" + user_id ;

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("user retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public User getName(String name) {
		// TODO Auto-generated method stub
		String hql = "from User where user_id=" + "'" + name + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("username retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public List<User> list() {
		// TODO Auto-generated method stub
		String hql = " from User";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}

	@Transactional
	public User authenticate(String email, String password) {
		// TODO Auto-generated method stub
		String hql = "from User where email = '" + email + "' and password='" + password + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public User isValidUser(String email, String password) {
		// TODO Auto-generated method stub
		String hql = "from User where email = '" + email + "' and password='" + password + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public void setOnline(int user_id) {
		String hql="UPDATE User SET isOnline='Y' where user_id=" + user_id;
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}


	@Transactional
	public void setOffline(int user_id) {
		String hql="UPDATE User SET isOnline='N' where user_id=" + user_id;
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}


}
