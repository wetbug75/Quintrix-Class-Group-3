package com.bigshots.spabackend.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.JokeKeyword;
import com.bigshots.spabackend.repo.JokeKeywordRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JokeKeyWordService {
	@Autowired
	private JokeKeywordRepo jkr;
	
	@Autowired
	public JokeKeyWordService(JokeKeywordRepo jkr) {
		this.jkr = jkr;
	}
	
	public void addJokeKeyword(JokeKeyword jk) {
		jkr.save(jk);
	}
	
	public Flux<JokeKeyword> findKeywords() {
		return jkr.findAll();
	}
	
	public Mono<JokeKeyword> findAKeyword(String id) {
		return jkr.findById(id);
	}
	

}
