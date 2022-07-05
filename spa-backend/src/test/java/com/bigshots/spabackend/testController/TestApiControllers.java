package com.bigshots.spabackend.testController;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.repo.JokeRepo;
import com.jayway.jsonpath.JsonPath;
import com.nimbusds.jose.shaded.json.JSONObject;

import groovy.json.JsonException;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class TestApiControllers {
	/*@LocalServerPort
	private int port;
	private String rootUrl = "localhost:" + port;*/
	@Autowired
	JokeRepo repo;
	@BeforeAll
    public static void setup() {
       String baseURI = "http://localhost:8080";
	}
	@Test
	public void testNewJokeEndpoint() throws JsonException{
		JSONObject joke = new JSONObject();
		joke.put("question", "Hi");
		joke.put("answer", "bye");
		given().auth().preemptive().basic("imdev", "imdev").contentType(ContentType.JSON)
		.body(joke.toString())
		.log().all()
		.when().post("http://localhost:8080/newJoke")
		.then().assertThat().statusCode(201);
	}
	//if a joke number is searched that doesnt exist, should return null
	/*@Test
	public void testGetJoke() {
		Response response = given().auth().preemptive().basic("imdev", "imdev")
		.contentType(ContentType.JSON).pathParam("id", (long) 1000)
		.when().get("http://localhost:8080/jokes/find/{id}")
		.then().log().all().extract().response();
		System.out.println(response.getBody());
		
	}*/
	
	
	
}
