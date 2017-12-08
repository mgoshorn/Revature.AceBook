package com.acebook.dao;

import com.acebook.entities.Friendship;

public interface FriendshipDao {
	
	Friendship save(FriendshipDao Friendship);
	Friendship persist(FriendshipDao Friendship);
	
	Friendship get(int id);
	Friendship load(int id);
	
	Friendship update(FriendshipDao Friendship);
	Friendship merge(FriendshipDao Friendship);
	
	boolean delete(FriendshipDao Friendship);
	
	
}
