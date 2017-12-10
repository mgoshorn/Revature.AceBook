package com.acebook.dao;

import java.util.List;
import java.util.Optional;

import com.acebook.beans.Credentials;
import com.acebook.entities.User;
import com.acebook.entities.WallPost;

/**
 * Interface defines basic behavior of a UserDao implementation
 * @author Mitchell Goshorn
 *
 */
public interface UserDao {
	
	/*
	 * Hibernate methods here modeled after in class methods - actual
	 * implementation in this style is likely not necessary given that 
	 * proxies and persistent references have no opportunity to be acted on.
	 * 
	 * As such these methods may be subject to refactoring.
	 */
	
	User save(User user);
	User persist(User user);
	
	User get(int id);
	User load(int id);
	
	User update(User user);
	User merge(User user);
	
	boolean delete(User user);
	
	/**
	 * Gets a User object given provided Credentials instance
	 * @param credentials - User provided authentication details
	 * @return 
	 */
	User getUserByCredentials(Credentials credentials);
	
	/**
	 * Optionally retrieves a User object from database with provided username.
	 * @param username Username to search for
	 * @return Optional User
	 */
	Optional<User> getUserByUsername(String username);
	
	/**
	 * Retrieves the list of User's friends
	 * @param user User whose friends are to be searched for
	 * @return List of user's friends. This list will be empty if no friends are found.
	 */
	List<User> getFriends(User user);
	
	/**
	 * Optionally retrieves a User object from database with provided email
	 * @param email
	 * @return Optional User
	 */
	Optional<User> getUserByEmail(String email);
	
	List<WallPost> getWallPosts(User user);
	
}
