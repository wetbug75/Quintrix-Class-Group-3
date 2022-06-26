package com.bigshots.spabackend.model;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class JokeVoteId implements Serializable {

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
  /*
	//below 2 overrides might not be necessary
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokeVoteId jvId = (JokeVoteId) o;
        return user.equals(jvId.user) &&
                joke.equals(jvId.joke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, joke);
    }
    */
}
