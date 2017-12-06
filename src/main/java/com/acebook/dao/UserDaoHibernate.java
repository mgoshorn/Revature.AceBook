package com.acebook.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;
import com.acebook.util.SessionUtil;

@Repository
public class UserDaoHibernate implements UserDao {
	private SessionUtil su = SessionUtil.getSessionUtil();
	
	@Override
	public User save(User user) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.save(user);
		tx.commit();
		se.close();
		return user;
	}

	@Override
	public User persist(User user) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.persist(user);
		tx.commit();
		se.close();
		return user;
	}

	@Override
	public User get(final int id) {
		Session se = su.getSession();
		User user = (User) se.get(User.class, id);
		se.close();
		return user;
	}
	
	@Override
	public User load(final int id) {
		Session se = su.getSession();
		User user = (User) se.load(User.class, id);
		se.close();
		return user;
	}

	@Override
	public boolean delete(User user) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.delete(user);
		tx.commit();
		se.close();
		return true;
	}

	@Override
	public User getUserByCredentials(Credentials credentials) {
		Session se = su.getSession();
		Criteria c = se.createCriteria(User.class);
		c.add(Restrictions.eq("username", credentials.getUsername()));
		User user = (User) c.uniqueResult();
		return user;
	}

	@Override
	public User update(User user) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.update(user);
		tx.commit();
		se.close();
		return user;
	}

	@Override
	public User merge(User user) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.merge(user);
		tx.commit();
		se.close();
		return user;
	}

	@Override
	public List<User> getFriends(User user) {
		// TODO Relies on implementation of friend join table
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		Session se = su.getSession();
		Criteria c = se.createCriteria(User.class);
		c.add(Restrictions.eq("username", username));
		return Optional.ofNullable((User) c.uniqueResult());
	}
	
	

}
