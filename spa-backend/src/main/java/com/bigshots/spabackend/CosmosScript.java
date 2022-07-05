package com.bigshots.spabackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

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
import com.bigshots.spabackend.model.JokeKeyword;
@Component
public class CosmosScript {

	
	
	
	public void buildTableOverCloud() {
		//These are the strings you must change!!! look below------------
		
		String azureEndpoint = "https://jokeproject.documents.azure.com:443/";
		String azurePrimaryKey = "sqlMrvo8PY91y1I94mncw9hZeocwniD49mr3Rb3b3EqBbW4qmumYVDemy4UuEH4HNAm4q9qjlCVJptLNANMH8Q==";
		String mySqlUrl = "jdbc:mysql://127.0.0.1:3306/jokes?useSSL=false&serverTimezone=UTC";
		String mySqlUsername = "root";
		String mySqlPassword = "kevinwall5";
		
		//These are the strings you must change--------------------------
		try {
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
					.endpoint(azureEndpoint)
					.key(azurePrimaryKey)
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
			String myUrl = mySqlUrl;
			//change this to your mysql login credentials
			Connection conn = DriverManager.getConnection(myUrl, mySqlUsername, mySqlPassword);
			
			String query = "SELECT * FROM joke";
			
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			//figure out how to separate the question mark from the question
			while(rs.next())  {
				System.out.println("Checking Joke Question: " +rs.getString("answer") + "  " + rs.getString("question"));
			 
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
	
}

