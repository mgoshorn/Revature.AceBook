package com.acebook.services;

import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acebook.beans.Credentials;
import com.acebook.dao.UserDao;
import com.acebook.entities.User;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = Logger.getRootLogger();
	
	@Autowired
	private UserDao dao;
	
	/**
	 * Authenticates a user given provided credentials
	 * @param credentials - User provided credentials
	 * @return Authenticated user instance, or null if authentication fails
	 */
	public final User authenticate(Credentials credentials) {
		log.trace("Authentication requested...");
		System.out.println(dao);
		Optional<User> optionalUser = dao.getUserByUsername(credentials.getUsername());

		// Return null when no user found
		if (!optionalUser.isPresent()) {
			log.trace("Authentication failed - No user with username");
			return null;
		}

		// Access user instance, extract stored hash
		User user = optionalUser.get();
		String storedHash = user.getHash();
		String providedHash = this.hash(credentials.getPassword(), user.getSalt());

		// Check if hashes match
		if (storedHash.equals(providedHash)) {
			log.trace("Authenticated");
			return user;
		} else {
			log.trace("Not authenticated - No hash match");
			return null;
		}
	}

	/**
	 * Helper method for {@link #authenticate(Credentials)}.
	 * Hashes a password, salt pair using the sha256 algorithm.
	 * @param password - user provided password
	 * @param salt - stored hash
	 * @return hashed String of pair
	 */
	private final String hash(String password, String salt) {
		return DigestUtils.sha256Hex(password + salt);
	}
}
