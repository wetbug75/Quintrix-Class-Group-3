package com.Utility.ufs.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.hash.Hashing;
import com.nimbusds.jose.util.StandardCharset;

@Entity
public class TinyUrl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	@Column
	private String originalUrl;
	
	@Column
	private String tinyUrl;
	
	@Column
	private String user;
	
	public TinyUrl() {}
	
	
	public TinyUrl(String originalUrl, String user) {
		this.originalUrl = originalUrl;
		this.tinyUrl = Hashing.murmur3_32().hashString(originalUrl, StandardCharset.UTF_8).toString();
		this.user = user;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getTinyUrl() {
		return tinyUrl;
	}
	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	

}
