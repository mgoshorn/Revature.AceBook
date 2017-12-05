package com.acebook.dao;

import com.acebook.entities.Message;

public interface MessageDao {
	
	Message save(Message Message);
	Message persist(Message Message);
	
	Message get(int id);
	Message load(int id);
	
	Message update(Message Message);
	Message merge(Message Message);
	
	boolean delete(Message Message);
}
