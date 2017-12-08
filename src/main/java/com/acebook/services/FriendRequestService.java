package com.acebook.services;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.beans.Credentials;
import com.acebook.dao.FriendRequestDao;
import com.acebook.dao.UserDao;
import com.acebook.entities.FriendRequest;
import com.acebook.entities.User;
import com.acebook.enums.FriendRequestState;
import com.acebook.exceptions.AcebookAPIException;
import com.acebook.exceptions.AuthenticationException;

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
	public void addFriendRequest(Credentials credentials, int userId) throws AcebookAPIException {
		//Authenticate user
		User sender = us.authenticate(credentials); 
		if(sender == null) throw new AuthenticationException();
		
		User receiver = userDao.get(userId);
		if(receiver == null) {
			log.trace("Null recipient of friend request.");
		}
		
		FriendRequest request = new FriendRequest(sender, receiver);
		
		dao.save(request);
	}
	
	
	public boolean areFriends(User user, User other) {
		return fs.areFriends(user, other);
	}
	
	public FriendRequestState getFriendRequestState(@NotNull User user, @NotNull User other) {
		if(areFriends(user, other)) 		return FriendRequestState.FRIENDS;
		if(hasReceivedRequest(user, other)) return FriendRequestState.REQUEST_RECEIVED;
		if(hasSentRequest(user, other)) 	return FriendRequestState.PENDING;
		return FriendRequestState.NONE;
	}
	
	private boolean hasSentRequest(User sender, User receiver) {
		return dao.isPending(sender, receiver);
	}
	
	private boolean hasReceivedRequest(User sender, User receiver) {
		return dao.isReceived(sender, receiver);
	}

}
