package com.acebook.dao;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;

public interface UserDao {
	
	User save(User user);
	User persist(User user);
	
	User get(int id);
	
	User load(int id);
	
	boolean delete(User user);
	
	User getUserByCredentials(Credentials credentials, String hash);
	
	
}
