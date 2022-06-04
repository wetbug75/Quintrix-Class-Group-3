package com.bigshots.spabackend;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.*;
import io.restassured.http.ContentType;
//import io.restassured.response.ValidatableResponse;
//import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpaBackendApplicationTests {
	
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
    public void testGetJokeAnswer() {
    	//TODO Andrew can probably finish this when the get request works properly
    	//RestAssured.given().contentType(ContentType.HTML).get("/joke-answer/0").then().assertThat().body("answer", equalTo("ARM"));
    	RestAssured.given().contentType(ContentType.HTML).get("/joke-answer/0").then().log().body();//could do this easily if get returned with a JS file
    }
    
}
