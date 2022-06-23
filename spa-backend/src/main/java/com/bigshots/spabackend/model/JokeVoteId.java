package com.bigshots.spabackend.model;

import java.io.Serializable;
import java.util.Objects;

public class JokeVoteId implements Serializable {
	private Users user;
	private Joke joke;
	
	public JokeVoteId() {}
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
}
