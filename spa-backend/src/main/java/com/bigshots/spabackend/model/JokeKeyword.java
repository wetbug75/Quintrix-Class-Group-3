package com.bigshots.spabackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Container(containerName = "jokeKeywords")
public class JokeKeyword {
	@Id
	private Integer id;
	@PartitionKey
	private String word;
	private Integer[] jokeId;
	
	public JokeKeyword(String word, Integer[] jokeId) {
		this.word = word;
		this.jokeId = jokeId;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public Integer[] getJokeId() {
		return jokeId;
	}

	public void setJokeId(Integer[] jokeId) {
		this.jokeId = jokeId;
	}
	
	public String getWord() {
		return word;
	}
}