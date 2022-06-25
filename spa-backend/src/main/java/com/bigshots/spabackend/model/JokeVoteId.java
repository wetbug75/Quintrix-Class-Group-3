package com.bigshots.spabackend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
@Embeddable
public class JokeVoteId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long jokeId;
	public JokeVoteId() {}
	
	public JokeVoteId(Users user, Joke joke){
		this.userId = user.getId();
		this.jokeId = joke.getId();
	}
	public JokeVoteId(Long userId,Long jokeId) {
		this.userId = userId;
		this.jokeId = jokeId;
	}
	
	public Long getUser() {
		return userId;
	}

	public void setUser(Long userId) {
		this.userId = userId;
	}

	public Long getJoke() {
		return jokeId;
	}

	public void setJoke(Long jokeId) {
		this.jokeId = jokeId;
	}
	
	
}
