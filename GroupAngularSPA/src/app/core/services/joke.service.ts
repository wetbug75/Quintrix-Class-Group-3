import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { JokeItemComponent } from '../../views/view-randomizer/components/joke-item/joke-item.component';
import { RatingsComponent } from 'src/app/shared/components/ratings/ratings.component';
import { Joke } from 'src/app/models/Joke';
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

  constructor(private http:HttpClient, private jokeItem: JokeItemComponent, private jokeRate: RatingsComponent) { }


  // If a joke is displayed, have a variable to store the joke id to reference
  SendJokeID(jokeID: number){
    this.jokeItem.SetJokeID(jokeID);
  }

  SetCurrentJoke(newJoke: Joke){
    this.currentJoke = newJoke;
  }

  GetCurrentJoke(){
    return this.currentJoke;
  }

  SetUpvoteCount(newUpvote: number){
    this.jokeRate.SetLikeCount(newUpvote);
  }
}
