package com.bigshots.spabackend.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.Joke;
//User is the name of the model class in Devin local code not sure if the file was changed in main
import com.bigshots.spabackend.model.Users;
//import com.bigshots.spabackend.repo.JokeKeywordRepo;

import com.bigshots.spabackend.model.Users;

import com.bigshots.spabackend.repo.JokeRepo;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class JokeService {
	
	private JokeRepo jokeRepo;
	
	//private JokeKeywordRepo jkRepo;
	
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

	public Joke getOneJoke(Long indexID){
		System.out.println("Finding joke by ID:" + indexID);

		return jokeRepo.findById(indexID).get();
	}

	public Integer jokeCount() {
		return (int) jokeRepo.count();
	}
	
	public List<Optional<Joke>> getPaginatedJokes(int pageNum, int jokesDisplayed) {
		List<Optional<Joke>> jokeList = new ArrayList<Optional<Joke>>();
		for(int i = 0; i < jokesDisplayed; i++)
		{
			jokeList.add(jokeRepo.findById((long) ((pageNum*jokesDisplayed) - jokesDisplayed + i + 1)));
			System.out.println(jokeRepo.findById((long) ((pageNum*jokesDisplayed) - jokesDisplayed + i + 1)).toString());
		}
		return jokeList; 
	}
	
	public void addJoke(Joke joke) {
		jokeRepo.save(joke);
	}

	public void UpdateUpvote(Joke updateJoke, Long jokeID){
		System.out.println("Updating joke upvote.");
		System.out.println("Previous upvote: " + jokeRepo.findById(jokeID).get().getUpvotes().toString());
		jokeRepo.findById(jokeID).get().setUpvotes(updateJoke.getUpvotes());
		System.out.println("New upvote count: " + jokeRepo.findById(jokeID).get().getUpvotes().toString());
		jokeRepo.saveAndFlush(updateJoke);
	}

	public void UpdateDownvote(Joke updateJoke, Long jokeID){
		System.out.println("Updating joke downvote.");
		System.out.println("Previous downvote: " + jokeRepo.findById(jokeID).get().getDownvotes().toString());
		jokeRepo.findById(jokeID).get().setDislikes(updateJoke.getDownvotes());
		System.out.println("New downvote count: " + jokeRepo.findById(jokeID).get().getDownvotes().toString());
		jokeRepo.saveAndFlush(updateJoke);
	}

	public int GetUpvote(Long jokeID){
		return jokeRepo.findById(jokeID).get().getUpvotes();
	}
}
