import { Component, OnInit } from '@angular/core';
import { JokeService } from 'src/app/core/services/joke.service';
import { PaginationControlsDirective } from 'ngx-pagination';
import { pageJoke } from 'src/app/models/pageJoke';

@Component({
  selector: 'app-jokes-paginate',
  styleUrls: ['./jokes-paginate.component.css'],
  template: `
    <div class="container">
    <table class="table table-borderless">
        <tr>
            <th>QUESTION</th>
            <th>ANSWER</th>
            <th class="like">LIKES</th>
            <th class="dislike">DISLIKES</th>
        </tr>
        <tr *ngFor="let joke of JOKES | paginate: { itemsPerPage: pageSize, currentPage: p }">
           <td>{{joke.question}}</td>
           <td>{{joke.answer}}</td>
           <td class="like">{{joke.upvotes}}</td>
           <td class="dislike">{{joke.downvotes}}</td>
        </tr>
    </table>  
    <div class="page-control">
      <pagination-controls 
          
         (pageChange)="p = $event">
      </pagination-controls>
    </div>
</div>
    
  `
})
export class JokesPaginateComponent implements OnInit {
   JOKES: pageJoke[] = []; //recieved array of jokes
   totalElements: number = 0; //holds total no of records available in the database, server side paging useful. 
    p: number = 1; // the current (active) page number
    pageSize: number= 6;

   constructor(public jokeService: JokeService){
      this.jokeService.getJokesPage(this.p,this.pageSize).subscribe(result=>{
        console.log("INSIDE PAGE");
        console.log(result);
        this.JOKES = result;
      })
   }

   ngOnInit(): void {
     
   }

   
}
