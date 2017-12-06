package com.acebook.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;
import com.acebook.services.UserServiceImpl;

@Controller
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private static UserServiceImpl service;
	
	private static final Logger log = Logger.getRootLogger();
	
	@PostMapping("login")
	public User login(@RequestBody Credentials credentials) {
		User user = service.authenticate(credentials);
		
		if(user == null) {
			log.trace("Invalid authentication credentials");
			//TODO throw new InvalidCredentialsException()
		} else {
			log.trace("Used authenticated.");		
		}
		
		return null;
	}

}
