package Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Model.Joke;

@Repository
public interface JokeRepo extends JpaRepository<Joke, Long>{

}
