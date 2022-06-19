package com.bigshots.spabackend;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bigshots.spabackend.model.JokeKeyword;
import com.bigshots.spabackend.repo.JokeKeywordRepo;

import reactor.core.publisher.Mono;
@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordRepoTest {
	
	@Autowired 
	JokeKeywordRepo jkr;
	
	@Test
	public void test() {
		Mono<JokeKeyword> jk = jkr.findById("1");
		
	}

}
