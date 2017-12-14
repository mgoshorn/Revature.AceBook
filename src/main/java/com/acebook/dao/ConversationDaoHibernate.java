package com.acebook.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.entities.Conversation;
import com.acebook.entities.User;
@Repository
public class ConversationDaoHibernate implements ConversationDao {

	private static final Logger log = Logger.getRootLogger();
	
	@Autowired
	private SessionFactory sf;
	
	@Transactional
	@Override
	public Conversation save(Conversation conversation) {
		log.trace("Saving Conversation");
		sf.getCurrentSession().save(conversation);
		return conversation;
	}

	@Override
	public Conversation persist(Conversation conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Conversation get(int id) {
		return (Conversation) sf.getCurrentSession().get(Conversation.class, id);
	}
	
	@Transactional
	@Override
	public Conversation getByUsers(User user1, User user2) {
		log.trace("here is the first user: " + user1.getUsername());
		log.trace("here is the second user: " + user2.getUsername());
		return (Conversation) sf.getCurrentSession().createCriteria(Conversation.class)
				.add(Restrictions.and(
						Restrictions.eq("user1", user1),
						Restrictions.eq("user2", user2)))
				.uniqueResult();
	}

	@Override
	public Conversation load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation update(Conversation conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation merge(Conversation conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Conversation conversation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Conversation getAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
