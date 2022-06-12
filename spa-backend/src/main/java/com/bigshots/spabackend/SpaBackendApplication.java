package com.bigshots.spabackend;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CorsFilter;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.User;
import com.bigshots.spabackend.repo.JokeRepo;
import com.bigshots.spabackend.repo.UserRepo;
import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpaBackendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpaBackendApplication.class, args);
	}
	
	//function that will insert all the data from JSON files to Database.
	//JSON files located in resources folder. 
	//comment this function out if you do not need. 
	//JAVA 1.8 
	/*@Bean
	CommandLineRunner runner(UserService userService, JokeService jokeService) {
		  return args -> {ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<User>> typeReferenceUser = new TypeReference<List<User>>() {};
		InputStream inputStreamUser = TypeReference.class.getResourceAsStream("/json/user.json");
		
		TypeReference<List<Joke>> typeReferenceJoke = new TypeReference<List<Joke>>() {};
		InputStream inputStreamJoke = TypeReference.class.getResourceAsStream("/json/joke.json");

		try {
			List<User> users = mapper.readValue(inputStreamUser,typeReferenceUser);
			List<Joke> jokes = mapper.readValue(inputStreamJoke,typeReferenceJoke);
			userService.saveJson(users);
			jokeService.saveJson(jokes);
			System.out.println("Users Saved!");
			System.out.println("Jokes saved!");
		} catch (IOException e){
			System.out.println("Unable to save users: " + e.getMessage());
			System.out.println("Unable to save jokes: " + e.getMessage());
		}
		  };
	}*/
}
