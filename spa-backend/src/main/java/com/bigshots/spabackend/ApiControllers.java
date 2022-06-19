package com.bigshots.spabackend;

import java.io.File;  // Import the File class

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.InputStream;
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

import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApiControllers {
	
	private Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JokeService jokeService;
	
	@Autowired
	private JokeKeyWordService jokeKeyWordService;
	
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
	public ResponseEntity<List<Users>> getUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
		//return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
	}
	

	@GetMapping(value = "/jokes/find/{joke_id}")
	public ResponseEntity<Joke> getJoke(@PathVariable("joke_id") Long joke_id) {
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


	@GetMapping(value = "/jokes/pagination/{page_size}/{page_num}")
	public ResponseEntity<List<Optional<Joke>>> getPaginatedJokes(@PathVariable int page_num, @PathVariable int page_size) {
		return new ResponseEntity<>(jokeService.getPaginatedJokes(page_num, page_size), HttpStatus.OK);
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
	public ResponseEntity<?> newUser(@RequestBody Users user) {
		//public ResponseEntity<?> newUser(@RequestBody String userName, @RequestBody String email, @RequestBody String password) {   <---- previous implementation
		// userService.addUser(userName, email, password);  <--------- previous implementation
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody Users user) {
		//public ResponseEntity<?> newUser(@RequestBody String userName, @RequestBody String email, @RequestBody String password) {   <---- previous implementation
		// userService.addUser(userName, email, password);  <--------- previous implementation
		System.out.println("hi there");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	/*
	 * @PutMapping
	 * public ResponseEntity<Joke
	 */
	
	@GetMapping("/keywords")
	public ResponseEntity<Flux<JokeKeyword>> getKeywords() {
		Flux<JokeKeyword> listOfKeywords = jokeKeyWordService.findKeywords();
		return new ResponseEntity<>(listOfKeywords, HttpStatus.OK);
	}
	
	@GetMapping("/jokesWith/{keyword}")
	public ResponseEntity<List<Joke>> getJokeByKeyword(@PathVariable String keyword) {
		//hash the keyword 
		String str = Integer.toString(keyword.toString().hashCode());
		
		//check cosmos repository for this hash
		Mono<JokeKeyword> search = jokeKeyWordService.findAKeyword(str);
		//if it exists, 
		
		JokeKeyword got = search.block();
		List<Joke> keywordJokes = new ArrayList<Joke>();
		for(Integer id : got.jokeId) {
			Joke joke = jokeService.findThisJoke(id);
			keywordJokes.add(joke);
		}
		return new ResponseEntity<>(keywordJokes, HttpStatus.OK);
		
	}
	
	
}
