package com.acebook.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Wall_Post")
public class WallPost implements Comparable<WallPost> {
	
	@Id
	@Column(name="wall_post_id")
	private int wallPostId;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User poster;
	@Column(name="content")
	private String body;
	@Column(name="POST_TIMESTAMP")
	private LocalDateTime postTime;
	private int privacy = 1;

	@OneToMany(mappedBy="parent", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Comment> comments;
	
	public WallPost(User wallOwner, User poster, String postbody) {
		this.owner = wallOwner;
		this.poster = poster;
		this.body = postbody;
		this.postTime = LocalDateTime.now();
	}

	public WallPost() {
		super();
	}

	public WallPost(int wallPostId, User owner, User poster, String body, LocalDateTime postTime) {
		super();
		this.wallPostId = wallPostId;
		this.owner = owner;
		this.poster = poster;
		this.body = body;
		this.postTime = postTime;
	}

	@Override
	public String toString() {
		return "WallPost [wallPostId=" + wallPostId + ", owner=" + owner + ", poster=" + poster + ", body=" + body
				+ ", postTime=" + postTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((postTime == null) ? 0 : postTime.hashCode());
		result = prime * result + privacy;
		result = prime * result + wallPostId;
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
		WallPost other = (WallPost) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (postTime == null) {
			if (other.postTime != null)
				return false;
		} else if (!postTime.equals(other.postTime))
			return false;
		if (privacy != other.privacy)
			return false;
		if (wallPostId != other.wallPostId)
			return false;
		return true;
	}

	public int getWallPostId() {
		return wallPostId;
	}

	public void setWallPostId(int wallPostId) {
		this.wallPostId = wallPostId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}
	
	public int getPrivacy() {
		return privacy;
	}
	
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	
	

	public WallPost(int wallPostId, User owner, User poster, String body, LocalDateTime postTime, int privacy,
			List<Comment> comments) {
		super();
		this.wallPostId = wallPostId;
		this.owner = owner;
		this.poster = poster;
		this.body = body;
		this.postTime = postTime;
		this.privacy = privacy;
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int compareTo(WallPost wp) {
		return this.getPostTime().compareTo(wp.getPostTime());
	}

	
}
