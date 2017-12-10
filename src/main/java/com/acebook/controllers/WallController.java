package com.acebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acebook.beans.PostRequest;
import com.acebook.entities.WallPost;
import com.acebook.services.WallService;

@RestController
@RequestMapping("wall")
public class WallController {

	@Autowired
	WallService ws;
	
	@PostMapping("post/{userId}")
	public WallPost requestWallPost(@PathVariable("userId") int userId,
						@RequestBody PostRequest postRequest) {
		
		ws.wallPost(userId, postRequest);
		
		return null;
	}

}
