package com.acebook.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.acebook.beans.Credentials;
import com.acebook.dao.ConversationDao;
import com.acebook.entities.Conversation;
import com.acebook.entities.Message;
import com.acebook.entities.User;

@Service
public class MessageService {
	
	private static final Logger log = Logger.getRootLogger();

	@Autowired
	ConversationDao convDao;
	
	@Autowired
	UserService us;
	
	@Transactional
	public List<Message> getMessages(int conversationId, Credentials credentials){
		User sender = us.authenticate(credentials); 
		if(sender == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unvalid user credentials");
		Conversation conv = convDao.get(conversationId);
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
}
