package com.bigshots.spabackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.User;
import com.bigshots.spabackend.repo.JokeRepo;

@Service
public class JokeService {
	
	private JokeRepo jokeRepo;
	@Autowired
	public JokeService(JokeRepo jokeRepo) {
		this.jokeRepo = jokeRepo;
	}
	
	public List<Joke> getAllJokes(){
		return jokeRepo.findAll();
	}
	
	public void saveJson(List<Joke> jokes) {
		// TODO Auto-generated method stub
		jokeRepo.saveAll(jokes);
		
	}
	
	public long jokeCount() {
		return jokeRepo.count();
	}
	
	public List<Optional<Joke>> getPaginatedJokes(int pageNum, int jokesDisplayed) {
		List<Optional<Joke>> jokeList = new ArrayList<Optional<Joke>>();
		for(int i = 0; i < jokesDisplayed; i++)
		{
			jokeList.add(jokeRepo.findById((long) ((pageNum*jokesDisplayed) - jokesDisplayed + i)));
			System.out.println(jokeRepo.findById((long) ((pageNum*jokesDisplayed) - jokesDisplayed + i)).toString());
		}
		return jokeList; 
	}
	
	public void addJoke(Joke joke) {
		
		jokeRepo.save(joke);
	}
}
