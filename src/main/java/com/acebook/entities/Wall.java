package com.acebook.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wall {
	
	@Id
	private int wallId;
	private User owner;
	private String content;
	private int privacy;
	
	private List<WallPost> posts;
	}
