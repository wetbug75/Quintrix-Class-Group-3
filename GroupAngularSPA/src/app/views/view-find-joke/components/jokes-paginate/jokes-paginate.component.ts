import { Component, OnInit } from '@angular/core';
import { JokeService } from 'src/app/core/services/joke.service';
import { JokeGetService } from 'src/app/core/services/JokeGET/joke-get.service';
import { pageJoke } from 'src/app/models/pageJoke';

@Component({
  selector: 'app-jokes-paginate',
  styleUrls: ['./jokes-paginate.component.css'],
  templateUrl: './jokes-paginate.component.html'
})
export class JokesPaginateComponent implements OnInit {
   JOKES: pageJoke[] = []; //recieved array of jokes from backend. 
   currentPage: number = 1; // the current (active) page 
   pageSize: number= 6; //sets how many results per page
   totalJokesDB: number = 0;//need this from backend. This only updates the pagination controls. 
   keyword: string = ""; //user keyword
   constructor(public jokeGetService: JokeGetService){
        this.jokeGetService.getJokesPage(this.currentPage,this.pageSize).subscribe(jokes=>{
          this.totalJokesDB = 150; //need this from backend. 
          this.JOKES = jokes.filter(index => index !== null); //need joke array from backend. 
          this.keyword = "";
      })
   }

   ngOnInit(): void {
   }
   //occurs when click on page number
   pageChanged(updatedPageNumber){ 
     console.log(this.keyword); //keyword will be required to differentiate between getting with keyword or getting all jokes. 
     this.currentPage = updatedPageNumber;
     //find and retrieve all jokes. 
     this.jokeGetService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=>{ 
        console.log(result);
        this.totalJokesDB =150; //required from backend. 
        this.JOKES =result.filter(joke=> joke !== null);
      })
   }

   //search keyword
   onSubmitSearch($event){
    this.keyword = $event; //required - this is from the search bar. 
    console.log(this.keyword) 
    this.currentPage = 1; //required - sets to first page of results when user clicks on search
     
    this.pageSize = 4; //remove - i think i can remove this function after the backend search keyword is enabled. 

     //search by key word
    this.jokeGetService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=>{
        console.log(result);
        this.totalJokesDB = 3; //required - how many jokes with that keyword - controls pagination control
        this.JOKES = result.filter(joke=> joke!=null); //found jokes with the key word
      })
   }

   
}
