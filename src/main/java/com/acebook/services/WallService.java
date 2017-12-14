package com.acebook.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.acebook.beans.Credentials;
import com.acebook.beans.PostRequest;
import com.acebook.beans.ProfilePhotoUpload;
import com.acebook.dao.UserDao;
import com.acebook.entities.User;
import com.acebook.entities.WallPost;

@Service
public class WallService {

	@Autowired
	UserService us;
	
	@Autowired
	UserDao userDao;
	
	private Logger log = Logger.getRootLogger();
	
	@Transactional
	public WallPost wallPost(int targetUserId, PostRequest postRequest) {
		User poster = us.mustAuthenticate(postRequest.getCredentials());
		User wallOwner = us.mustGetUserById(targetUserId);
		
		if(!hasPermission(wallOwner, poster)) 
				throw new HttpClientErrorException(HttpStatus.FORBIDDEN, 
				"You are not authorized to post on this user's wall.");
		
		WallPost wp = new WallPost(wallOwner, poster, postRequest.getPostbody());
		wallOwner.getWallPosts().add(wp);
		return wp;
	}
	
	@Transactional
	public boolean hasPermission(User owner, User actor) {
		if(owner.equals(actor)) return true;
		return owner.getFriends().contains(actor);
	}

	@Transactional
	public List<WallPost> getWallPosts(int targetUserId, Credentials credentials) {
		User poster = us.mustAuthenticate(credentials);
		User wallOwner = us.mustGetUserById(targetUserId);
		
		if(!hasPermission(wallOwner, poster)) 
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, 
			"You are not authorized to view this user's wall.");
		
		//List<WallPost> posts = wallOwner.getWallPosts();
		List<WallPost> posts = userDao.getWallPosts(wallOwner);
		
		Hibernate.initialize(posts);
		
		return posts;
	}

	public void updateProfile(ProfilePhotoUpload upload) {
		//validate is image type
		if(!upload.getFileType().startsWith("image/")) 
			throw new HttpClientErrorException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Only image files allowed!");
		
		
		us.updateProfilePhoto(upload);
		
		log.trace("User profile image updated");
		
	}
	
}
