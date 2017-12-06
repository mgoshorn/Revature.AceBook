package com.acebook.beans;

public class SignUp {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String birthdayTimestamp;
	private String password;

	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUp(String username, String firstName, String lastName, String email, String birthdayTimestamp,
			String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthdayTimestamp = birthdayTimestamp;
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignUp [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", birthdayTimestamp=" + birthdayTimestamp + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdayTimestamp == null) ? 0 : birthdayTimestamp.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		SignUp other = (SignUp) obj;
		if (birthdayTimestamp == null) {
			if (other.birthdayTimestamp != null)
				return false;
		} else if (!birthdayTimestamp.equals(other.birthdayTimestamp))
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
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdayTimestamp() {
		return birthdayTimestamp;
	}

	public void setBirthdayTimestamp(String birthdayTimestamp) {
		this.birthdayTimestamp = birthdayTimestamp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}