import { Component, OnInit } from '@angular/core';
import { JokeService } from 'src/app/core/services/joke.service';
import { JokeGetService } from 'src/app/core/services/JokeGET/joke-get.service';
import { LoadingService } from 'src/app/core/services/Loading/loading.service';
import { Joke } from 'src/app/models/Joke';
import { pageJoke } from 'src/app/models/pageJoke';
import { LoadingComponent } from 'src/app/shared/components/loading/loading.component';
@Component({
  selector: 'app-jokes-paginate',
  styleUrls: ['./jokes-paginate.component.css'],
  templateUrl: './jokes-paginate.component.html'
})
export class JokesPaginateComponent implements OnInit {
   loadingMessage: string = "Loading...";
   JOKES: pageJoke[] = []; //recieved array of jokes from backend.
   currentPage: number = 1; // the current (active) page
   pageSize: number= 6; //sets how many results per page
   totalJokesDB: number = 0;//need this from backend. This only updates the pagination controls.
   keyword: string; //user keyword
   constructor(public jokeGetService: JokeGetService, public loadingService: LoadingService){

      this.jokeGetService.getJokesPage(this.currentPage,this.pageSize).subscribe(jokes=>{
        this.JOKES = jokes.filter(index => index !== null); //need joke array from backend.
        this.keyword = "";
      })
      this.jokeGetService.getJokeSize().subscribe(count=>{
        this.totalJokesDB = count;
      })

   }

   ngOnInit(): void {
   }
   //occurs when click on page number
   pageChanged(updatedPageNumber) {
    console.log("this is keyword");
     console.log(this.keyword); //keyword will be required to differentiate between getting with keyword or getting all jokes.
     this.currentPage = updatedPageNumber;
     //find and retrieve all jokes.

     if(this.keyword == "") {

      this.jokeGetService.getJokesPage(this.currentPage,this.pageSize).subscribe(result=> {
          console.log(result);
          this.JOKES =result.filter(joke=> joke !== null);
        })
        this.jokeGetService.getJokeSize().subscribe(count=> {
          this.totalJokesDB = count;
        })
     } else {
        this.jokeGetService.getJokeByKeyword(this.keyword, this.currentPage, this.pageSize).subscribe(
          result => {
            console.log(result);
            this.JOKES = result.filter((joke: Joke)=> joke!=null); //found jokes with the key word
        })
     }
   }

   //search keyword still in progress
   onSubmitSearch($event){
    this.keyword = $event; //required - this is from the search bar.
    this.currentPage = 1; //required - sets to first page of results when user clicks on search

    //search by key word
    if(this.keyword==""  || this.keyword === undefined) {
      //repeated code
      this.jokeGetService.getJokesPage(this.currentPage,this.pageSize).subscribe(jokes=> {
        this.JOKES = jokes.filter(index => index !== null); //need joke array from backend.
        this.keyword = "";
      })
      this.jokeGetService.getJokeSize().subscribe(count=> {
        this.totalJokesDB = count;
      })
     } else {
        console.log()
        this.jokeGetService.getJokeByKeyword(this.keyword, this.currentPage, this.pageSize).subscribe(
        result => {
          console.log(result);
          this.JOKES = result.filter((joke: Joke)=> joke!=null); //found jokes with the key word
        })
        this.jokeGetService.getKeywordSize(this.keyword).subscribe(count => {
          console.log("this is from backend getkeywordzie : " + count);
          this.totalJokesDB = count;
        }, error =>{
          console.log("this shit don't work");
        })
     }
   }
}
