package com.bigshots.spabackend.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.JokeKeyword;
import com.bigshots.spabackend.repo.JokeKeywordRepo;
import com.bigshots.spabackend.repo.JokeRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JokeKeyWordService {
	@Autowired
	private JokeKeywordRepo jkr;
	@Autowired
	private JokeRepo jokeRepo;
	
	
	public void addJokeKeyword(JokeKeyword jk) {
		jkr.save(jk);
	}
	
	public Flux<JokeKeyword> findKeywords() {
		return jkr.findAll();
	}
	
	public Mono<JokeKeyword> findAKeyword(String id) {
		return jkr.findById(id);
	}
	
	public List<Optional<Joke>> getJokeByKeyword(String keywordHashCode, Integer pageNum, Integer jokesDisplayed){
		//if anyone knows how to use optionals , please refactor. thanks
		Mono<JokeKeyword> searchCosmos = jkr.findById(keywordHashCode);
		JokeKeyword retrievedFromCosmos = searchCosmos.block();
		List<Optional<Joke>> keywordJokes = new ArrayList<Optional<Joke>>();
		
		if(retrievedFromCosmos == null) {
			return keywordJokes; //empty array
		}else {
			int end = (pageNum * jokesDisplayed - 1);
			int start = (pageNum * jokesDisplayed ) - jokesDisplayed ;
			int maxSizeResult = retrievedFromCosmos.getJokeId().size();
			for (int i = start ; i <= end;i++) {
				if(i < maxSizeResult) {
					Optional<Joke> joke = jokeRepo.findById((long)retrievedFromCosmos.getJokeId().get(i));
					joke.get().setAuthor_name(joke.get().getAuthor().getUsername());
					keywordJokes.add(joke);
				}	
			}	
			return keywordJokes;
		}
		
	}
	
	public Integer getJokeByKeywordCount(String keywordHashCode) {		
		Mono<JokeKeyword> searchCosmos = jkr.findById(keywordHashCode);
		JokeKeyword retrievedFromCosmos = searchCosmos.block();
		if(retrievedFromCosmos == null) {
			//if it is not found in db
			return 0;
		}else {
			//found in db
			return retrievedFromCosmos.getJokeId().size();
		}
		
	}

}
