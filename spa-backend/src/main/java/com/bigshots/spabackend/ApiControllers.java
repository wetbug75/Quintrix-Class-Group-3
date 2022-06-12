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
	
	@GetMapping(value = "/jokes/find/{joke_id}")
	public ResponseEntity<Joke> getJoke(@PathVariable("joke_id") Integer joke_id) {
		Joke foundJoke = jokeService.getOneJoke(joke_id);
		if(foundJoke == null)
		{
			System.out.println(foundJoke);
		}
		return new ResponseEntity<>(foundJoke, HttpStatus.OK);
	}
	
	@GetMapping(value = "/jokeCount")
	public Integer getJokeCount() {
		return jokeService.jokeCount();
	}

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
	
	
	//connected with backend. check front end src/app/core/services folder for the post method. 
	@PostMapping("/newJoke" )
	public ResponseEntity<Joke> newJoke( @RequestBody Joke newJoke) throws IOException {
		
		System.out.println(newJoke.getAnswer());
		System.out.println(newJoke.getQuestion());
		System.out.println(newJoke.getDownvotes());
		
		return new ResponseEntity<>(newJoke, HttpStatus.OK);
	}
	/*
	@PostMapping(value = "/users/save-new")
	public String saveUser(User user) {
		//userRepo.save(user);
		//return  "Saved: " + user.getUsername() + " as new user";
	}

	@PostMapping("/newUser")
	public ResponseEntity<?> newUser(@RequestBody String userName, @RequestBody(required=false) String email, @RequestBody(required=false) String password) {
		//userService.addUser(userName, email, password);
		//return new ResponseEntity<>(HttpStatus.CREATED);
	}
	*/
}
