package com.bigshots.service;

import org.springframework.stereotype.Service;

import com.bigshots.model.User;
import com.bigshots.repo.UserRepo;

@Service
public class UserService {
	private UserRepo userRepo;
	
	public void addUser(String userName, String email, String password) {
		/*User newUser = new User(userName, email, password);
		userRepo.save(newUser);*/
	}

}
