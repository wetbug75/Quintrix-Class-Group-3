package com.bigshots.model;

 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String userName;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private long created_at; //time stamp
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
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

	public long getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	
	//use user model in the restcontroller 
	
	//create post method where only users can increment like and dislike functions 
	//sub feature where they can only like or dislike the joke once 
	//may have to add an instance to (eventual) joke model that verifies if a specific user 
	//liked the joke already? (maybe)
	
	/*public User(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}*/
	
}
