package com.acebook.dao;

import com.acebook.entities.Conversation;
import com.acebook.entities.User;

public interface ConversationDao {
	
	Conversation save(Conversation conversation);
	Conversation persist(Conversation conversation);
	
	Conversation get(int id);
	Conversation load(int id);
	
	Conversation update(Conversation conversation);
	Conversation merge(Conversation conversation);
	
	boolean delete(Conversation conversation);

	public Conversation getByUsers(User user1, User user2);
	
	Conversation getAll(int id);
}
