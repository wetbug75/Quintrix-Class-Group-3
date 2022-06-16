package com.bigshots.spabackend.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.bigshots.spabackend.model.Joke;
//import com.bigshots.spabackend.model.Joke.jokeKeyword;

@Repository
public interface JokeRepo extends JpaRepository<Joke, Long>{
	Joke findById(Integer id);

}



