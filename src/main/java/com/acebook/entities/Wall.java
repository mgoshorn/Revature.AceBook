package com.acebook.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Wall {

	@Id
	private int wallId;
	private User owner;
	private String content;
	private int privacy;

	private List<WallPost> posts;

	public Wall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wall(int wallId, User owner, String content, int privacy, List<WallPost> posts) {
		super();
		this.wallId = wallId;
		this.owner = owner;
		this.content = content;
		this.privacy = privacy;
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Wall [wallId=" + wallId + ", owner=" + owner + ", content=" + content + ", privacy=" + privacy
				+ ", posts=" + posts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		result = prime * result + privacy;
		result = prime * result + wallId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wall other = (Wall) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		if (privacy != other.privacy)
			return false;
		if (wallId != other.wallId)
			return false;
		return true;
	}

	public int getWallId() {
		return wallId;
	}

	public void setWallId(int wallId) {
		this.wallId = wallId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public List<WallPost> getPosts() {
		return posts;
	}

	public void setPosts(List<WallPost> posts) {
		this.posts = posts;
	}

}
