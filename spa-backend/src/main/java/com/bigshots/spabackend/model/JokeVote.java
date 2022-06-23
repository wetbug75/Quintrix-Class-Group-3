package com.bigshots.spabackend.model;

import javax.persistence.*;
import com.bigshots.spabackend.service.VoteStatus;

@Entity
@IdClass(JokeVoteId.class)
public class JokeVote {
	@Id
	@ManyToOne
	private Users user;
	@Id
	@ManyToOne
	private Joke joke;
	@Column
	private VoteStatus voteStatus;

	public JokeVote() {
		System.out.println("JokeVote empty constructor");
	}
	public JokeVote(JokeVoteId jokeVoteId, VoteStatus voteStatus) {
		this(jokeVoteId.getUser(), jokeVoteId.getJoke(), voteStatus);
	}
	public JokeVote(Users user, Joke joke, VoteStatus voteStatus) {
		this.user = user;
		this.joke = joke;
		this.voteStatus = voteStatus;
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

	public VoteStatus getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(VoteStatus voteStatus) {
		this.voteStatus = voteStatus;
	}
}