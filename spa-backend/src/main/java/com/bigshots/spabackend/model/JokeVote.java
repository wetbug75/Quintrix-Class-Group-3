package com.bigshots.spabackend.model;

import javax.persistence.*;
import com.bigshots.spabackend.service.VoteStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("vote_status")
	@Column
	private VoteStatus voteStatus;


	public JokeVote() { }

	public JokeVote(Long user, Long joke, VoteStatus voteStatus) {
		this.user.setId(user);
		this.joke.setId(joke);
		this.voteStatus = voteStatus;
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