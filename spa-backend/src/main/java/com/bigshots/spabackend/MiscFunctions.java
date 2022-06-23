package com.bigshots.spabackend;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MiscFunctions {
	@Autowired
	private UserService userService;
	@Autowired
	private JokeService jokeService;
	public void jsonToDB() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			if(userService.findUserById((long) 2).isEmpty()) {
				TypeReference<List<Users>> typeReferenceUser = new TypeReference<List<Users>>() {};
				InputStream inputStreamUser = TypeReference.class.getResourceAsStream("/json/user.json");
				List<Users> users = mapper.readValue(inputStreamUser,typeReferenceUser);
				userService.saveJson(users);
				System.out.println("Users Saved!");
				
			}else {
				System.out.println("Users in DB already, no import needed");
			}
			
			if(jokeService.getOneJoke((long)2).isEmpty()) {
				TypeReference<List<Joke>> typeReferenceJoke = new TypeReference<List<Joke>>() {};
				InputStream inputStreamJoke = TypeReference.class.getResourceAsStream("/json/joke.json");
				List<Joke> jokes = mapper.readValue(inputStreamJoke,typeReferenceJoke);
				jokeService.saveJson(jokes);
				System.out.println("Jokes saved!");
			}else {
				System.out.println("Jokes in DB already, no import needed");
			}
			
		} catch (IOException e){
			System.out.println("Unable to save users: " + e.getMessage());
			System.out.println("Unable to save jokes: " + e.getMessage());
		}
	}
	
	public void bcryptexample() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "creator";
		String encodedPassword = encoder.encode(rawPassword);
		Users user = new Users("creator", "email", encodedPassword);
		user.setPassword(encodedPassword);
		user.setUsername("creator");
		user.setId((long) 2);
		user.setEnabled(true);
		userService.addUser(user);
		System.out.println("overwritten id 1 user, with user: creator, password: creator");
	}
}
