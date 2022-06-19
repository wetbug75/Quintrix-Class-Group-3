
# Joke Telling Application

The application's purpose is to let users view, create, and search for programming jokes.

This application was created and worked on by Andrew, Devin, Leon, and Simon

The purpose for the project was to allow us to learn and grow our skills in team work. 

## Technologies

<p align="left"> 
  <a href="https://angular.io" target="_blank" rel="noreferrer"> <img src="https://angular.io/assets/images/logos/angular/angular.svg" alt="angular" width="50"     height="50"/> </a> 
   <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="50" height="50"/> </a> 
  <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="60" height="60"/> </a>
  <a href="https://azure.microsoft.com/en-us/services/cosmos-db/" target="_blank" rel="noreferrer"> <img src="https://www.freelogovectors.net/wp-content/uploads/2022/03/azure_cosmos_db_logo_freelogovectors.net_-400x398.png" alt="azure" width="50" height="50"/> </a>
</p>

## Installation and the running project

Prereqs: MySQL, Eclipse

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
Open Eclipse 
Click on File on the toolbar
Click on Open Project from File System...
Click on Directory...
In the file explorer, find and double click on the cloned directory 
Click on spa-backend ( should be highlighted )
Click on Select Folder
Click Finish in the Import Projects From File System or Archive window

In the eclipse Project Explorer
Double click spa-backend Folder
Double click src/main/resrouces
Double click application.properties - this is where you must put in your configurations for MySQL/Azure
and you must create or already created a schema called jokeschema in your MySQL. 


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
    Error: Public Key Retrieval is not allowed <br>
    Solution: You have to configure your application.properties and make sure the username and password are correct with your local MySQL
  </li>
</ol>

#### ...more updates to come

