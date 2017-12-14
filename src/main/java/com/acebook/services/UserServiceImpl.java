package com.acebook.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.acebook.beans.Credentials;
import com.acebook.beans.ProfilePhotoUpload;
import com.acebook.beans.SignUp;
import com.acebook.beans.UserImage;
import com.acebook.dao.UserDao;
import com.acebook.entities.ProfileImages;
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
	 * Wrapper method for {@link #authenticate(Credentials)} that throws an
	 * HttpClientErrorException as Unauthorized when authentication fails.
	 * @param credentials - credentials to authenticate
	 * @return - User represented by credentials
	 */
	public final User mustAuthenticate(Credentials credentials) {
		User user = authenticate(credentials);
		if(user == null) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
		return user;
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
		log.trace("In service signup...");

		try {
			log.trace("Creating new user instance");
			//Checks that the user input a valid date, throws exception if otherwise
			log.debug(signup);
			User user = new User(signup);
			
			//Non-null values
			notNull(signup);
			//Passwords meets specifications
			validPassword(signup);
			//Email meets specifications
			validEmail(signup);
			//Username is unique
			uniqueUsername(signup.getUsername());
			//Email is unique
			uniqueEmail(signup.getEmail());
			
			user.setSalt(generateSalt());
			user.setHash(hash(signup.getPassword(), user.getSalt()));
			return dao.save(user);
		}
		catch(DateTimeParseException e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Date");
		}
		
	}

	/**
	 * Generate a new salt String to use with a new account
	 * Generated String will have alphanumeric capitalized characters.
	 * @return salt string
	 */
	private String generateSalt() {
		log.trace("Generating salt..");
		Random rand = new Random(System.nanoTime());
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		String salt = "";
		for(int i = 0; i < 40; i++) {
			salt += str.charAt(rand.nextInt(str.length()));
		}
		
		return salt;
	}
	/**
	 * Checks that the user did not input null values into required fields, throws exception if otherwise
	 * @param signup
	 */
	private void notNull(SignUp signup) {
		log.trace("Validating data present...");
		if(signup.getUsername().equals("") || signup.getFirstName().equals("") 
		|| signup.getLastName().equals("") || signup.getEmail().equals("") 
		|| signup.getBirthdate().equals("") || signup.getPassword().equals(""))
		{
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cannot leave required fields blank");
		}
	}
	
	/**
	 * Checks that the inputed password meets required specifications, throws an error if otherwise
	 * @param signup
	 */
	private void validPassword(SignUp signup) {
		//Number Requirement
		log.trace("Validating password...");
		String pw = signup.getPassword();
		Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pw);
		boolean test = matcher.find();
		
		//Length Requirement
		if(pw.length() < 8) {
			test = false;
		}
		
		if(!test) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password does not meet specifications");
		}		
	}
	
	/**
	 * Checks that the inputed email meets standard email format, throws an error if otherwise
	 * @param signup
	 */
	private void validEmail(SignUp signup) {
		log.trace("Validating email...");
		String email = signup.getEmail();
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		boolean test = matcher.matches();
		
		if(!test) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Email");
		}
		
	}
	
	/**
	 * Checks that the inputed username does not already exist in the database, returns false if otherwise
	 * @param username
	 */
	public void uniqueUsername(String username) {
		log.trace("Validating username uniqueness...");
		if ((dao.getUserByUsername(username).isPresent())) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
	}
	
	/**
	 * Checks that the inputed email does not already exist in the database, returns false if otherwise
	 * @param email
	 */
	private void uniqueEmail(String email) {
		log.trace("Validating email uniqueness");
		if ((dao.getUserByEmail(email).isPresent())) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email already in use");
		}
		
	}

	@Override
	public User getUserById(int id) {
		return dao.get(id);
	}

	/**
	 * Gets the User with the given ID or throws exception.
	 * @param id - ID of requested User
	 * @return user instance
	 */
	@Override
	public User mustGetUserById(int id) {
		User user = dao.get(id);
		if(user == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Null target user");
		return user;
	}
	
	@Transactional
	public List<User> getFriends(int userId) {
		User user = dao.get(userId);
		if(user == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Null user requested");
		List<User> friends = user.getFriends();
		for(User friend : friends) {
			Hibernate.initialize(friend);
		}
		return friends;
	}

	@Override
	@Transactional
	public void updateProfilePhoto(ProfilePhotoUpload upload) {
		User user = mustAuthenticate(upload.getCredentials());
		log.trace("Setting user profile image data");
		
		log.trace(user.getImages());
		ProfileImages images = user.getImages();
		Hibernate.initialize(images);
		log.trace(images == null);
		if(images == null) {
			user.setImages(new ProfileImages());
			user.getImages().setUserId(user.getUser_id());
		}
		user.getImages().setProfileImage64(upload.getImage());
		user.getImages().setProfileImageType(upload.getFileType());
	}

	@Override
	public byte[] getProfileImage(int userId) throws IOException {
		User user = dao.get(userId);
		if(user == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Null user request");
		if(user.getImages() == null) throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "No such image");
		
		Hibernate.initialize(user.getImages());
		UserImage ui = new UserImage();
		
		ui.setImage(user.getImages().getProfileImage64());
		ui.setImageType(user.getImages().getProfileImageType());
		
		BufferedImage image = null;
		Decoder decoder = Base64.getDecoder();
		byte[] imageArray = decoder.decode(ui.getImage());
		return imageArray;
//		ByteArrayInputStream bis = new ByteArrayInputStream(imageArray);
//		image = ImageIO.read(bis);
//		bis.close();
//		return image;
//	
		
		
	}
	
	@Transactional
	public List<User> getFriendRequests(int userId) {
		User user = dao.get(userId);
		if(user == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Null user requested");
		List<User> friendRequests = user.getFriendRequests();
		for(User friendRequest : friendRequests)
			Hibernate.initialize(friendRequest);
		return friendRequests;
		
	}
	
}
