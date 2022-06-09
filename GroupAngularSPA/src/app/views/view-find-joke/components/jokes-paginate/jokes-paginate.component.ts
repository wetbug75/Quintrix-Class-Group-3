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
   totalJokesDB: number = 100; //need this from backend. 
   
   constructor(public jokeService: JokeService){
      this.jokeService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=>{
        this.JOKES = result;
      })
   }

   ngOnInit(): void {
   }
   //occurs when click on page number
   pageChanged(updatedPageNumber){ 
     console.log(updatedPageNumber);
     this.currentPage = updatedPageNumber;
     this.jokeService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=>{
      console.log(result);
     this.JOKES = result;
    })
   }

   
}
