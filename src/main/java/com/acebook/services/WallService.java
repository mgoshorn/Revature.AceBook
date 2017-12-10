package com.acebook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acebook.beans.PostRequest;
import com.acebook.dao.UserDao;
import com.acebook.entities.User;
import com.acebook.entities.WallPost;

@Service
public class WallService {

	@Autowired
	UserService us;
	
	@Autowired
	UserDao userDao;
	
	public void wallPost(int targetUserId, PostRequest postRequest) {
		User poster = us.mustAuthenticate(postRequest.getCredentials());
		User wallOwner = us.mustGetUserById(targetUserId);
		
		
		WallPost wp = new WallPost(wallOwner, poster, postRequest.getPostbody());
		wallOwner.getWallPosts().add(wp);
	}
	
	public boolean hasPermission(User owner, User poster) {
		
	}
	
}
