package com.acebook.services;

import org.springframework.stereotype.Service;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;

public interface UserService {

	public User authenticate(Credentials credentials);

}
