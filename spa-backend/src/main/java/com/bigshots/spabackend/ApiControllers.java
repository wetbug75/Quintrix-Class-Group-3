package com.bigshots.spabackend;

import java.io.File;  // Import the File class



import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random; // Random number generator
import java.util.Scanner; // Import the Scanner class to read text files

import org.springframework.web.bind.annotation.*;



import Model.Joke;
import Service.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ApiControllers {
	private UserService userService;

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
	public ResponseEntity<Joke> newJoke(@RequestBody(required = false) String question, @RequestBody(required = false) String answer) throws IOException {
		Joke newJoke = new Joke(question, answer);
	
	
		try
		{
			
			Path filePath = Path.of("/programming_jokes.txt");
			Files.writeString(filePath, question);
			Files.writeString(filePath, answer);
		
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
		
		return new ResponseEntity<>(newJoke, HttpStatus.OK);
	}
	
	/**
	 * user registration post method 
	 * every user will be registered with userName, email & password 
	 * 
	 * and then saved to the user repository for verification with other functions of
	 * the app
	 */
	@PostMapping("/newUser")
	public ResponseEntity<?> newUser(@RequestBody String userName, @RequestBody(required=false) String email, 
			@RequestBody(required=false) String password) {
		userService.addUser(userName, email, password);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}