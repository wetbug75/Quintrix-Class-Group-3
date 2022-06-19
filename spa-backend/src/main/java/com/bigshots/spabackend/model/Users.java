package com.bigshots.spabackend.model;

 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;
	@Column
	private boolean enabled = true;
	@Column
	private String created_at; //time stamp   "yyyy.MM.dd hh:mm:ss"
	// https://www.youtube.com/watch?v=CyWQVYLiupc
	public Users(){
		
	}
	public Users(String userName, String email, String password) {
		this.username = userName;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	//use user model in the restcontroller 
	
	//create post method where only users can increment like and dislike functions 
	//sub feature where they can only like or dislike the joke once 
	//may have to add an instance to (eventual) joke model that verifies if a specific user 
	//liked the joke already? (maybe)
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
