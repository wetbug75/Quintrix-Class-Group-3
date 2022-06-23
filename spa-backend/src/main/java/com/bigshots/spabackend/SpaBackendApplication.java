package com.bigshots.spabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//import org.springframework.boot.CommandLineRunner;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import java.util.Objects;
import java.util.Optional;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

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

		
		//this code will add the mysql data to azure cosmos db as keywords
		
		//this object is going to initialize the connection to azure portal
		CosmosClient client;
		//make sure you change the database and container names unless you name yours exactly the same
		//name of the database 
		final String databaseName = "Jokes";
		//name of the container in the database 
		final String containerName = "jokeKeywords";
		
		CosmosDatabase database;
		CosmosContainer container;
		//credentials to connect to the specific database. link is database, key is the primary key
		//not sure if it will let you log into mine so if you create your account plug in the values 
		client = new CosmosClientBuilder()

		

				.endpoint("")
				.key("")
				.buildClient();
		
		//CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);
        //database = client.getDatabase(databaseResponse.getProperties().getId());
		CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);
		database = client.getDatabase(databaseResponse.getProperties().getId());
		 //CosmosContainerProperties containerProperties =
		            //new CosmosContainerProperties(containerName, "/partitionKey");

		        //CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
		   //container = database.getContainer(containerResponse.getProperties().getId());
		CosmosContainerProperties containerProperties = new CosmosContainerProperties(containerName, "word");
		CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
		container = database.getContainer(containerResponse.getProperties().getId());
		//make sure you change the mysql link to your local mysql link
		try {
			String myUrl = "jdbc:mysql://127.0.0.1:3306/jokeschema?useSSL=false&serverTimezone=UTC";
			//change this to your mysql login credentials
			Connection conn = DriverManager.getConnection(myUrl, "root", "javaroot");
			
			String query = "SELECT * FROM joke";
			
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			//figure out how to separate the question mark from the question
			while(rs.next())  {
				String parse = rs.getString("answer").replaceAll("\\p{Punct}", "").toLowerCase();
				String parsed = rs.getString("question").replaceAll("\\p{Punct}", "").toLowerCase();
				Integer jokeIndex = rs.getInt("id");
				String[] arr = parse.split(" ");
				String[] arrs = parsed.split(" ");
				for(String a : arr) { 
					
					
					JokeKeyword jk = new JokeKeyword(Integer.toString(a.hashCode()), a);
					jk.jokeId.add(jokeIndex);
					
					
					CosmosItemResponse<JokeKeyword> item = null;
			
					try {
			
						 item = container.readItem(jk.getId(), new PartitionKey(jk.getWord()), JokeKeyword.class);
						
						
					}  catch (CosmosException ex) {}
					
					if(item == null) {	
						//System.out.println(jk.jokeId);
						container.createItem(jk, new CosmosItemRequestOptions());
						//jk.jokeId.add(jokeIndex);

						
						

						
					} if(item != null) {
						
						int count = 1;
						
						 if(!container.readItem(jk.getId(), new PartitionKey(jk.getWord()), JokeKeyword.class).getItem().jokeId.contains(jokeIndex)) {
						
							CosmosPatchOperations patchOps = CosmosPatchOperations.create();//.add("/jokeId", rs.getInt("id"));
			
							patchOps.add("/jokeId/" + count, jokeIndex);
						
							//step 3 
							container.patchItem(jk.getId(), new PartitionKey(jk.getWord()), patchOps, JokeKeyword.class);
							
							count++;
						 }
				
					}
					

				}
				for(String b : arrs) { 
					
					
					JokeKeyword jk = new JokeKeyword(Integer.toString(b.hashCode()), b);
					jk.jokeId.add(jokeIndex);
					
					
					CosmosItemResponse<JokeKeyword> item = null;
			
					try {
			
						 item = container.readItem(jk.getId(), new PartitionKey(jk.getWord()), JokeKeyword.class);
						
						
					}  catch (CosmosException ex) {}
					
					if(item == null) {	
						//System.out.println(jk.jokeId);
						container.createItem(jk, new CosmosItemRequestOptions());
						//jk.jokeId.add(jokeIndex);

						
						

						
					} if(item != null) {
						
						int count = 1;
						
						 if(!container.readItem(jk.getId(), new PartitionKey(jk.getWord()), JokeKeyword.class).getItem().jokeId.contains(jokeIndex)) {
						
							CosmosPatchOperations patchOps = CosmosPatchOperations.create();//.add("/jokeId", rs.getInt("id"));
			
							patchOps.add("/jokeId/" + count, jokeIndex);
						
							//step 3 
							container.patchItem(jk.getId(), new PartitionKey(jk.getWord()), patchOps, JokeKeyword.class);
							
							count++;
						 }
				
					}
			}
		}
			
			st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
		
	
	//function that will insert all the data from JSON files to Database.
	//JSON files located in resources folder. 
	//comment this function out if you do not need. 
	//JAVA 1.8 
	@Bean
	CommandLineRunner runner(MiscFunctions miscfunctions) {
		  return args -> {
			  		miscfunctions.jsonToDB();
					miscfunctions.bcryptexample();
		  };
	}

}
	

