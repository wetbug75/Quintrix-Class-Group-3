package com.bigshots.spabackend.model;

import javax.persistence.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import java.util.ArrayList;

@Container(containerName = "jokeKeywords")
public class JokeKeyword {
	@Id
	private String id;
	@PartitionKey
	private String word;
	public ArrayList<Integer> jokeId = new ArrayList<Integer>(137);
	
	public JokeKeyword() {}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public JokeKeyword(String id, String word) {
		this.id = id;
		this.word = word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public ArrayList<Integer> getJokeId() {
		return jokeId;
	}
	
	public void setJokeId(ArrayList<Integer> jokeId) {
		this.jokeId = jokeId;
	}
	
	public String getWord() {
		return word;
	}
}