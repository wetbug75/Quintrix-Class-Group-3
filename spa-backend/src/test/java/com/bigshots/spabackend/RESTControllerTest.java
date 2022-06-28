package com.bigshots.spabackend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.bigshots.spabackend.controller.ApiControllers;
import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiControllers.class)
public class RESTControllerTest {

	@MockBean
	JokeService jokeService;
	
	@MockBean
	UserService userService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testFindAllJokes() throws Exception {
		
		Users user = new Users("Username", "email@website.com", "password");
		Joke joke = new Joke("joke question", "joke answer", user);
		List<Joke> jokeList = Arrays.asList(joke);
		
		Mockito.when(jokeService.getAllJokes()).thenReturn(jokeList);
		
		mockMvc.perform(get("/jokes")).andExpect(jsonPath("$[0].answer", Matchers.is("joke answer")))
        	.andExpect(status().isOk());
        	//.andExpect(jsonPath("$", Matchers.hasSize(1)));
        	
	}
	
}
