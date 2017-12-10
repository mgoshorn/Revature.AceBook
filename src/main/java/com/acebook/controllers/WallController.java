package com.acebook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.acebook.beans.Credentials;
import com.acebook.beans.PostRequest;
import com.acebook.entities.WallPost;
import com.acebook.services.WallService;

@RestController
@RequestMapping("wall")
public class WallController {

	@Autowired
	WallService ws;
	
	@PostMapping("post/{userId}")
	public WallPost requestWallPost(@PathVariable("userId") int userId,
						@RequestBody PostRequest postRequest) {
		
		ws.wallPost(userId, postRequest);
		return null;
	}
	
	@PostMapping("read/{userId}")
	public List<WallPost> handleGetWallPosts(@PathVariable("userId") int userId,
						@RequestBody Credentials credentials) {
		return ws.getWallPosts(userId, credentials);
		
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<String> handleException(HttpStatusCodeException e) {
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		
	}

}
