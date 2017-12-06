package com.acebook.services;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;

public interface UserService {

	public User authenticate(Credentials credentials);
	
	public User signup(User user);
}
