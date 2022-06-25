package com.bigshots.spabackend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.bigshots.spabackend.service.VoteStatus;
@Embeddable
public class JokeVoteId implements Serializable {
	private Long userId;
	private Long jokeId;
	
	public JokeVoteId() {}
	
	public JokeVoteId(Users user, Joke joke){
		this.userId = user.getId();
		this.jokeId = joke.getId();
	}
	
	public JokeVoteId(JokeVote jokeVote) {
		this.userId = jokeVote.getUser().getId();
		this.jokeId = jokeVote.getJoke().getId();
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
