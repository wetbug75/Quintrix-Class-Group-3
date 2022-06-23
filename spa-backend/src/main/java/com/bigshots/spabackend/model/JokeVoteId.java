package com.bigshots.spabackend.model;

import java.io.Serializable;

public class JokeVoteId implements Serializable {
	private Users user;
	private Joke joke;
	
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
}
