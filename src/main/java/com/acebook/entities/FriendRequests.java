package com.acebook.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FriendRequests {
	
	@Id
	private int requestId;
	private int sender;
	private int receiver;
	
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	
	
}
