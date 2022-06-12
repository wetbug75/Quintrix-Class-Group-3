package com.bigshots.spabackend;

import java.io.File;  // Import the File class

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random; // Random number generator
import java.util.Scanner; // Import the Scanner class to read text files

import org.springframework.web.bind.annotation.*;


import com.bigshots.spabackend.model.*;
import com.bigshots.spabackend.service.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.User;
import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;

@RestController
public class ApiControllers {
	
	private Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JokeService jokeService;
	
	@GetMapping("/test")
	public String hi() {
		return "All fixed";
	}
	//Please refactor code . 
	//
	
	
	@GetMapping(value = "/jokes")
	public ResponseEntity<List<Joke>> getJokes() {
		return new ResponseEntity<>(jokeService.getAllJokes(), HttpStatus.OK);
		//return new ResponseEntity<>(jokeRepo.findAll(), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
		//return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/jokes/pagination/{page_size}/{page_num}")
	public ResponseEntity<List<Optional<Joke>>> getPaginatedJokes(@PathVariable int page_num, @PathVariable int page_size) {
		return new ResponseEntity<>(jokeService.getPaginatedJokes(page_num, page_size), HttpStatus.OK);
	}
	
	@GetMapping(value = "/jokeCount")
	public long getJokeCount() {
		return jokeService.jokeCount();
	}
	
	/*@GetMapping(value = "/jokes/{joke_id}")
	public ResponseEntity<Optional<Joke>> getJoke(@PathVariable long joke_id) {
		return new ResponseEntity<>(jokeRepo.findById(joke_id), HttpStatus.OK);
	}*/
	
	/**
	 * return User of specified id
	 * @param user_id
	 * @return null if no such User id
	 */
	
	/*
	@GetMapping(value = "/users/{user_id}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable long user_id) {
		return new ResponseEntity<>(userRepo.findById(user_id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public String getPage() {
		return "Welcome the the backend, try localhost:4200 if you'd like a UI";
	}
	
	*/

	/**
	 * 
	 * @param question
	 * @param answer
	 * @return
	 * @throws IOException
	 * 
	 * post method adds a new joke to the repository with optional question and answer strings 
	 * just in case the user wants to submit one line jokes 
	 */

	@PostMapping("/newJoke" )
	public ResponseEntity<?> newJoke(@RequestBody Joke joke) throws IOException {
		jokeService.addJoke(joke);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*@PostMapping(value = "/users/save-new")
	public String saveUser(User user) {
		//userRepo.save(user);
		//return  "Saved: " + user.getUsername() + " as new user";
	}*/

	@PostMapping("/newUser")
	public ResponseEntity<?> newUser(@RequestBody String userName, @RequestBody String email, @RequestBody String password) {
		userService.addUser(userName, email, password);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/*
	 * @PutMapping
	 * public ResponseEntity<Joke
	 */
	
}
