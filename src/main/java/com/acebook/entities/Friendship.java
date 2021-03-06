package com.acebook.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Friendship {
	@Id
	private int friendshipId;
	private int friend1;
	private int friend2;
	
	public int getFriend1() {
		return friend1;
	}
	public void setFriend1(int friend1) {
		this.friend1 = friend1;
	}
	public int getFriend2() {
		return friend2;
	}
	public void setFriend2(int friend2) {
		this.friend2 = friend2;
	}
}
