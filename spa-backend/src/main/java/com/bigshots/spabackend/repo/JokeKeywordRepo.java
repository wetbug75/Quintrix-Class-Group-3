package com.bigshots.spabackend.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import com.bigshots.spabackend.model.JokeKeyword;

@Configuration
@EnableCosmosRepositories
@Repository
public interface JokeKeywordRepo extends CosmosRepository<JokeKeyword, Integer>{

}
