package com.bigshots.spabackend.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigshots.spabackend.model.Joke;

@Repository
public interface JokeRepo extends JpaRepository<Joke, Integer>{

}
