package com.acebook.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;
import com.acebook.util.SessionUtil;

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
	public User getUserByCredentials(Credentials credentials, String hash) {
		Session se = su.getSession();
		Criteria c = se.createCriteria(User.class);
		c.add(Restrictions.and(
				Restrictions.eq("username", credentials.getUsername()),
				Restrictions.eq("hash", hash)));
		User user = (User) c.uniqueResult();
		return user;
	}

}
