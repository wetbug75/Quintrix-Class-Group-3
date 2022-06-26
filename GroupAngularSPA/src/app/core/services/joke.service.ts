import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Joke } from 'src/app/models/Joke';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';
import { RatingsComponent } from 'src/app/shared/components/ratings/ratings.component';
import { JokeGetService } from './JokeGET/joke-get.service';
import { JokePostService } from './JokePOST/joke-post.service';
import { Observable } from 'rxjs';


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
  currentUserID: number;

  constructor(
    private jokeGetSer: JokeGetService,
    private jokePostSer: JokePostService,
    private jokeItem: JokeItemComponent,
    private jokeRating: RatingsComponent,
    private http: HttpClient){
  }

  UpdateUpvote(joke: Joke, upvote: number, jokeid: number){
    console.log("Updating upvote");
    this.jokePostSer.UpdateLikeCount(joke, jokeid);
    this.jokeRating.SetLikeCount(upvote);
  }

  UpdateDownvote(joke: Joke, downvote:number, jokeid: number){
    console.log("Updating downvote");
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

  GetUserVote(userID: number, jokeID: number){
    return this.jokeGetSer.getUserVote(userID, jokeID);
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

  SetUserID(id: number){
    this.currentUserID = id;
  }

  GetUserID(){
    return this.currentUserID;
  }

  UpdateUserJokeVote(user_id: number, joke_id: number, jokeVote: String){
    this.jokePostSer.UpdateUserJokeVote(user_id, joke_id, jokeVote);

  }

   //getJokeByKeyword(word: String): Observable<any>{
     //return this.http.get<any>(`${this.springUrl}/jokesWith/${word}`)
   //}

}

