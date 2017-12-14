package com.acebook.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.acebook.beans.Credentials;
import com.acebook.enums.FriendRequestState;
import com.acebook.services.FriendRequestService;

@RestController
@RequestMapping("friendrequest")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class FriendRequestController {

	@Autowired
	private FriendRequestService service;
	
	private static final Logger log = Logger.getRootLogger();
	
	
	@PostMapping("request/{userIdString}")
	public boolean request(@PathVariable("userIdString") String userIdString, 
						@RequestBody Credentials credentials) {
		int userId = 0;		
		
		try {
			log.trace("The user id is: " + userIdString);
			userId = Integer.parseInt(userIdString);
			
			service.addFriendRequest(credentials, userId);
		} catch(NumberFormatException e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "URL format exception");	
		}
		return true;
	}
	
	@PostMapping("friend/{userIdString}")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public FriendRequestState getState(@PathVariable("userIdString") int userId,
			@RequestBody Credentials credentials) {
		return service.handleFriendRequestState(credentials, userId);
	}
	
	@PostMapping("accept/{userIdString}")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public boolean acceptRequest(@PathVariable("userIdString") int userId,
			@RequestBody Credentials credentials) {
		return service.handleRequestResponse(credentials, userId, true);
	}
	
	
	@PostMapping("deny/{userIdString}")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public boolean denyRequest(@PathVariable("userIdString") int userId,
			@RequestBody Credentials credentials) {
		return service.handleRequestResponse(credentials, userId, false);
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<String> handleException(HttpStatusCodeException e) {
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		
	}
	
}
