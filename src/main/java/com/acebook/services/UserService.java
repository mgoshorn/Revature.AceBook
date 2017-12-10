package com.acebook.services;

import java.util.List;

import com.acebook.beans.Credentials;
import com.acebook.beans.SignUp;
import com.acebook.entities.User;

public interface UserService {

	public User authenticate(Credentials credentials);
	
	public User mustAuthenticate(Credentials credentials);
	
	public User signup(SignUp signup);

	
	public User getUserById(int id);
	public User mustGetUserById(int id);

	public List<User> getFriends(int userId);
}
	