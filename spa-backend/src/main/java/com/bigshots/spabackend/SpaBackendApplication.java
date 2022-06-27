package com.bigshots.spabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//import org.springframework.boot.CommandLineRunner;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.implementation.NotFoundException;
import com.azure.cosmos.implementation.Utils;
import com.azure.cosmos.implementation.ImplementationBridgeHelpers.CosmosPatchOperationsHelper;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.CosmosPatchOperations;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.bigshots.spabackend.model.JokeKeyword;
import com.bigshots.spabackend.repo.JokeKeywordRepo;
import com.nimbusds.jose.util.StandardCharset;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedIterable;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.bigshots.spabackend.config.LongToJokeConverter;
import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.Users;
import com.bigshots.spabackend.repo.JokeRepo;
import com.bigshots.spabackend.repo.UserRepo;
import com.bigshots.spabackend.service.JokeService;
import com.bigshots.spabackend.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class SpaBackendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpaBackendApplication.class, args);
		
	}
	
	@Bean
	CommandLineRunner runner(MiscFunctions miscfunctions , CosmosScript cosmosScript) {
		  return args -> {
			  		miscfunctions.jsonToDB(); //imports jokes to DB
					miscfunctions.bcryptexample(); //creates an example bcrypt
				    
					//this imports the mysql jokes into azure
					//comment code below code if not needed. Exit the program
					//rerun to stop officially. Can't just resave to stop
					
					//cosmosScript.buildTableOverCloud();
					
		  };
	}

}
	

