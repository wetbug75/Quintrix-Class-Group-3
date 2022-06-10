package com.bigshots.repo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigshots.model.Joke;

@Repository
@ComponentScan("com.bigshots.model")
public interface JokeRepo extends JpaRepository<Joke, Long>{

}
