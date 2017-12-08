package com.acebook.services;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.acebook.beans.Credentials;
import com.acebook.dao.FriendRequestDao;
import com.acebook.dao.UserDao;
import com.acebook.entities.FriendRequest;
import com.acebook.entities.User;
import com.acebook.enums.FriendRequestState;

@Service
public class FriendRequestService {

	private static final Logger log = Logger.getRootLogger();
	
	@Autowired
	FriendRequestDao dao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	FriendService fs;
	
	@Autowired
	UserService us;
	
	
	@Transactional
	public void addFriendRequest(Credentials credentials, int userId) {
		//Authenticate user
		User sender = us.authenticate(credentials); 
		if(sender == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid user credentials");
		
		User receiver = userDao.get(userId);
		if(receiver == null) {
			log.trace("Null recipient of friend request.");
		}
		
		FriendRequest request = new FriendRequest(sender, receiver);
		
		dao.save(request);
	}
	
	
	
	
	public boolean areFriends(User user, User other) {
		log.trace("Are friends");
		return fs.areFriends(user, other);
	}
	
	public FriendRequestState getFriendRequestState(@NotNull User user, @NotNull User other) {
		log.trace("Requesting friend state");
		if(areFriends(user, other)) 		return FriendRequestState.FRIENDS;
		if(hasReceivedRequest(user, other)) return FriendRequestState.REQUEST_RECEIVED;
		if(hasSentRequest(user, other)) 	return FriendRequestState.PENDING;
		return FriendRequestState.NONE;
	}
	
	private boolean hasSentRequest(User sender, User receiver) {
		log.trace("Requesting has sent request..");
		return dao.isPending(sender, receiver);
	}
	
	private boolean hasReceivedRequest(User sender, User receiver) {
		log.trace("Requesting has friend request..");
		return dao.isReceived(sender, receiver);
	}


	
	public FriendRequestState handleFriendRequestState(Credentials credentials, int userId) {
		User user = userDao.getUserByCredentials(credentials);
		if(user == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid user credentials");
		log.trace("Request from user " + user);
		
		User other = userDao.get(userId);
		log.trace("requested user found: " + user);
		if(other == null) return FriendRequestState.NONE;

		return getFriendRequestState(user, other);
	}



	@Transactional
	public boolean handleRequestResponse(Credentials credentials, int userId, boolean accept) {
		User user = userDao.getUserByCredentials(credentials);
		if(user == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid user credentials");
		log.trace("Request from user " + user);
		
		User other = userDao.get(userId);
		log.trace("requested user found: " + user);
		if(other == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Null user object");
		
		FriendRequest fr = dao.getByUsers(other, user);
		
		if(fr != null) {
			if(accept) {
				//add friend
				//Relies on User class implementation
			}
			dao.delete(fr);
		} else {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cannot accept friend request - No pending friend request");
		}
		
		return true;
	}


}
