package com.bigshots.spabackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.JokeVote;
import com.bigshots.spabackend.model.JokeVoteId;
import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.repo.JokeVoteRepo;

@Service
public class JokeVoteService {

	private JokeVoteRepo jokeVoteRepo;
	
	@Autowired
	public JokeVoteService(JokeVoteRepo jokeVoteRepo) {
		this.jokeVoteRepo = jokeVoteRepo;
	}
	
	public Optional<JokeVote> getJokeVote(JokeVoteId jokeVoteId) {
		return jokeVoteRepo.findById(jokeVoteId);
	}
	
	public VoteStatus getJokeVoteStatus(JokeVoteId jokeVoteId) {
		if(jokeVoteRepo.findById(jokeVoteId) == null)
			return VoteStatus.NONE;
		return jokeVoteRepo.getById(jokeVoteId).getVoteStatus();
	}
	
	public void addJokeVote(JokeVote jokeVote) {
		JokeVoteId theId = new JokeVoteId(jokeVote.getUser(), jokeVote.getJoke());
		if(jokeVoteRepo.findById(theId) != null)
			modifyJokeVote(jokeVote.getUser(), jokeVote.getJoke(), jokeVote.getVoteStatus());
		else
			jokeVoteRepo.save(jokeVote);
	}

	public void modifyJokeVote(Users user, Joke joke, VoteStatus voteStatus) {
		JokeVoteId theId = new JokeVoteId(user, joke);
  
		//Check if JokeVoteId exists
		if(jokeVoteExists(theId) == true)
			jokeVoteRepo.findById(theId).get().setVoteStatus(voteStatus);
		else {
			JokeVote jokevoter = new JokeVote(user, joke, voteStatus);
			jokeVoteRepo.save(jokevoter);
		}
	}
	
	public boolean jokeVoteExists(JokeVoteId jokeVoteId)
	{
		if(jokeVoteRepo.findById(jokeVoteId).orElse(null) == null)
			return false;
		return true;
	}	
}
