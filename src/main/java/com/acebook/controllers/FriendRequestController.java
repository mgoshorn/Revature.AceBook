package com.acebook.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acebook.beans.Credentials;
import com.acebook.exceptions.AcebookAPIException;
import com.acebook.services.FriendRequestService;

@RestController
@RequestMapping("friendrequest")
public class FriendRequestController {

	@Autowired
	private FriendRequestService service;
	
	private static final Logger log = Logger.getRootLogger();
	
	
	@PostMapping("request/{userIdString}")
	public ResponseEntity request(@PathVariable("userIdString") String userIdString, 
						@RequestBody Credentials credentials) {
		int userId = 0;		
		
		try {
			userId = Integer.parseInt(userIdString);
			service.addFriendRequest(credentials, userId);
			
			
		} catch(AcebookAPIException e) {
			log.warn("API Exception thrown with status code: " + e.getStatusCode());
			return ResponseEntity.status(e.getStatusCode()).body(null);
		} catch(NumberFormatException e) {
			log.warn("Number format exception thrown");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);	
		}
		
		return ResponseEntity.status(200).body(null);
		
	}
	
}
