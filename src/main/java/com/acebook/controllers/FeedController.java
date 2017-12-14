package com.acebook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acebook.beans.Credentials;
import com.acebook.entities.WallPost;
import com.acebook.services.FeedService;

@RestController
@RequestMapping("feed")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class FeedController {
	
	@Autowired
	private FeedService fs;
	
	@PostMapping("")
	public List<WallPost> handleFeedRequest(@RequestBody Credentials credentials) {
		return fs.getFeed(credentials);
	}
	
}
