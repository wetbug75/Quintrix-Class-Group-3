package com.bigshots.spabackend.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	public int addUser(Users user) {
		Optional<Users> findByEmail = userRepo.findByEmail(user.getEmail());
		Optional<Users> findByUsername = userRepo.findByUsername(user.getUsername());
		if(findByEmail.isEmpty() && findByUsername.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setCreated_at(LocalDateTime.now()
				       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			userRepo.save(user);
			return 1;
		}
		if(findByEmail.isPresent() && findByUsername.isPresent()) {
			return 4;
		}
		if(findByEmail.isPresent()) {
			return 2;
		}
		if(findByUsername.isPresent()) {
			return 3;
		}
		
		return 0; //nothing happens
	}

	public void saveJson(List<Users> users) {
		// TODO Auto-generated method stub
		userRepo.saveAll(users);
	}
	
	public Optional<Users> findUserById(Long id) {
		return userRepo.findById(id);
	}
	
	public long getUserIDByName(String username){
		Optional<Users> findByUserName = userRepo.findByUsername(username);
		System.out.println(findByUserName.get().getId());
		return findByUserName.get().getId();
	}
}
