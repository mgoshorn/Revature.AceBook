package com.acebook.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.acebook.beans.Credentials;
import com.acebook.beans.SignUp;
import com.acebook.entities.User;
import com.acebook.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	private static final Logger log = Logger.getRootLogger();
	
	@PostMapping("login")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public ResponseEntity<User> login(@RequestBody Credentials credentials) {
		User user = service.authenticate(credentials);
		
		if(user == null) {
			log.trace("Invalid authentication credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			//TODO throw new InvalidCredentialsException()
		} else {
			log.trace("Used authenticated.");
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping("signup")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public ResponseEntity<User> signup(@RequestBody SignUp signup) {
		log.trace("Signup request received in controller");
		User user = service.signup(signup); 
		
		if(user == null) {
			log.trace("Invalid authentication credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			//TODO throw new InvalidCredentialsException()
		} else {
			log.trace("Used authenticated.");
			return ResponseEntity.ok(user);
		}
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<String> handleexception(HttpStatusCodeException e){
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
	}
	
}
