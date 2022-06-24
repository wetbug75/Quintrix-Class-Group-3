package com.bigshots.spabackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.CosmosPatchOperations;
import com.azure.cosmos.models.PartitionKey;
import com.bigshots.spabackend.model.Joke;
import com.bigshots.spabackend.model.JokeKeyword;

public class CosmosBuilder {
	
	CosmosClient client = new CosmosClientBuilder()
			.endpoint("https://jokeproject.documents.azure.com:443/")
			.key("sqlMrvo8PY91y1I94mncw9hZeocwniD49mr3Rb3b3EqBbW4qmumYVDemy4UuEH4HNAm4q9qjlCVJptLNANMH8Q==")
			.buildClient();
	
	final String databaseName = "Jokes";
	final String containerName = "jokeKeywords";
	
	CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);

	CosmosDatabase database = client.getDatabase(databaseResponse.getProperties().getId());
	CosmosContainerProperties containerProperties = new CosmosContainerProperties(containerName, "word");

	CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
	CosmosContainer container = database.getContainer(containerResponse.getProperties().getId());
	
	
	
	public void writeToCosmos(Joke joke) {
		//joke question break it down into the array
		//joke answer break it down into the array 
		//make sure it goes into lowercase and splits question marks 
		//container create item if it doesnt exist 
		//if it does exist take the id from this joke object and put it into the array of the item that already exists
	}
	


}
