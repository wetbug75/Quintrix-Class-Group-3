package com.bigshots.spabackend.model;

import javax.persistence.*;
import com.bigshots.spabackend.service.VoteStatus;

@Entity
public class JokeVote {
	@EmbeddedId
	private JokeVoteId id = new JokeVoteId();
	@MapsId("userId")
	@ManyToOne
	private Users user = new Users();
	@MapsId("jokeId")
	@ManyToOne
	private Joke joke = new Joke();
	@Column
	private VoteStatus voteStatus;

	public JokeVote(Long user, Long id, VoteStatus voteStatus2) {
		this.user.setId(user);
		this.joke.setId(id);
		this.voteStatus = voteStatus2;
	}
	public JokeVote(JokeVoteId jokeVoteId, VoteStatus voteStatus) {
		this(jokeVoteId.getUser(), jokeVoteId.getJoke(), voteStatus);
	}
	public JokeVote(Users user, Joke joke, VoteStatus voteStatus) {
		this.user = user;
		this.joke = joke;
		this.voteStatus = voteStatus;
	}
	
	public JokeVote(Users user, Joke joke) {
		this.user = user;
		this.joke =joke;
	}
	
	public JokeVote() {
		
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