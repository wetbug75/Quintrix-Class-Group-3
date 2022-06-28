package com.bigshots.spabackend;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bigshots.spabackend.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BasicTests {

	@Autowired
	private UserService userService;
	
	@Test
	public void contextLoads() {
		Assertions.assertThat(userService).isNot(null);
	}
}
