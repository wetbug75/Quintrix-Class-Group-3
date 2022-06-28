package com.bigshots.spabackend.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.CosmosBuilder;
import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.JokeKeyword;
//User is the name of the model class in Devin local code not sure if the file was changed in main
import com.bigshots.spabackend.model.Users;
//import com.bigshots.spabackend.repo.JokeKeywordRepo;

import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.repo.JokeKeywordRepo;
import com.bigshots.spabackend.repo.JokeRepo;
import com.bigshots.spabackend.repo.UserRepo;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class JokeService {
	@Autowired
	private JokeRepo jokeRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JokeKeywordRepo jkRepo;
	

	
	public List<Joke> getAllJokes(){
		return jokeRepo.findAll();
	}
	
	public void saveJson(List<Joke> jokes) {
		// TODO Auto-generated method stub
		jokeRepo.saveAll(jokes);
		
	}

	public Optional<Joke> getOneJoke(Long indexID){
		return jokeRepo.findById(indexID);
	}

	public Integer jokeCount() {
		return (int) jokeRepo.count();
	}
	
	//TODO if a joke is deleted, there will be an empty space in the front end
	public List<Optional<Joke>> getPaginatedJokes(int pageNum, int jokesDisplayed) {
		List<Optional<Joke>> jokeList = new ArrayList<Optional<Joke>>();
		for(int i = 0; i < jokesDisplayed; i++)
		{
			Optional<Joke> joke = jokeRepo.findById((long) ((pageNum*jokesDisplayed) - jokesDisplayed + i + 1));
			if(joke.isPresent()) {
				joke.get().setAuthor_name(joke.get().getAuthor().getUsername());
			}
			
			jokeList.add(joke);
			System.out.println(jokeRepo.findById((long) ((pageNum*jokesDisplayed) - jokesDisplayed + i + 1)).toString());
		}
		return jokeList; 
	}
	
	public void addJokeToCosmosAndMySQL(Joke joke, String authorName) {
		addJokeToMySQL(joke, authorName);
		addJokeToCosmos(joke, Integer.valueOf(joke.getId().intValue()));
	}
	
	private void addJokeToMySQL(Joke joke, String authorName) {
		joke.setAuthor(userRepo.findByUsername(authorName).get());
		joke.setDownvotes(0);
		joke.setUpvotes(0);
		joke.setCreated_at(LocalDateTime.now()
			       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		jokeRepo.save(joke);
	}
	
	private void addJokeToCosmos(Joke joke, Integer jokeId) {
		CosmosBuilder cosmos = new CosmosBuilder();
		cosmos.writeToCosmos(joke, jokeId);
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
		//System.out.println("New downvote count: " + jokeRepo.findById(jokeID).get().getDownvotes().toString());
		jokeRepo.saveAndFlush(updateJoke);
	}
	
	public void upvoteOnce(Long jokeId) {
		Joke joke = jokeRepo.findById(jokeId).get();
		joke.setUpvotes(joke.getUpvotes() + 1);
		jokeRepo.save(joke);
	}
	
	public void downvoteOnce(Long jokeId) {
		Joke joke = jokeRepo.findById(jokeId).get();
		joke.setDownvotes(joke.getDownvotes() + 1);
		jokeRepo.save(joke);
	}
	
	public void removeOneUpvote(Long jokeId) {
		Joke joke = jokeRepo.findById(jokeId).get();
		if(joke.getUpvotes() > 0)
		{
			joke.setUpvotes(joke.getUpvotes() - 1);
			jokeRepo.save(joke);
		}
	}
	
	public void removeOneDownvote(Long jokeId) {
		Joke joke = jokeRepo.findById(jokeId).get();
		if(joke.getDownvotes() > 0)
		{
			joke.setDownvotes(joke.getDownvotes() - 1);
			jokeRepo.save(joke);
		}
	}

	public int GetUpvote(Long jokeID){
		return jokeRepo.findById(jokeID).get().getUpvotes();
	}

	public Joke findById(Integer integer) {
		return jokeRepo.findById((long)integer).orElse(null);
	}
	
	public Optional<Joke> findById(Long thelong) {
		System.out.println(jokeRepo.findById(thelong).orElse(null).getAnswer());
		return jokeRepo.findById(thelong);
	}
}
