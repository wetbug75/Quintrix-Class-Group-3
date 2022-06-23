package com.bigshots.spabackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.service.JokeService;

@Component
public class LongToJokeConverter implements Converter<Long, Joke>{

	//@Autowired
	JokeService jokeService;
	
	@Override
	public Joke convert(Long source) {
		return jokeService.findById(source).orElse(null);
	}

}
