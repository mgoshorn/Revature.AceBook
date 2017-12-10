package com.acebook.services;

import java.util.List;

import com.acebook.beans.Credentials;
import com.acebook.beans.SignUp;
import com.acebook.entities.User;

public interface UserService {

	public User authenticate(Credentials credentials);
	
	public User signup(SignUp signup);

	public List<User> getFriends(int userId);
}
	