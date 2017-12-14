package com.acebook.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.acebook.entities.User;

@Entity
public class Message {
	
	@Id
	@Column(name = "message_id")
	private int msgID;
	
	@ManyToOne
	@JoinColumn(name="conversation_id")
	private Conversation conversation;
	
	private String content;
	
	@Column(name = "sender_id")
	private int senderID;
	@Column(name = "post_timestamp")
	private LocalDateTime postTime;
	
	public Message() {
		super();
	}
	
	public Message(Conversation conversation, String content, int senderID) {
		this.conversation = conversation;
		this.content = content;
		this.senderID = senderID;
		this.postTime = LocalDateTime.now();
	}
	
	public int getMsgID() {
		return msgID;
	}
	public void setMsgID(int msgID) {
		this.msgID = msgID;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	public LocalDateTime getPostTime() {
		return postTime;
	}
	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
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
