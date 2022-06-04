package com.bigshots.spabackend;

 import java.time.Instant;

import javax.persistence.*;

@Entity
public class User {
	
	
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
	private long created_at; //time stamp
	
	public Long getId() {
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

	public long getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	

}