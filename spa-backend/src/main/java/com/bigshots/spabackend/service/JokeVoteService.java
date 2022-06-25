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
	
	public void modifyJokeVote(JokeVote jokeVote) {
		//joveVoteId takes in a user and joke. 
		
		JokeVoteId jokeVoteId = new JokeVoteId(jokeVote.getUser(), jokeVote.getJoke());
		
		if(jokeVoteRepo.existsById(jokeVoteId))
			jokeVoteRepo.deleteById(jokeVoteId);
		System.out.println("Below is the vote");
	    System.out.println(jokeVoteId.getJoke());
	    System.out.println(jokeVoteId.getUser());
	    System.out.println("------------");
		JokeVote newJokeVote = new JokeVote(jokeVoteId, jokeVote.getVoteStatus());
		System.out.println(newJokeVote.getUser().getId());
		System.out.println(newJokeVote.getJoke().getId());
		System.out.println(newJokeVote.getVoteStatus());
		System.out.println("-----+++____=+++");
	
		jokeVoteRepo.save(newJokeVote);
		System.out.println("save");
	}
	public void modifyJokeVote(JokeVoteId jokeVoteId, VoteStatus voteStatus) {
		if(jokeVoteRepo.existsById(jokeVoteId))
			jokeVoteRepo.deleteById(jokeVoteId);
		JokeVote newJokeVote = new JokeVote(jokeVoteId, voteStatus);
		System.out.println("Joke Id: " + newJokeVote.getJoke().getId());
		System.out.println("right before save");
		jokeVoteRepo.save(newJokeVote);//this is the problem
		System.out.println("right after save");
	}
	public void modifyJokeVote(Users user, Joke joke, VoteStatus voteStatus) {
		JokeVoteId JokeVote = new JokeVoteId(user, joke);
		if(jokeVoteRepo.existsById(JokeVote))
			jokeVoteRepo.deleteById(JokeVote);
		JokeVote newJokeVote = new JokeVote(JokeVote, voteStatus);
		jokeVoteRepo.save(newJokeVote);
	}
	// please delete this temporary solution later
	public void modifyJokeVote(Users user, Joke joke, String voteStatus) {
		System.out.println("AM I EVEN GETTING HERE?");
		JokeVoteId theId = new JokeVoteId(user, joke);
		if(jokeVoteRepo.existsById(theId))
			jokeVoteRepo.deleteById(theId);
		VoteStatus realStatus = VoteStatus.NONE;
		if(voteStatus == "0")
			realStatus = VoteStatus.UPVOTE;
		else if(voteStatus == "1")
			realStatus = VoteStatus.DOWNVOTE;
		JokeVote newJokeVote = new JokeVote(theId, realStatus);
		jokeVoteRepo.save(newJokeVote);
	}
	
	public boolean jokeVoteExists(JokeVote jokeVote)
	{
		JokeVoteId jokeVoteId = new JokeVoteId(jokeVote.getUser(), jokeVote.getJoke());
		return jokeVoteExists(jokeVoteId);
	}
	public boolean jokeVoteExists(JokeVoteId jokeVoteId)
	{
		if(jokeVoteRepo.findById(jokeVoteId).orElse(null) == null)
			return false;
		return true;
	}
	
}
