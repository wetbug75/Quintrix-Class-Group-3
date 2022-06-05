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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	
	private String[][] jokeArray; // jokeArray[...][0] is the Question, jokeArray[...][1] is the Answer
	
	@GetMapping(value = "/")
	public String getPage() {
		return "Welcome";
	}
	
	@GetMapping(value = "/random-joke")
	public String getRandomJoke() throws FileNotFoundException{
		getJokeArray();

		if(jokeArray.length == 0)
			return "Sorry, something went wrong";
		
		Random random = new Random();
		int randomIndex = random.nextInt(jokeArray.length);
		return "Q: " + jokeArray[randomIndex][0] + "</n>A: " + jokeArray[randomIndex][1];
	}
	
	/**
	 * Gets the joke question at the specified index
	 * @param index location in the joke array you want to access
	 * @return
	 */
	@GetMapping(value = "/joke-question/{index}")
	public String getJokeQuestion(@PathVariable int index)
	{
		return jokeArray[index][0];
	}
	
	/**
	 * Gets the joke answer at the specified index
	 * @param index location in the joke array you want to access
	 * @return
	 */
	@GetMapping(value = "/joke-answer/{index}")
	public String getJokeAnswer(@PathVariable int index)
	{
		return jokeArray[index][1];
	}
	
	/**
	 * Turn the joke .txt file into a 2d array of strings, necessary for the other functions to work
	 * @throws FileNotFoundException
	 */
	public void getJokeArray() throws FileNotFoundException
	{
		ArrayList<String> list = new ArrayList<String>();
		int count = 0;
		String jokeQuestion = "";
		String jokeAnswer = "";
		File text = new File("src/main/resources/programming_jokes.txt");
		Scanner myReader = new Scanner(text);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if(data.contains("**Q:**"))
			{
				jokeQuestion = data;
				//jokeQuestion.replaceFirst("\\*\\*Q:\\*\\*", ""); IDK why this doesn't work
				jokeQuestion = jokeQuestion.substring(6);
				jokeQuestion.trim();
			}
			else if(data.contains("**A:**"))
			{
				jokeAnswer = data;
				//jokeAnswer.replaceFirst("\\*\\*A:\\*\\*", "");
				jokeAnswer = jokeAnswer.substring(6);
				jokeAnswer.trim();
			}
			else if(data.equals("---"))//this bit is unnecessary if the .txt file is formatted correctly
			{
				jokeQuestion = "";
				jokeAnswer = "";
			}
			
			if(jokeQuestion != "" && jokeAnswer != "")
			{
				count++;
				list.add(jokeQuestion);
				list.add(jokeAnswer);
				jokeQuestion = "";
				jokeAnswer = "";
			}
	  	}
	  	myReader.close();
		
	  	String[][] jokeArr = new String[count][2];
	  	for(int i = 0; i < count; i++)
	  	{
	  		jokeArr[i][0] = list.get(i*2);
	  		jokeArr[i][1] = list.get((i*2) + 1);
	  	}
	  	
	  	jokeArray = jokeArr;
	}
	
}
