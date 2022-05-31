package com.bigshots.spabackend.Post;



import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PostController {
	@CrossOrigin
	@PostMapping("/newjoke" )
	public ResponseEntity<?> newJoke(@RequestBody(required = false) String question, @RequestBody(required = false) String answer) throws IOException {
		
		
		
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
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
