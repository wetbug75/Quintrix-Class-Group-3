import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';
import { Joke } from 'src/app/models/Joke';
import { Observable } from 'rxjs';
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

  constructor(private http:HttpClient, private jokeItem: JokeItemComponent) { }
    //post a new joke , sends {answer, question}
    postJoke(newJoke: Joke): Observable<Joke>{

      console.log(newJoke);
      //Spring Boot
      return this.http.post<Joke>(`${this.springUrl}/newJoke`, newJoke, httpOptions);
  
      //JSON server
      //return this.http.post<any>(`${this.apiURL}`, {"answer": newJoke.answer, "question": newJoke.question, "id": (Math.random()*1000000)+ 200}, httpOptions)
    }
}