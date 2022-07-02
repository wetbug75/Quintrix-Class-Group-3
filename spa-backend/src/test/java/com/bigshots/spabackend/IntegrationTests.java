package com.bigshots.spabackend;


import org.junit.Before;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.*;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.assertj.core.api.Assertions;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.response.ValidatableResponse;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {
	
	private final static String BASE_URI = "http://localhost";
    
    @LocalServerPort
    private int port;
 
	//TODO BASE_URI and port might be unnecessary
    @Before
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }
	
	final static String rootUrl = "localhost:8080";
	
	
	private ValidatableResponse validatableResponse;
	
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
	
    @Test
    public void testGetJokeAnswer() {
    	given().contentType(ContentType.JSON).get("/jokes/find/1")
    			.then().assertThat().body("answer", equalTo("ARM"));
    }
    
    @Test
    public void listJokes() {
    	validatableResponse = given().contentType(ContentType.JSON)
    			.when().get("/jokes")
    			.then().assertThat().statusCode(200);
    }
    
    @Test
    public void listAJoke() {
    	System.out.println("response incoming: ");
    	validatableResponse = given().contentType(ContentType.JSON)
    			.when().get("/jokes/find/1")
    			.then().assertThat().log().all().statusCode(200).body("id", equalTo("1")).body("question", equalTo("Which body part does a programmer know best?"));
    	System.out.println(validatableResponse.toString());
    }
    
}
