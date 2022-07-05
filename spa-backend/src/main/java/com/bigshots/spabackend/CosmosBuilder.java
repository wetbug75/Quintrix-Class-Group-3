package com.bigshots.spabackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang3.ArrayUtils;

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
import com.bigshots.spabackend.repo.JokeKeywordRepo;

public class CosmosBuilder {
	
	//You must change the strings below----------------------------
	String azureEndpoint = "https://jokeproject.documents.azure.com:443/";
	String azurePrimaryKey = "sqlMrvo8PY91y1I94mncw9hZeocwniD49mr3Rb3b3EqBbW4qmumYVDemy4UuEH4HNAm4q9qjlCVJptLNANMH8Q==";
	
	//you must change the strings above -------------------------
	
	CosmosClient client = new CosmosClientBuilder()
			.endpoint(azureEndpoint)
			.key(azurePrimaryKey)
			.buildClient();
	
	final String databaseName = "Jokes";
	final String containerName = "jokeKeywords";
	
	CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);

	CosmosDatabase database = client.getDatabase(databaseResponse.getProperties().getId());
	CosmosContainerProperties containerProperties = new CosmosContainerProperties(containerName, "word");

	CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
	CosmosContainer container = database.getContainer(containerResponse.getProperties().getId());
	
	
	
	public void writeToCosmos(Joke joke, Integer jokeIndex) {
		
		String[] arr = ArrayUtils.addAll(joke.getQuestion().replaceAll("\\p{Punct}", "").toLowerCase().split(" "), joke.getAnswer().replaceAll("\\p{Punct}", "").toLowerCase().split(" "));
		//Integer jokeIndex = Long.valueOf(joke.getId()).intValue();
		for(String a: arr) {
			JokeKeyword jk = new JokeKeyword(Integer.toString(a.hashCode()), a);
			jk.jokeId.add(jokeIndex);
			
			CosmosItemResponse<JokeKeyword> item = null;
						 
			 try {
				//check the database for the exact item
				 item = container.readItem(jk.getId(), new PartitionKey(jk.getWord()), JokeKeyword.class);
				
				
			}  catch (CosmosException ex) {}
			//if the container read didnt return an item create a new one 
			if(item == null) {	
				container.createItem(jk, new CosmosItemRequestOptions());
			//if the container read did return an item then just add this items joke id to the array of the one that already exists
			} if(item != null) {
				
				int count = 1;
				//while(item.getItem().jokeId.get(count) != null) {
					//count++;
				//}
				
				 if(!container.readItem(jk.getId(), new PartitionKey(jk.getWord()), JokeKeyword.class).getItem().jokeId.contains(jokeIndex)) {
				
					CosmosPatchOperations patchOps = CosmosPatchOperations.create();//.add("/jokeId", rs.getInt("id"));
	
					patchOps.add("/jokeId/" + count, jokeIndex);
				
					container.patchItem(jk.getId(), new PartitionKey(jk.getWord()), patchOps, JokeKeyword.class);
					
					count++;
				 }
			}
		}
	}
	


}
