import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import { JokeItemComponent } from '../components/joke-item/joke-item.component';
import { Joke } from '../Joke';
import {JOKES} from '../mock-joke';

@Injectable({
  providedIn: 'root'
})

export class JokeService {
  private apiURL = 'http://localhost:5000/jokes';

  constructor(private http:HttpClient, private jokeItem: JokeItemComponent) { }
  
  getJoke(id: number): Observable<any>{
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }

  SendQuestion(question: string){
    this.jokeItem.setQuestion(question);
  }

  SendAnswer(answer: string){
    this.jokeItem.setAnswer(answer);
  }

}
