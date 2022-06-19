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
	

 //two methods that find jokes by id didnt want to delete either because theyre tied to different files 
  //but watch for it if it creates errors in running the file 
	public Joke findThisJoke(Integer id) {
		Joke thisOne = jokeRepo.findById(id);
		return thisOne;
	}
	

	public Optional<Joke> findJokeById(Long id){
		return jokeRepo.findById(id);
	}
	
	

}
