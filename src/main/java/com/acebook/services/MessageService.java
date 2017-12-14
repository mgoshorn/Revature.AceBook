package com.acebook.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.acebook.beans.Credentials;
import com.acebook.beans.PostRequest;
import com.acebook.dao.ConversationDao;
import com.acebook.dao.MessageDao;
import com.acebook.entities.Conversation;
import com.acebook.entities.Message;
import com.acebook.entities.User;

@Service
public class MessageService {
	
	private static final Logger log = Logger.getRootLogger();

	@Autowired
	ConversationDao convDao;
	
	@Autowired
	MessageDao msgDao;
	
	@Autowired
	UserService us;
	
	@Transactional
	public List<Message> getMessages(int receiverId, Credentials credentials){
		User sender = us.authenticate(credentials); 
		if(sender == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid user credentials");
		User receiver = us.getUserById(receiverId);
		log.trace("User who should receive a conversation request: " + receiver);
		if(receiver == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid receiver id");
		if(!sender.getFriends().contains(receiver)) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "You are not friends with this user");
		}
		if(receiver.getUser_id()> sender.getUser_id()) {
			User temp = receiver;
			receiver = sender;
			sender = temp;
		}
		Conversation conv = convDao.getByUsers(sender, receiver);
		if(conv == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Null conversation requested");
		List<Message> messages = conv.getMessages();
		log.trace("\n");
		log.trace("This is the array of messages: " + messages);
		log.trace("\n");
		for(Message message: messages) {
			Hibernate.initialize(message);
		}
		
		return messages;
	}
	
	@Transactional
	public Message sendMessage(int receiverId, PostRequest postRequest) {
		User sender = us.authenticate(postRequest.getCredentials()); 
		log.trace("User who should send a message: " + sender);
		if(sender == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid user credentials");
		User receiver = us.getUserById(receiverId);
		log.trace("User who should receive a message: " + receiver);
		if(receiver == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid receiver id");
		Conversation conv = convDao.getByUsers(sender, receiver);
		log.trace("got conversation: " + conv);
		if(conv == null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "You have not started a conversation with this user");
		}
		else {
			Hibernate.initialize(conv);
		}
		log.trace("here is the conversation: " + conv);
		Message message = new Message(conv, postRequest.getPostbody(), sender.getUser_id());
		message = msgDao.save(message);
		log.trace("here is the message: " + message);
		Hibernate.initialize(message);
		conv.addMessage(message);
		return message;
	}

	@Transactional
	public Conversation startConversation(Credentials credentials, String receiverIn) {
		User sender = us.authenticate(credentials); 
		log.trace("User who should send a conversation request: " + sender);
		if(sender == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid user credentials");
		Optional<User> receiverOptional = us.getByUsername(receiverIn);
		User receiver = receiverOptional.get();
		log.trace("User who should receive a conversation request: " + receiver);
		if(receiver == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid receiver id");
		if(!sender.getFriends().contains(receiver)) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "You are not friends with this user");
		}
		if(receiver.getUser_id()> sender.getUser_id()) {
			User temp = receiver;
			receiver = sender;
			sender = temp;
		}
		Conversation conv = convDao.getByUsers(sender, receiver);
		if(conv == null) {
			conv = new Conversation(sender, receiver);
			conv = convDao.save(conv);
			//sender.getConversations().add(conv);
			//receiver.getConversations().add(conv);
			Hibernate.initialize(conv);
		}
		else {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Conversation already exists");
		}
		return conv;
	}
}
