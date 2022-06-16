package com.bigshots.spabackend;

import org.springframework.beans.factory.annotation.Autowired;


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


@SpringBootApplication
public class SpaBackendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpaBackendApplication.class, args);
		
		//this object is going to initialize the connection to azure portal
		CosmosClient client;
		//name of the database 
		final String databaseName = "Jokes";
		//name of the container in the database 
		final String containerName = "jokeKeywords";
		
		CosmosDatabase database;
		CosmosContainer container;
		//credentials to connect to the specific database. link is database, key is the primary key
		//not sure if it will let you log into mine so if you create your account plug in the values 
		client = new CosmosClientBuilder()
				.endpoint("https://jokeproject.documents.azure.com:443/")
				.key("cewHNUlsbyOe9v6ctfd3Uz2JSMisxG38KH6lqWNwEZPX3WCd8zpfTn8kSKnlzhbmMrYCTHfLEJiOV4uYm5aUyA")
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
		
		try {
			String myUrl = "jdbc:mysql://127.0.0.1:3306/jokes?useSSL=false&serverTimezone=UTC";
			
			Connection conn = DriverManager.getConnection(myUrl, "root", "kevinwall5");
			
			String query = "SELECT * FROM joke";
			
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())  {
				String parse = rs.getString("question");
				Integer jokeIndex = rs.getInt("id");
				String[] arr = parse.split(" ");
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
						
						 
						
						CosmosPatchOperations patchOps = CosmosPatchOperations.create();//.add("/jokeId", rs.getInt("id"));
		
						patchOps.add("/jokeId/" + count, jokeIndex);
					
						//step 3 
						container.patchItem(jk.getId(), new PartitionKey(jk.getWord()), patchOps, JokeKeyword.class);
						
						count++;
				
					}

				}
			}
			
			st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		

}
	

