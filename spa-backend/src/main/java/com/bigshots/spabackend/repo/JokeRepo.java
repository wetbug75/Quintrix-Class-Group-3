package com.bigshots.spabackend.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.bigshots.spabackend.model.Joke;
//import com.bigshots.spabackend.model.Joke.jokeKeyword;

@Repository
public interface JokeRepo extends JpaRepository<Joke, Long>{
	//@Repository
	//public interface JokeKeywordRepo extends ReactiveCosmosRepository<jokeKeyword, Integer> {}
	

}


/**
 * hashtable set up 
 * ID: id for the keyword 
 * key value pair: keyword, joke id array
 */

//query method 
/**
 * for every entry in mysql(by id)
 * take the question use the split method to separate into individual words into an array
 * loop through the array checking if the word exists in the dataase 
 * if it does exist , add joke id to existing entry
 * if it does not exist, create new entry and add joke id 
 */

