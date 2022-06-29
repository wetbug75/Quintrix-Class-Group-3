import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Joke } from 'src/app/models/Joke';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}
@Injectable({
  providedIn: 'root'
})
export class JokePostService {

  private apiURL = 'http://localhost:5000/jokes';
  springUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) { }

  //post a new joke , sends {answer, question}
  postJoke(newJoke: Joke): Observable<Joke> {
    return this.http.post<Joke>(`${this.springUrl}/newJoke`, newJoke, httpOptions);
  }

  updateLikeCount(updateJoke: Joke, jokeID: number) {
    this.http.put(`${this.springUrl}/jokes/${jokeID}/update/upvote`,updateJoke,httpOptions).subscribe(data => {
      console.log(data);
    });
  }

  updateDislikeCount(updateJoke: Joke, jokeID: number) {
    this.http.put(`${this.springUrl}/jokes/${jokeID}/update/downvote`,updateJoke,httpOptions).subscribe(data => {
      console.log(data);
    });
  }

  updateUserJokeVote(user_id: number, joke_id: number, jokeVote: String) {
    this.http.put(`${this.springUrl}/voteStatus/update/${user_id}/${joke_id}`, jokeVote, httpOptions).subscribe(data => {
      console.log(data);
    });
  }
}

