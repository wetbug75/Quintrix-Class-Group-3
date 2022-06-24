package com.bigshots.spabackend.repo;



import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.bigshots.spabackend.model.JokeKeyword;



@Repository
public interface JokeKeywordRepo extends ReactiveCosmosRepository<JokeKeyword, String>{
	
}
