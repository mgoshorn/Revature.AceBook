package com.acebook.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public boolean isPending(User user, User other) {
		return null != sf.getCurrentSession().createCriteria(FriendRequest.class)
				.add(Restrictions.and(
						Restrictions.eq("sender", user),
						Restrictions.eq("receiver", other)
				)).uniqueResult();
	}

	@Transactional
	@Override
	public boolean isReceived(User user, User other) {
		return isPending(other, user);
	}

	@Transactional
	@Override
	public FriendRequest getByUsers(User sender, User receiver) {
		return (FriendRequest) sf.getCurrentSession()
			.createCriteria(FriendRequest.class)
			.add(Restrictions.and(
					Restrictions.eq("sender", sender),
					Restrictions.eq("receiver", receiver)))
			.uniqueResult();
	}
}
