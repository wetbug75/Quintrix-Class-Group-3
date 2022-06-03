package com.bigshots.spabackend;

import org.junit.jupiter.api.Test;

import io.restassured.*;
import org.hamcrest.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

class SpaBackendApplicationTests {

	/*@Test
	void contextLoads() {
		
	}*/
	
	@Test
	public void testPostMethod() {
		RestAssured.given().body("question").body("answer").
		when().post("http://localhost:8080/newjoke").
		then().log().all();
	}

}
