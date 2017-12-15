package com.acebook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.beans.Credentials;
import com.acebook.comparators.FeedPostComparator;
import com.acebook.entities.User;
import com.acebook.entities.WallPost;

@Service
public class FeedService {

	private static final Logger log = Logger.getRootLogger();
	
	@Autowired
	private UserService us;
	
	@Transactional
	public List<WallPost> getFeed(Credentials credentials) {
		User user = us.mustAuthenticate(credentials);
		List<User> users = new ArrayList<User>(user.getFriends());
		users.add(user);
		Hibernate.initialize(users);
		
		return users.stream()
				.flatMap(u -> u.getWallPosts().stream())
				.distinct()
				.sequential()
				.sorted(new FeedPostComparator())
				.collect(Collectors.toList());
		
	}

}
