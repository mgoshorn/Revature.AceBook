package com.acebook.entities;

import javax.persistence.Entity;

@Entity
public class FriendRequests {
	
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
