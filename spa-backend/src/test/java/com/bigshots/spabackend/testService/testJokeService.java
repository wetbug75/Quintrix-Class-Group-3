package com.bigshots.spabackend.testService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.repo.JokeRepo;
import com.bigshots.spabackend.repo.UserRepo;
import com.bigshots.spabackend.service.JokeService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class testJokeService {
	@Mock
	JokeRepo repo;
	@Mock
	UserRepo userRepo;
	@InjectMocks
	JokeService service;
	
	@Test
	public void testAddJoke() {
		Joke joke = new Joke("hi", "bye");
		joke.setId((long) 200);
		service.addJoke(joke, "me");
		
		Optional<Joke> saved = repo.findById((long) 200);
		assertNotNull(saved);
	}
	

}
