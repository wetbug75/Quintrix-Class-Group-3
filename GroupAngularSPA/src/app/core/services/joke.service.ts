import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import { JokeItemComponent } from '../../views/view-randomizer/components/joke-item/joke-item.component';
import { pageJoke } from 'src/app/models/pageJoke';
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

  constructor(private http:HttpClient, private jokeItem: JokeItemComponent) { }

  getJokeById(index: number): Observable<Joke>{
    return this.http.get<Joke>(`${this.springUrl}/jokes/find/${index}`);
  }

  SendQuestion(question: string){
    this.jokeItem.setQuestion(question);
  }

  SendAnswer(answer: string){
    this.jokeItem.setAnswer(answer);
  }

  getJokeSize(): Observable<number>{
    return this.http.get<number>(`${this.springUrl}/jokeCount`);
  }

  //post a new joke , sends {answer, question}
  postJoke(newJoke: Joke): Observable<Joke>{
    console.log('send to backend');
    newJoke.created_by="klsdf;j";

    console.log(newJoke);
    //Spring Boot
    return this.http.post<Joke>(`${this.springUrl}/newJoke`, newJoke);

    //JSON server
    //return this.http.post<any>(`${this.apiURL}`, {"answer": newJoke.answer, "question": newJoke.question, "id": (Math.random()*1000000)+ 200}, httpOptions)
  }

  getJokesPage(page: number, limit: number):Observable<any>{

    /*
      GET /posts?_page=7
      GET /posts?_page=7&_limit=20
    */
    //json server
    return this.http.get<pageJoke>(`${this.apiURL}?_page=${page}&_limit=${limit}`)
  }

}
