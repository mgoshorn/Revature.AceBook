package com.acebook.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			user = notNull(signup, user);
			user = validPassword(signup, user);
			user = validEmail(signup, user);
			
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
	
	private User notNull(SignUp signup, User user) {
		if(signup.getUsername().equals("") || signup.getFirstName().equals("") 
		|| signup.getLastName().equals("") || signup.getEmail().equals("") 
		|| signup.getBirthday().equals("") || signup.getPassword().equals(""))
		{
			return null;
		
		}
		else {
			return user;
		}
	}
	
	private User validPassword(SignUp signup, User user) {
		String pw = signup.getPassword();
		Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pw);
		boolean test = matcher.find();
		
		if(test) {
			return user;
		}
		else {
			return null;
		}
		
	}
	
	private User validEmail(SignUp signup, User user) {
		String email = signup.getEmail();
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		boolean test = matcher.matches();
		
		if(test) {
			return user;
		}
		else {
			
		}
		return null;
	}
}
