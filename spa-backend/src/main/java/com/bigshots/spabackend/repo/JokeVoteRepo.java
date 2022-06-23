package com.bigshots.spabackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigshots.spabackend.model.JokeVote;
import com.bigshots.spabackend.model.JokeVoteId;

@Repository
public interface JokeVoteRepo extends JpaRepository<JokeVote, JokeVoteId>{

}
