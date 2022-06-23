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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private JokeVoteService jokeVoteService;
	
	@GetMapping("/test")
	public String hi() {
		return "All fixed";
	}
	
	 @GetMapping(path = "/basicauth")
	    public AuthenticationBean basicauth(@RequestHeader(value="authorization") String token) {
		 System.out.println("Below is the token");
		 System.out.println(token);
	        return new AuthenticationBean("You are authenticated");
	    }
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
		Joke foundJoke = jokeService.getOneJoke(joke_id).get();
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

	@GetMapping(value = "/jokes/{jokeID}/upvote")
	public Integer GetUpvoteCount(@PathVariable Long jokeID){
		return jokeService.GetUpvote(jokeID);
		
	}

	@PutMapping(value = "/jokes/{jokeID}/update/upvote")
	public ResponseEntity<?> updateLike(@RequestBody Joke updateJoke, @PathVariable Long jokeID) throws IOException{
		jokeService.UpdateUpvote(updateJoke, jokeID);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/jokes/{jokeID}/update/downvote")
	public ResponseEntity<?> updateDislike(@RequestBody Joke updateJoke, @PathVariable Long jokeID) throws IOException{
		jokeService.UpdateDownvote(updateJoke, jokeID);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/newJoke" )
	public ResponseEntity<?> newJoke(@RequestBody Joke joke) throws IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authorName = auth.getName();
		jokeService.addJoke(joke, authorName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PostMapping("/newUser")
	public ResponseEntity<HttpStatus> newUser(@RequestBody Users user) {
		//public ResponseEntity<?> newUser(@RequestBody String userName, @RequestBody String email, @RequestBody String password) {   <---- previous implementation
		// userService.addUser(userName, email, password);  <--------- previous implementation
		int userCreate = userService.addUser(user);
		if(userCreate == 1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		}else if(userCreate == 2) {
			//email already exists in db
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); //400
		}else if (userCreate == 3) {
			//username already exists in db
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT); //409
		}else if (userCreate == 4) {
			return new ResponseEntity<HttpStatus>(HttpStatus.FAILED_DEPENDENCY); // 424
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED); //417
		
		
	}
	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody Users user) {
		System.out.println("hi there");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	

	@GetMapping("/keywords")
	public ResponseEntity<Flux<JokeKeyword>> getKeywords() {
		Flux<JokeKeyword> listOfKeywords = jokeKeyWordService.findKeywords();
		return new ResponseEntity<>(listOfKeywords, HttpStatus.OK);
	}
	//make it case insensitive
	@GetMapping("/jokesWith/{keyword}/{page}/{pageSize}")
	public ResponseEntity<Object> getJokeByKeyword(@PathVariable String keyword, @PathVariable int page, 
			@PathVariable int pageSize) {
		String lowerCasedKeyword = keyword.toLowerCase();
		System.out.println(lowerCasedKeyword);
		String keywordHashCode = Integer.toString(lowerCasedKeyword.hashCode());
		return new ResponseEntity<Object>(jokeKeyWordService.getJokeByKeyword(keywordHashCode,page,pageSize), HttpStatus.OK);
		
	}
	
	@GetMapping("/jokesWithKeywordCount/{keyword}")
	public ResponseEntity<Integer> keywordCount(@PathVariable String keyword) {
		String keywordHashCode = Integer.toString(keyword.hashCode());
		return new ResponseEntity<Integer>(jokeKeyWordService.getJokeByKeywordCount(keywordHashCode), HttpStatus.OK);
	}
	
	//TODO we shouldn't need to include {user_id} since we should have access to which user is currently logged in
	@GetMapping("/voteStatus/{user_id}/{joke_id}")
	public ResponseEntity<VoteStatus> getVoteStatus(@PathVariable long user_id, @PathVariable long joke_id) {
		Users user = (userService.findUserById(user_id)).orElse(null);
		Joke joke = (jokeService.getOneJoke(user_id)).orElse(null);
		if(user == null || joke == null)
			return new ResponseEntity<>(VoteStatus.NONE, HttpStatus.NO_CONTENT);
		JokeVoteId theId = new JokeVoteId(user, joke);
		if(!jokeVoteService.jokeVoteExists(theId))
			return new ResponseEntity<>(VoteStatus.NONE, HttpStatus.OK);
		return new ResponseEntity<>(jokeVoteService.getJokeVoteStatus(theId), HttpStatus.OK);
	}
	
	//adds new entries and modifies old ones
	@PostMapping("changeVoteStatus")
	public ResponseEntity<?> changeVoteStatus(@RequestBody JokeVote jokeVote) {
		jokeVoteService.modifyJokeVote(jokeVote);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	
}
