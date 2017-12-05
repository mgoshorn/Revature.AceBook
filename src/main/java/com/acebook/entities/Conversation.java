package com.acebook.entities;

import javax.persistence.Entity;

@Entity
public class Conversation {
	
	private int conversationID;
	private int user1;
	private int user2;
	
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
