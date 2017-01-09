package com.niit.collaboration.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Chat;

@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO{

	@Autowired
	private SessionFactory sessionfactory;
	public ChatDAOImpl(SessionFactory sessionfactory)
	{
		this.sessionfactory=sessionfactory;
	}
	@Transactional
	public boolean save(Chat chat) {
		// TODO Auto-generated method stub
try {
			
			sessionfactory.getCurrentSession().save(chat);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Chat chat) {
		// TODO Auto-generated method stub
try {
			
			sessionfactory.getCurrentSession().update(chat);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Chat chat) {
		// TODO Auto-generated method stub
try {
			
			sessionfactory.getCurrentSession().delete(chat);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
