package com.Utility.ufs.TinyUrl;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


import com.Utility.ufs.model.TinyUrl;

  
  




@RestController
@RequestMapping("/user")
public class TinyUrlController {
	@Autowired
	private final TinyUrlService tinyUrlService;
	
	public TinyUrlController(TinyUrlService tinyUrlService) {
		this.tinyUrlService = tinyUrlService;
	}
	
	/**
	 * 
	 * @param user
	 * option only pops up after the user is logged in
	 * option on front end is selected to view all tiny urls 
	 * that have been created by this logged in user 
	 * 
	 * @return
	 * list of all urls with user variable as user
	 */
	@GetMapping("/{user}/all")
	public ResponseEntity<List<TinyUrl>> getMyUrls(@PathVariable("user") String user) {
		List<TinyUrl> forMe = tinyUrlService.findUserTinyUrls(user);
		return new ResponseEntity<>(forMe, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param originalUrl
	 * @param user
	 * once user is logged in option to create a tiny url is selected 
	 * 
	 * this method creates the tiny url from the original url 
	 * entered with the front end form 
	 * 
	 * no return value url gets added to the databased registered under 
	 * "user"
	 * 
	 * and can be retrieved with the getMyUrls method 
	 */
	@PostMapping("/{user}/create")
	public ResponseEntity<TinyUrl> createTinyUrl(@RequestBody String originalUrl, @PathVariable("user") String user) {
		TinyUrl tiny = tinyUrlService.createTinyUrl(originalUrl, user);
		return new ResponseEntity<>(tiny, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * this method is a subset of getMyUrls method 
	 * a button will be attached to the Urls that allows 
	 * a specific Url to be deleted 
	 * when that button is pressed on that specific url 
	 * it will be deleted from the database 
	 * 
	 * @return
	 * response from the http signals the request was 
	 * successfully completed 
	 */
	@DeleteMapping("/delete{id}")
	public ResponseEntity<?> deleteTinyUrl(@PathVariable("id")Long id) {
		tinyUrlService.deleteTinyUrl(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * method to redirect the tinyurl 
	 * to the original url 
	 * @throws IOException 
	 */
	
	@GetMapping("/{tinyUrl}")
	public ResponseEntity<?> redirectUrl(@PathVariable("tinyUrl") String tinyUrl, HttpServletResponse response) throws IOException {
		//method is going to find the object TinyUrl of the tinyUrl entered 
		//and then assign it to this temporary TinyUrl "tiny" in order to access and redirect to the original
		TinyUrl tiny = tinyUrlService.findTinyUrl(tinyUrl);
		response.sendRedirect(tiny.getOriginalUrl());
	
		
		//then redirect to the original url of that object 
		
		return null;
	}
}
                