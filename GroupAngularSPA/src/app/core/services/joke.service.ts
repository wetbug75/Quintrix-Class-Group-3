import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Joke } from 'src/app/models/Joke';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';
import { RatingsComponent } from 'src/app/shared/components/ratings/ratings.component';
import { JokeGetService } from './JokeGET/joke-get.service';
import { JokePostService } from './JokePOST/joke-post.service';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}
@Injectable({
  providedIn: 'root'
})

export class JokeService {

  private apiURL = `http://127.0.0.1:3306`;
  springUrl = `http://localhost:8080`;
  currentJoke: Joke;

  constructor(
    private jokeGetSer: JokeGetService,
    private jokePostSer: JokePostService,
    private jokeItem: JokeItemComponent,
    private jokeRating: RatingsComponent){
  }

  UpdateUpvote(joke: Joke, upvote: number, jokeid: number){
    this.jokePostSer.UpdateLikeCount(joke, jokeid);
    this.jokeRating.SetLikeCount(upvote);
  }

  UpdateDownvote(joke: Joke, downvote:number, jokeid: number){
    this.jokePostSer.UpdateDislikeCount(joke, jokeid);
    this.jokeRating.SetDislikeCount(downvote);
  }

  // getJokeById(index: number): Observable<Joke>{
  //   return this.http.get<Joke>(`${this.springUrl}/jokes/find/${index}`, httpOptions);
  // }

  GetRandomJoke(id: number){
    this.jokeGetSer.getJokeById(id).subscribe(Response =>{
      this.jokeItem.SetJoke(Response);
      this.jokeItem.SetQuestion(Response.question);
      this.jokeItem.SetAnswer(Response.answer);
      this.jokeItem.SetUpvote(Response.upvotes);
      this.jokeItem.SetDownvote(Response.downvotes);
      this.jokeRating.SetLikeCount(Response.upvotes);
      this.jokeRating.SetDislikeCount(Response.downvotes);
    })
  }

  GetJokeDatabaseSize(){
    return this.jokeGetSer.getJokeSize();
  }

  GetJoke(){
    return this.jokeItem.GetJoke();
  }

  GetJokeID(){
    return this.jokeItem.GetJokeID();
  }

  // getJokeByKeyword(word: String): Observable<any>{
  //   return this.http.get<any>(`${this.springUrl}/jokesWith/${word}`)
  // }

}

