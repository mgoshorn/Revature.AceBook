package com.acebook.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acebook.entities.FriendRequest;
import com.acebook.entities.User;

@Repository
public class FriendRequestDaoHibernate implements FriendRequestDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public FriendRequest save(FriendRequest request) {
		sf.getCurrentSession().save(request);
		return request;
	}
	
	@Override
	public void delete(FriendRequest request) {
		sf.getCurrentSession().delete(request);
	}

	@Override
	public boolean isPending(User sender, User receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReceived(User sender, User receiver) {
		// TODO Auto-generated method stub
		return false;
	}
}
