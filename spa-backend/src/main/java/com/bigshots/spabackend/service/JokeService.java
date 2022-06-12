package com.bigshots.spabackend.service;

import java.util.List;

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

	public Joke getOneJoke(Integer indexID){
		System.out.println("Finding joke by ID:" + indexID);

		return jokeRepo.findById(indexID).get();
	}

	public Integer jokeCount() {
		return (int) jokeRepo.count();
	}
}
