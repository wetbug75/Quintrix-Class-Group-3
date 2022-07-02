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
    private jokeRating: RatingsComponent)
    { }

  updateUpvote(joke: Joke, upvote: number, jokeid: number) {
    this.jokePostSer.updateLikeCount(joke, jokeid);
    this.jokeRating.setLikeCount(upvote);
  }

  updateDownvote(joke: Joke, downvote:number, jokeid: number) {
    this.jokePostSer.updateDislikeCount(joke, jokeid);
    this.jokeRating.setDislikeCount(downvote);
  }

  getRandomJoke(id: number) {
    this.jokeGetSer.getJokeById(id).subscribe(Response =>{
      this.jokeItem.setJoke(Response);
      this.jokeItem.setJokeID(Response.id);
      this.jokeItem.setQuestion(Response.question);
      this.jokeItem.setAnswer(Response.answer);
      this.jokeItem.setUpvote(Response.upvotes);
      this.jokeItem.setDownvote(Response.downvotes);
      this.jokeRating.setLikeCount(Response.upvotes);
      this.jokeRating.setDislikeCount(Response.downvotes);
    })
  }

  getUserVote(userID: number, jokeID: number) {
    return this.jokeGetSer.getUserVote(userID, jokeID);
  }

  getJokeDatabaseSize() {
    return this.jokeGetSer.getJokeSize();
  }

  getJoke() {
    return this.jokeItem.getJoke();
  }

  getJokeID() {
    return this.jokeItem.getJokeID();
  }

  setUserID(id: number) {
    this.currentUserID = id;
  }

  getUserID() {
    return this.currentUserID;
  }

  updateUserJokeVote(user_id: number, joke_id: number, jokeVote: String) {
    this.jokePostSer.updateUserJokeVote(user_id, joke_id, jokeVote);
  }
}

