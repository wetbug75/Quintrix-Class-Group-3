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

  constructor(private http:HttpClient, private jokeItem: JokeItemComponent, private jokeRating: RatingsComponent) { }

  getJokeById(index: number): Observable<Joke>{
    return this.http.get<Joke>(`${this.springUrl}/jokes/find/${index}`);
  }

  SendQuestion(question: string){
    this.jokeItem.setQuestion(question);
  }

  SendAnswer(answer: string){
    this.jokeItem.setAnswer(answer);
  }

  SendLike(like: number)
  {
    this.jokeRating.SetLikeCount(like);
  }

  SendDislike(dislike: number)
  {
    this.jokeRating.SetDislikeCount(dislike);
  }

  getJokeSize(): Observable<number>{
    return this.http.get<number>(`${this.springUrl}/jokeCount`);
  }


}
