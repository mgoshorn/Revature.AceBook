package com.acebook.entities;

import javax.persistence.Entity;

@Entity
public class Message {
	
	private int msgID;
	private int conversationID;
	private String content;
	private int senderID;
	
	public int getMsgID() {
		return msgID;
	}
	public void setMsgID(int msgID) {
		this.msgID = msgID;
	}
	public int getConversationID() {
		return conversationID;
	}
	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSenderID() {
		return senderID;
	}
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	
	
}
