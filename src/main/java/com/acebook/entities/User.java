package com.acebook.entities;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class User {

	private int user_id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private String hash;
	private String salt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

}
