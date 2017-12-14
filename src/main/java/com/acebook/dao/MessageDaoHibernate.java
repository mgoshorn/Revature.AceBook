package com.acebook.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.entities.Message;

@Repository
public class MessageDaoHibernate implements MessageDao {

	private static final Logger log = Logger.getRootLogger();
	
	@Autowired
	private SessionFactory sf;
	
	@Transactional
	@Override
	public Message save(Message message) {
		log.trace("Saving Conversation");
		sf.getCurrentSession().save(message);
		return message;
	}

	@Override
	public Message persist(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message update(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message merge(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public Conversation getConversation(int conversationId) {

		return (Conversation) sf.getCurrentSession().get(Conversation.class, conversationId);
	}*/

}
