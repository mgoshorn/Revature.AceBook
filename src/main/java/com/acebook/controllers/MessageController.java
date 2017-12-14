package com.acebook.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acebook.beans.Credentials;
import com.acebook.beans.PostRequest;
import com.acebook.entities.Conversation;
import com.acebook.entities.Message;
import com.acebook.services.MessageService;

@RestController
@RequestMapping("message")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class MessageController {
	
	@Autowired
	private MessageService service;
	
	private static final Logger log = Logger.getRootLogger();
	
	@PostMapping("{receiverId}")
	public List<Message> getMessages(@PathVariable("receiverId") int receiverId, 
			@RequestBody Credentials credentials){
		log.trace("\n");
		log.trace("This is the passed in variable: " + receiverId);
		log.trace("\n");
		return service.getMessages(receiverId, credentials);
	}
	
	@PostMapping("startconversation/{receiver}")
	public Conversation startConversation(@PathVariable("receiver") String receiver, @RequestBody Credentials credentials) {
		log.trace("starting a conversation with " + receiver);
		log.trace("with credentials: " + credentials.getUsername() + " " + credentials.getPassword());
		return service.startConversation(credentials, receiver);
	}
	
	@PostMapping("send/{receiverId}")
	public Message sendMessage(@PathVariable("receiverId") int receiverId, @RequestBody PostRequest postRequest) {
		log.trace("The user receiving this message has an id of: " + receiverId);
		log.trace("Here are the credentials of the post request: " + postRequest.getCredentials().getUsername() + " " + postRequest.getCredentials().getPassword());
		log.trace("Here is the body of the post request: " + postRequest.getPostbody() );
		return service.sendMessage(receiverId, postRequest);
	}
}
