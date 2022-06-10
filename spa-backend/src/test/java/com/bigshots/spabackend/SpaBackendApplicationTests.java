package com.bigshots.spabackend;


import org.junit.Before;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.*;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.response.ValidatableResponse;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
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
	
	
	
	private final static String BASE_URI = "http://localhost";
    
    @LocalServerPort
    private int port;
 
	//TODO BASE_URI and port might be unnecessary
    @Before
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }
 
    @Test
    public void testHomePage() {
    	//TODO Can't figure out how to confirm HTML content
    	RestAssured.given().contentType(ContentType.HTML).get("/").then().assertThat().body("", equalTo("Welcome"));
    	//RestAssured.given().contentType(ContentType.HTML).get("/").then().log().body();

    }
    
    @Test
    public void testGetJokeQuestion() {
    	//TODO Andrew can probably finish this when the get request works properly
    	//RestAssured.given().contentType(ContentType.HTML).get("/joke-answer/0").then().assertThat().body("answer", equalTo("ARM"));
    	RestAssured.given().contentType(ContentType.HTML).get("/joke-answer/0").then().log().body();//could do this easily if get returned with a JS file
    }
    
}
