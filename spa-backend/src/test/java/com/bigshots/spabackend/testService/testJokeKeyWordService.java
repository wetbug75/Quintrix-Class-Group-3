package com.bigshots.spabackend.testService;





import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.JokeKeyword;
import com.bigshots.spabackend.repo.JokeKeywordRepo;
import com.bigshots.spabackend.repo.JokeRepo;
import com.bigshots.spabackend.service.JokeKeyWordService;

import reactor.core.publisher.Mono;
//tests work
@RunWith(SpringRunner.class)
@SpringBootTest
public class testJokeKeyWordService {
	@Autowired
	private JokeKeyWordService service;
	@Autowired
	private JokeKeywordRepo repo;
	@Autowired
	private JokeRepo jokeRepo;
	//testing to make sure that something that exists in the database returns a list of jokes
	@Test
	void testGetJokeByKeyword() {
		List<Optional<Joke>> keyWordJokeList = service.getJokeByKeyword("3707", 1, 6);
		assertNotNull(keyWordJokeList);
	}
	//if the thing searched does not exist the list should be empty
	@Test
	void testGetJokeByKeywordThatDoesntExist() {
		List<Optional<Joke>> list = service.getJokeByKeyword("150034578349", 1, 6);
		assertTrue(list.isEmpty());
	}
}
