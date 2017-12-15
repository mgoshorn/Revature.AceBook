package com.acebook.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.acebook.beans.Credentials;
import com.acebook.beans.PostRequest;
import com.acebook.beans.ProfilePhotoUpload;
import com.acebook.entities.Comment;
import com.acebook.entities.WallPost;
import com.acebook.services.WallService;

@RestController
@RequestMapping("wall")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class WallController {

	@Autowired
	WallService ws;
	
	private static final Logger log = Logger.getRootLogger();
	
	@PostMapping("post/{userId}")
	public WallPost handlePostToWall(@PathVariable("userId") int userId,
						@RequestBody PostRequest postRequest) {
		log.trace("Incoming request: " + postRequest);
		return ws.wallPost(userId, postRequest);
	}
	
	@PostMapping("read/{userId}")
	public List<WallPost> handleGetWallPosts(@PathVariable("userId") int userId,
						@RequestBody Credentials credentials) {
		log.trace(credentials);
		return ws.getWallPosts(userId, credentials);
	}
	
	@PostMapping("profilePhoto/update") 
	public void updateProfilePhoto(@RequestBody ProfilePhotoUpload upload){
		log.trace("Requesting profile photo update: " + upload.getImage().length());
		ws.updateProfile(upload);
	}
	
	@PostMapping("comment/{conversationId}")
	public Comment addNewComment(@PathVariable int conversationId, @RequestBody PostRequest commentRequest) {
		return ws.addNewComment(conversationId, commentRequest);
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<String> handleException(HttpStatusCodeException e) {
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
	}

}
