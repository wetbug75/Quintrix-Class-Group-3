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
	/* 
	public void modifyJokeVote(JokeVote jokeVote) {
		JokeVoteId jokeVoteId = new JokeVoteId(jokeVote.getUser(), jokeVote.getJoke());
		jokeVoteRepo.deleteById(jokeVoteId);
		JokeVote newJokeVote = new JokeVote(jokeVoteId, jokeVote.getVoteStatus());
		jokeVoteRepo.save(newJokeVote);
	}
	public void modifyJokeVote(JokeVoteId jokeVoteId, VoteStatus voteStatus) {
		jokeVoteRepo.deleteById(jokeVoteId);
		JokeVote newJokeVote = new JokeVote(jokeVoteId, voteStatus);
		jokeVoteRepo.save(newJokeVote);
	}
	*/
	public void modifyJokeVote(Users user, Joke joke, VoteStatus voteStatus) {
		JokeVoteId theId = new JokeVoteId(user, joke);
		//Check if JokeVoteId exists
		if(jokeVoteExists(theId) == true) {
			System.out.println("Joke Vote ID found!");
			//jokeVoteRepo.deleteById(theId);
			jokeVoteRepo.findById(theId).get().setVoteStatus(voteStatus);
			
			//JokeVote newJokeVote = new JokeVote(theId, voteStatus);
			//jokeVoteRepo.save(newJokeVote);
		} else {
			System.out.println("No joke vote ID found. Creating a new one.");
			JokeVote jokevoter = new JokeVote(user, joke, voteStatus);
			jokeVoteRepo.save(jokevoter);
		}
	}
	/* 
	public boolean jokeVoteExists(JokeVote jokeVote)
	{
		JokeVoteId jokeVoteId = new JokeVoteId(jokeVote.getUser(), jokeVote.getJoke());
		return jokeVoteExists(jokeVoteId);
	}
	*/
	public boolean jokeVoteExists(JokeVoteId jokeVoteId)
	{
		if(jokeVoteRepo.findById(jokeVoteId).orElse(null) == null)
			return false;
		return true;
	}	
}
