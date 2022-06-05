package com.bigshots.spabackend;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

class SpaBackendApplicationTests {
	final static String rootUrl = "localhost:8080";

	/*@Test
	void contextLoads() {
		
	}*/
	/**
	 * Test to make sure that post method is saving the joke and writing it to the data source 
	 */
	@Test
	public void post_test() {
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\"question\": \"hi\",\"answer\": \"bye\"}")
				.when()
				.post(rootUrl + "/newJoke");
		response.then().body("question", equalTo("hi"));
		response.then().body("answer", equalTo("bye"));
	}
	
	/**
	 * same test as above but for user
	 */
	
	/**
	 * Test to make sure question is being assigned to question 
	 * and 
	 * answer is being assigned to answer
	 */
	
	
	
	/**
	 * Test to make sure the user that is logged in is the one registered to the joke 
	 * that was added 
	 */
	
	

}
