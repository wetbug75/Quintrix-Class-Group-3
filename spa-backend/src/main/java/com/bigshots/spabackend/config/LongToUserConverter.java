package com.bigshots.spabackend.config;

import org.springframework.core.convert.converter.Converter;

import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.service.UserService;

public class LongToUserConverter implements Converter<Long, Users>{
	//@Autowired
		UserService userService;
		
		@Override
		public Users convert(Long source) {
			return userService.findUserById(source).orElse(null);
		}
}
