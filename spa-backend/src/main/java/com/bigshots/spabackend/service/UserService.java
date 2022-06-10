package com.bigshots.spabackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.User;
import com.bigshots.spabackend.repo.UserRepo;

@Service
public class UserService {
	private UserRepo userRepo;
	
	@Autowired 
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	/*
	public void addUser(String userName, String email, String password) {
		User newUser = new User(userName, email, password);
		userRepo.save(newUser);
	}*/

	public void saveJson(List<User> users) {
		// TODO Auto-generated method stub
		userRepo.saveAll(users);
		
	}

}
