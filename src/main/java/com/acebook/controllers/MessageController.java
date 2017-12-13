package com.acebook.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acebook.beans.Credentials;
import com.acebook.entities.Message;
import com.acebook.services.MessageService;

@RestController
@RequestMapping("message")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class MessageController {
	
	@Autowired
	private MessageService service;
	
	private static final Logger log = Logger.getRootLogger();
	
	@PostMapping("{conversationId}")
	public List<Message> getMessages(@PathVariable("conversationId") int conversationId, 
			@RequestBody Credentials credentials){
		log.trace("\n");
		log.trace("This is the passed in variable: " + conversationId);
		log.trace("\n");
		return service.getMessages(conversationId, credentials);
	}
}
