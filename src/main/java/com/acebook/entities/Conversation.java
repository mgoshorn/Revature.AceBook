package com.acebook.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conversation {
	
	@Id
	@Column(name = "conversation_id")
	private int conversationID;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_1")
	private User user1;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_2")
	private User user2;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private List<Message> messages;
	
	public Conversation() {
		super();
	}
	
	public Conversation(User user1, User user2) {
		this.user1 = user1;
		this.user2 = user2;
		this.messages = new ArrayList<Message>();
	}

	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public int getConversationID() {
		return conversationID;
	}
	
	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
}
