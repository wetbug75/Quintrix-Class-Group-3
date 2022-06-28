package com.bigshots.spabackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.repo.JokeRepo;
import com.bigshots.spabackend.repo.UserRepo;
import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
	
	@InjectMocks
	JokeService jokeService;
	
	@InjectMocks
	UserService userService;
	
	@Mock
	JokeRepo jokeRepo;
	
	@Mock
	UserRepo userRepo;
	
	@Test
	public void testFindAllJokes()
	{
		List<Joke> list = new ArrayList<Joke>();
		Joke joke1 = new Joke("joke question","joke answer");
		Joke joke2 = new Joke("joke question2","joke answer2");
		Joke joke3 = new Joke("joke question3","joke answer3");
		
		list.add(joke1);
		list.add(joke2);
		list.add(joke3);
		
		when(jokeRepo.findAll()).thenReturn(list);
		
		List<Joke> jokeList = jokeService.getAllJokes();
		
		assertEquals(3, jokeList.size());
		verify(jokeRepo, times(1)).findAll();
	}
	
	@Test
    public void testCreateUser()
    {
        Users user = new Users("Andrew", "email@website.com", "password");
        
        userService.addUser(user);
        
        verify(userRepo, times(1)).save(user);
    }
	
	@Test
    public void testCreateJoke()
    {
		//have to make a user first to give the joke an author
		Users user = new Users("Author", "email@website.com", "password");
        
        userService.addUser(user);
		
        Joke joke = new Joke("test quesiton","test answer", user);
        
        jokeService.addJokeToCosmosAndMySQL(joke, "Author");
          
        verify(jokeRepo, times(1)).save(joke);
    }
}
