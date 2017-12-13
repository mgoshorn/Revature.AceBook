package com.acebook.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.entities.Conversation;
@Repository
public class ConversationDaoHibernate implements ConversationDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public Conversation save(Conversation Conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation persist(Conversation Conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Conversation get(int id) {
		return (Conversation) sf.getCurrentSession().get(Conversation.class, id);
	}

	@Override
	public Conversation load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation update(Conversation Conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation merge(Conversation Conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Conversation Conversation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Conversation getAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
