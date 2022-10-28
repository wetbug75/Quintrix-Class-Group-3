# Joke Telling Application

DEMO LINK: https://youtu.be/s1o0qMeg5RI

The application's purpose is to let users view, create, and search for programming jokes.

This application was created and worked on by Andrew, Devin, Leon, and Simon.

The project's purpose was to allow us to learn and grow our skills in team work and coding.

## Contributions
<strong>[Andrew Krause](https://github.com/wetbug75)</strong> worked on styling (toggle between styles), database schema, user votes backend, pagination backend, and generate jokes backend.

<strong>[Devin Lewis](https://github.com/wolfpackdev5)</strong> worked on data models, keyword lookup table, cosmosdb setup and preprocessing "tags", create jokes backend.

<strong>[Leon Lu](https://github.com/IceDragoon064)</strong> worked on styling, generate joke front end, upvoting and downvoting front end.

<strong>[Simon Kuang](https://github.com/simonsimon9)</strong> worked on project management, login, registration, pagination frontend and keyword search front end. 
<br>

## Technologies

<p align="left"> 
  <a href="https://angular.io" target="_blank" rel="noreferrer"> <img src="https://angular.io/assets/images/logos/angular/angular.svg" alt="angular" width="50"     height="50"/> </a> 
   <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="50" height="50"/> </a> 
  <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="60" height="60"/> </a>
  <a href="https://azure.microsoft.com/en-us/services/cosmos-db/" target="_blank" rel="noreferrer"> <img src="https://www.freelogovectors.net/wp-content/uploads/2022/03/azure_cosmos_db_logo_freelogovectors.net_-400x398.png" alt="azure" width="50" height="50"/> </a>
   <a href="https://jasmine.github.io" target="_blank" rel="noreferrer"> <img src="https://seeklogo.com/images/J/jasmine-logo-1A0FA4D537-seeklogo.com.png" alt="jasmine" width="50"     height="50"/> </a> 
   <a href="https://karma-runner.github.io/latest/index.html" target="_blank" rel="noreferrer"> <img src="https://www.npmjs.com/npm-avatar/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdmF0YXJVUkwiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9kMmM0MzdjNmIwNjdkNjEwNWRjYWNlMjQyNzMwM2MzYj9zaXplPTQ5NiZkZWZhdWx0PXJldHJvIn0.R1m_mytYH_ZTlKgUYtyHNDImcK7AxvFFhDvjUBZClzs" alt="angular" width="50"     height="50"/> </a> 
</p>

## Installation and the running project

Prereqs: MySQL, Eclipse, Azure CosmosDB

git clone the project :

```bash
 git clone https://github.com/wetbug75/Quintrix-Class-Group-3.git
```

Setting up front end to run:

```bash
cd GroupAngularSPA
npm install 

```

Running the front end: 
```bash
cd GroupAngularSPA
ng serve
```

Setting up back end to run 
```bash
---Import the project to eclipse---
Open Eclipse 
Click on File on the toolbar
Click on Open Project from File System...
Click on Directory...
In the file explorer, find and double click on the cloned directory 
Click on spa-backend ( should be highlighted )
Click on Select Folder
Click Finish in the Import Projects From File System or Archive window

---Configure Applications.properties credentials---
In the eclipse Project Explorer
Open spa-backend Folder
Open src/main/resrouces
Double click application.properties - this is where you must put in your configurations for MySQL/Azure
and you must create or already created a schema called jokeschema in your MySQL. 

---Edit CosmosScript Credentials---
In the SpaBackendApplication.java file under com.bigshots.spabackend
At line 32 , put in your azure endpoint (uri)
At line 33 , put in your azure key (primary key)
At line 34 , put in correct MySQL url
At line 35 and 36 , put in your MySQL credentials 

---Edit CosmosBuilder Credentials---
In the CosmosBuilder.java file under com.bigshots.spabackend
At line 29 , put in your azure endpoint (uri)
At line 30 , put in your azure key (primary key)

```

Running the back end
```bash
Open Eclipse
Double click spa-backend
Double click com.bigshots.spabackend
Right click SpaBackendApplication.java
Popup will show up, find and mouse over Run As 
Another Pop will show up, find and click Java Application
```
## Trouble Shoot

<ol>
   <li>
    <strong>Error:</strong> Spring says it cannot create bean in general, like a service.<br>
      <strong>Solution:</strong> Drop the MySQL tables, and try again. 
  </li>
  <br>
  <li>
    <strong>Error:</strong> Public Key Retrieval is not allowed <br>
      <strong>Solution:</strong> You have to configure your application.properties and make sure the username and password are correct with your local MySQL
  </li>
  <br>
   <li>
     <strong> Why:</strong> Why are all the jokes printing on the console in Spring boot <br>
     <strong>Solution:</strong> In SpaBackendApplication.java, comment out the comsmosScript.buildTableOverCloud(). And rerun the program. This is a script used to     build the keyword lookup table. 
  </li>
   <br>
   <li>
     <strong> Why:</strong> After the buildTableOverCloud is ran, I attempted to search for a keyword that exists on MySQL. The search doesn't show any jokes <br>
     <strong>Solution:</strong> The script takes a few minutes to build out the entire look up table on Azure. Devin built this script to scan the MySQL database for all the jokes. The script will then create tags for each word in each joke and add them to the look up table. Since the script is requesting and waiting for a response from Azure for each joke entry, it will take some time. Also this script runs O(n) time. But we have a function in this program that adds new keywords everytime there is a new joke is created. That function is O(1) time. The wait is due to response speed by Microsoft Azure Free Tier subscription, so it will be a little slow. 
  </li>
</ol>

#### ...more updates to come

