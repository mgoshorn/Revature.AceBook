package com.acebook.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conversation {
	
	@Id
	@Column(name = "conversation_id")
	private int conversationID;
	@Column(name = "user_1")
	private int user1;
	@Column(name = "user_2")
	private int user2;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private List<Message> messages;

	
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public int getConversationID() {
		return conversationID;
	}
	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}
	public int getUser1() {
		return user1;
	}
	public void setUser1(int user1) {
		this.user1 = user1;
	}
	public int getUser2() {
		return user2;
	}
	public void setUser2(int user2) {
		this.user2 = user2;
	}

}
