package com.acebook.dao;

import com.acebook.entities.FriendRequest;
import com.acebook.entities.User;

public interface FriendRequestDao {
	public FriendRequest save(FriendRequest request);
	
	public void delete(FriendRequest request);

	public boolean isPending(User sender, User receiver);

	public boolean isReceived(User sender, User receiver);

	public FriendRequest getByUsers(User other, User user);
}
