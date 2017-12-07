package com.acebook.services;

import java.util.Optional;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.beans.Credentials;
import com.acebook.beans.SignUp;
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

	@Transactional
	@Override
	public User signup(SignUp signup) {
		//TODO meets password standards?
		//TODO username/email unique?
		User user = new User(signup);
		user.setSalt(generateSalt());
		user.setHash(hash(signup.getPassword(), user.getSalt()));
		return dao.save(user);
	}

	/**
	 * Generate a new salt String to use with a new account
	 * Generated String will have alphanumeric capitalized characters.
	 * @return salt string
	 */
	private String generateSalt() {
		Random rand = new Random(System.nanoTime());
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		String salt = "";
		for(int i = 0; i < 40; i++) {
			salt += str.charAt(rand.nextInt(str.length()));
		}
		
		return salt;
	}
}
