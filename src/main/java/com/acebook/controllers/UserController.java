package com.acebook.controllers;


import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.acebook.beans.Credentials;
import com.acebook.beans.SignUp;
import com.acebook.entities.User;
import com.acebook.services.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
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
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public ResponseEntity<User> signup(@RequestBody SignUp signup) {
		log.trace("Signup request received in controller");
		log.trace(signup);
		User user = service.signup(signup); 
		
		if(user == null) {
			log.trace("Invalid authentication credentials");
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "You must be logged in to do this.");
		} else {
			log.trace("Used authenticated.");
			return ResponseEntity.ok(user);
		}
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<String> handleexception(HttpStatusCodeException e){
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
	}
	
	@GetMapping("friends/{userId}")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public List<User> getFriends(@PathVariable("userId") int userId) {
		return service.getFriends(userId);
	}
	

	@GetMapping("friendrequests/{userId}")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public List<User> getFriendsRequests(@PathVariable("userId") int userId) {
		return service.getFriendRequests(userId);
	}

	@GetMapping(value="profile/{userId}", produces = MediaType.IMAGE_PNG_VALUE)
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public byte[] getProfilePhoto(@PathVariable("userId") int userId) throws IOException  {
		try {
			byte[] arr = service.getProfileImage(userId);
			log.trace("Returning array!");
			return arr;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		} catch(HttpClientErrorException e) {
			byte[] arr = new byte[512*512];
			UserController.class.getClassLoader().getResourceAsStream("default_image.jpg").read(arr);
			return arr;
//			return Files.readAllBytes(new File("src/main/resources/default_image.jpg").toPath());
			
		}
		

	}
	
	@GetMapping("profile/owner/{userId}")
	@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
	public User getByUserId(@PathVariable("userId") int userId) {
		log.trace("Trying to get the owner by ID");
		try {
			return service.mustGetUserById(userId);
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
