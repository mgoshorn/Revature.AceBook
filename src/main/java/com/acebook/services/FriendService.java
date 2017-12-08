package com.acebook.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acebook.entities.User;

@Service
public class FriendService {

	private static final Logger log = Logger.getRootLogger();
	
	public boolean areFriends(User user, User other) {
		log.trace("Friend service requesting areFriends");
		// TODO Auto-generated method stub
		return false;
	}

}
