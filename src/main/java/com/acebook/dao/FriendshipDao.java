package com.acebook.dao;

import com.acebook.entities.Friendship;

public interface FriendshipDao {
	
	FriendshipDao save(FriendshipDao Friendship);
	FriendshipDao persist(FriendshipDao Friendship);
	
	FriendshipDao get(int id);
	FriendshipDao load(int id);
	
	FriendshipDao update(FriendshipDao Friendship);
	FriendshipDao merge(FriendshipDao Friendship);
	
	boolean delete(FriendshipDao Friendship);
}
