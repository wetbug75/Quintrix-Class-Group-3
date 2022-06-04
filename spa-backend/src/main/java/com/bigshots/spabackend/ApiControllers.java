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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ApiControllers {
	
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	@Autowired
	private TheRepo theRepo;
	
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return theRepo.findAll();
	}
	
	/**
	 * return User of specified id
	 * @param user_id
	 * @return null if no such User id
	 */
	@GetMapping(value = "/users/{user_id}")
	public Optional<User> getUser(@PathVariable long user_id) {
		return theRepo.findById(user_id);
	}

	@PostMapping(value = "/users/save-new")
	public String saveUser(User user) {
		theRepo.save(user);
		return  "Saved: " + user.getUsername() + " as new user";
	}
	
	public String getRandomJoke() throws FileNotFoundException{
		
		Random random = new Random();
		int randomIndex = random.nextInt(100); //swap 100 for joke count later
		logger.info("get request a random joke");
		//jokeRepo.findById(randomIndex);
		
		return null;
	}
	
	@PostMapping("/newjoke" )
	public ResponseEntity<?> newJoke(@RequestBody(required = false) String question, @RequestBody(required = false) String answer) throws IOException {
		try
		{
			System.out.println("HELLO DEVIN , here's the info from the front end");
			System.out.println(question);
			System.out.println(answer);
			Path filePath = Path.of("/programming_jokes.txt");
			Files.writeString(filePath, question);
			Files.writeString(filePath, answer);
		
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public String getPage() {
		return "Welcome the the backend, try localhost:4200 if you'd like a UI";
	}
	
	/*@GetMapping(value = "/random-joke")
	public String getRandomJoke() throws FileNotFoundException{
		getJokeArray();

		if(jokeArray.length == 0)
			return "Sorry, something went wrong";
		
		Random random = new Random();
		int randomIndex = random.nextInt(jokeArray.length);
		logger.info("get request a random joke");
		return "Q: " + jokeArray[randomIndex][0] + "</n>A: " + jokeArray[randomIndex][1];
	}*/
	
	/**
	 * Gets the joke question at the specified index
	 * @param index location in the joke array you want to access
	 * @return
	 */
	/*@GetMapping(value = "/joke-question/{index}")
	public String getJokeQuestion(@PathVariable int index)
	{
		try {
			getJokeArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("An ERROR Message");
			e.printStackTrace();
		}
		
		logger.info("get request for question: " + index);
		return jokeArray[index][0];
	}*/
	
	/**
	 * Gets the joke answer at the specified index
	 * @param index location in the joke array you want to access
	 * @return
	 */
	/*@GetMapping(value = "/joke-answer/{index}")
	public String getJokeAnswer(@PathVariable int index)
	{
		try {
			getJokeArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("get request for answer: " + index);
		return jokeArray[index][1];
	}*/
	

}
