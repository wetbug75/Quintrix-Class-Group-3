package com.bigshots.spabackend.model;
import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class JokeVoteId implements Serializable {
	/* 
	private Users user;
	private Joke joke;
	


	public JokeVoteId(){ }

	public JokeVoteId(Users user, Joke joke) {
		this.user = user;
		this.joke = joke;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Joke getJoke() {
		return joke;
	}

	public void setJoke(Joke joke) {
		this.joke = joke;
	}
	*/

	private Long userId;
	private Long jokeId;

	public JokeVoteId() { }

	public JokeVoteId(Users user, Joke joke) { 
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
