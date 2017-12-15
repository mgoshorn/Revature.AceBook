package com.acebook.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "comments")
public class Comment {

	@Id
	@SequenceGenerator(name = "comments_seq", sequenceName = "comments_seq")
	@Column(name = "comment_id")
	private int commentId;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User poster;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "wall_post_id")
	@JsonIgnore
	private WallPost parent;

	@Column(name = "post_timestamp")
	private LocalDateTime postTime;

	@Column(name = "content")
	private String body;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public WallPost getParent() {
		return parent;
	}

	public void setParent(WallPost parent) {
		this.parent = parent;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", poster=" + poster + ", parent=" + parent + ", postTime="
				+ postTime + ", body=" + body + "]";
	}

	public Comment(int commentId, User poster, WallPost parent, LocalDateTime postTime, String body) {
		super();
		this.commentId = commentId;
		this.poster = poster;
		this.parent = parent;
		this.postTime = postTime;
		this.body = body;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + commentId;
		result = prime * result + ((postTime == null) ? 0 : postTime.hashCode());
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
		Comment other = (Comment) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (commentId != other.commentId)
			return false;
		if (postTime == null) {
			if (other.postTime != null)
				return false;
		} else if (!postTime.equals(other.postTime))
			return false;
		return true;
	}

}
