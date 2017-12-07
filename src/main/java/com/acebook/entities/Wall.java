package com.acebook.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wall {
	
	@Id
	private int wallPostID;
	private int authorID;
	private String content;
	private int privacy;
	
	public int getWallPostID() {
		return wallPostID;
	}
	public void setWallPostID(int wallPostID) {
		this.wallPostID = wallPostID;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	
	
	
}
