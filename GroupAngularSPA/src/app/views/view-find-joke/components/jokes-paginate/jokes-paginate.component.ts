import { Component, OnInit } from '@angular/core';
import { JokeService } from 'src/app/core/services/joke.service';
import { pageJoke } from 'src/app/models/pageJoke';

@Component({
  selector: 'app-jokes-paginate',
  styleUrls: ['./jokes-paginate.component.css'],
  templateUrl: './jokes-paginate.component.html'
})
export class JokesPaginateComponent implements OnInit {
   JOKES: pageJoke[] = []; //recieved array of jokes from backend. 
   totalElements: number = 0; //holds total no of records available in the database, server side paging useful. 
   currentPage: number = 1; // the current (active) page 
   pageSize: number= 6; //sets how many results per page
   totalJokesDB: number;//need this from backend. 
   keyword: string; //user keyword
   constructor(public jokeService: JokeService){
      this.jokeService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=>{
        this.totalJokesDB = 100; //need this from backend. 
        this.JOKES = result; //need joke array from backend. 
      })
   }

   ngOnInit(): void {
   }
   //occurs when click on page number
   pageChanged(updatedPageNumber){ 
     this.currentPage = updatedPageNumber;
     //find and retrieve all jokes. 
     this.jokeService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=>{
        console.log(result);
        this.totalJokesDB = 100;
        this.JOKES = result;
      })
   }

   onSubmitSearch($event){
     this.keyword = $event;
     
     //mock - remove when done. 
      this.pageSize = 3;
     //search by key word
     this.jokeService.getJokesPage(5,this.pageSize).subscribe(result=>{
        console.log(result);
        this.totalJokesDB = 1; // how many jokes with that keyword
        this.JOKES = result; //found jokes with the key word
      })
   }

   
}
