package com.acebook.dao;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;
import com.acebook.entities.WallPost;

@Repository
public class UserDaoHibernate implements UserDao {
	private final Logger log = Logger.getRootLogger();
	
	@Autowired
	private SessionFactory sf;
	
	@Transactional
	@Override
	public User save(User user) {
		log.trace("Saving user");
		sf.getCurrentSession().save(user);		
		return user;
	}

	@Transactional
	@Override
	public User persist(User user) {
		sf.getCurrentSession().persist(user);
		return user;
	}

	@Transactional
	@Override
	public User get(final int id) {
		return (User) sf.getCurrentSession().get(User.class, id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public User load(final int id) {
		return (User) sf.getCurrentSession().load(User.class, id);
	}

	@Transactional
	@Override
	public boolean delete(User user) {
		sf.getCurrentSession().delete(user);
		return true;
	}

	@Transactional
	@Override
	public User getUserByCredentials(Credentials credentials) {
		return (User) sf.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", credentials.getUsername()))
				.uniqueResult();
	}

	@Transactional
	@Override
	public User update(User user) {
		sf.getCurrentSession().update(user);
		return user;
	}

	@Transactional
	@Override
	public User merge(User user) {
		return (User) sf.getCurrentSession().merge(user);
	}

	@Transactional
	@Override
	public List<User> getFriends(User user) {
		// TODO Relies on implementation of friend join table
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Transactional
	@Override
	public Optional<User> getUserByUsername(String username) {
		return Optional.ofNullable((User) sf.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.uniqueResult());
	}

	@Transactional
	@Override
	public Optional<User> getUserByEmail(String email) {
		return Optional.ofNullable((User) sf.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult());
	}

	@Transactional
	@Override
	public List<WallPost> getWallPosts(User user) {
		return (List<WallPost>) sf.getCurrentSession().createCriteria(WallPost.class)
			.add(Restrictions.eq("owner", user))
			.addOrder(Order.desc("postTime")).list();
	}
	
	

}
