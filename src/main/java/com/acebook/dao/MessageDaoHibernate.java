package com.acebook.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acebook.entities.Conversation;
import com.acebook.entities.Message;

@Repository
public class MessageDaoHibernate implements MessageDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public Message save(Message Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message persist(Message Message) {
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
	public Message update(Message Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message merge(Message Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Message Message) {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public Conversation getConversation(int conversationId) {

		return (Conversation) sf.getCurrentSession().get(Conversation.class, conversationId);
	}*/

}
