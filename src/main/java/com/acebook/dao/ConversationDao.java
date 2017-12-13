package com.acebook.dao;

import com.acebook.entities.Conversation;

public interface ConversationDao {
	
	Conversation save(Conversation Conversation);
	Conversation persist(Conversation Conversation);
	
	Conversation get(int id);
	Conversation load(int id);
	
	Conversation update(Conversation Conversation);
	Conversation merge(Conversation Conversation);
	
	boolean delete(Conversation Conversation);

	Conversation getAll(int id);
}
