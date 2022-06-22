package com.bigshots.spabackend.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.repo.UserRepo;

@Service
public class UserService {
	private UserRepo userRepo;
	
	@Autowired 
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<Users> getAllUsers(){
		return userRepo.findAll();
	}
	
	public void addUser(Users user) {
		//public void addUser(String userName, String email, String password) {  <--- previous implementation
		//	User newUser = new User(userName, email, password);  <--- previous implmenetation
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setCreated_at(LocalDateTime.now()
			       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		userRepo.save(user);
	}

	public void saveJson(List<Users> users) {
		// TODO Auto-generated method stub
		userRepo.saveAll(users);
	}
	
	public Optional<Users> findUserById(Long id) {
		return userRepo.findById(id);
	}
	
	

}
