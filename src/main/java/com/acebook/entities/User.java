package com.acebook.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.acebook.beans.SignUp;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity representing an Acebook user.
 * 
 * @author Mitchell Goshorn
 *
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@SequenceGenerator(name = "users_seq", sequenceName = "users_seq")
	@GeneratedValue(generator = "users_seq", strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	private String username;
	
	@JsonIgnore
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private LocalDate birthdate;
	@JsonIgnore
	@Column(name = "passhash")
	private String hash;
	@JsonIgnore
	private String salt;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<WallPost> wallPosts;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "friendship", joinColumns = { @JoinColumn(name = "friend_1") }, inverseJoinColumns = {
			@JoinColumn(name = "friend_2") })
	private List<User> friends;
	
<<<<<<< HEAD
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "conversation", joinColumns = { @JoinColumn(name = "user_1") }, inverseJoinColumns = {
			@JoinColumn(name = "user_2") })
	private List<Conversation> conversations;

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}
=======
	@ManyToMany
	@JoinTable(name="friend_requests", joinColumns = { @JoinColumn(name="receiver") }, inverseJoinColumns = { @JoinColumn(name="sender") })
	@JsonIgnore
	private List<User> friendRequests;
>>>>>>> ce4f79eef1dc3e065bf7794db1389f7b9e68d943

	//User images
	
	@JoinColumn(name = "user_id")
	@OneToOne(cascade = CascadeType.ALL)
	private ProfileImages images;
	
	
	public int getUser_id() {
		return userId;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String username, String email, String firstName, String lastName, LocalDate birthdate,
			String hash, String salt, List<User> friends, List<User> friendRequests) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.hash = hash;
		this.salt = salt;
		this.friends = friends;
		this.friendRequests = friendRequests;
	}



	@Deprecated
	public void setUserID(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<WallPost> getWallPosts() {
		return wallPosts;
	}

	public void setWallPosts(List<WallPost> wallPosts) {
		this.wallPosts = wallPosts;
	}
	
	public List<User> getFriends() {
		return friends;
	}

	public List<User> getFriendRequests() {
		return friendRequests;
	}

	
	public ProfileImages getImages() {
		return images;
	}
	
	public void setImages(ProfileImages images) {
		this.images = images;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((friends == null) ? 0 : friends.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((wallPosts == null) ? 0 : wallPosts.hashCode());
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
		User other = (User) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (friends == null) {
			if (other.friends != null)
				return false;
		} else if (!friends.equals(other.friends))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (wallPosts == null) {
			if (other.wallPosts != null)
				return false;
		} else if (!wallPosts.equals(other.wallPosts))
			return false;
		return true;
	}


	public User(SignUp signup) {
		username = signup.getUsername();
		birthdate = signup.getBirthdate();
		email = signup.getEmail();
		firstName = signup.getFirstName();
		lastName = signup.getLastName();
	}

}
