package com.Utility.ufs.TinyUrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Utility.ufs.model.TinyUrl;
import com.Utility.ufs.repo.tinyUrlRepo;
import com.google.common.hash.Hashing;
import com.nimbusds.jose.util.StandardCharset;


@Service
public class TinyUrlService {
private final tinyUrlRepo tinyUrlRepo;
	
	@Autowired
	public TinyUrlService(tinyUrlRepo tinyUrlRepo) {
		this.tinyUrlRepo = tinyUrlRepo;
	}
	
	//method to add a tiny url to the repo
	public TinyUrl addTinyUrl(TinyUrl tinyUrl) {
		
		return tinyUrlRepo.save(tinyUrl);
	}
	
	//method to delete a tiny url from the repo 
	public void deleteTinyUrl(Long id) {
		tinyUrlRepo.deleteUrlById(id);
	}
	
	
	
	/**
	 * method that will return all the tiny urls registered to a user 
	 * when they log in
	 */
	public List<TinyUrl> findUserTinyUrls(String user) {
		return tinyUrlRepo.findByUser(user);
	}
	
	
	//method to create a tiny url 
	public TinyUrl createTinyUrl(String originalUrl, String user) {
		TinyUrl tinyUrl = new TinyUrl(originalUrl, user);
		addTinyUrl(tinyUrl);
		return tinyUrl;
	}
	
	//method to return the tinyUrl of TinyUrl
	public TinyUrl findTinyUrl(String tinyUrl) {
		TinyUrl tinyUrlrd = tinyUrlRepo.findByTinyUrl(tinyUrl);
		return tinyUrlrd;
	}
	
	
	

}
