package com.acebook.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<User> signup(@RequestBody SignUp signup) {
		log.trace("Signup request received in controller");
		User user; 
		
		//Ensures that the user input a valid birthday, returns a Bad_Request if they did not
		try {
			user = service.signup(signup);
			notNull(signup);
			//validPassword(user);
			//validEmail(user);
			
		}
		catch(DateTimeParseException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		//catch(SQLException e) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		//}
		
		
		
		if(user == null) {
			log.trace("Invalid authentication credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			//TODO throw new InvalidCredentialsException()
		} else {
			log.trace("Used authenticated.");
			return ResponseEntity.ok(user);
		}
	}
	
	private void notNull(SignUp signup) {
		if(signup.getUsername().equals("") || signup.getFirstName().equals("") 
		|| signup.getLastName().equals("") || signup.getEmail().equals("") 
		|| signup.getBirthday().equals("") || signup.getPassword().equals(""))
		{
			
		
		}
	}

	public User userValidation(SignUp signup) {
		User user;
		try {
			user = service.signup(signup);
		}
		catch(DateTimeParseException e){
			return null;
		}
		
		return user;
	}
}
