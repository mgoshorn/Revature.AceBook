package com.acebook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Friend_Requests")
public class FriendRequest {
	
	@Id
	@Column(name="friend_request_id")
	private int requestId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender")
	private User sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="receiver")
	private User receiver;
	
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public FriendRequest(User sender, User receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public FriendRequest() {
		super();
	}
	
}
