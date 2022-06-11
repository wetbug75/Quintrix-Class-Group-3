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

  private apiURL = 'http://localhost:5000/jokes';
  springUrl = 'http://localhost:8080';

  constructor(private http:HttpClient, private jokeItem: JokeItemComponent) { }
  
  
  getJoke(id: number): Observable<any>{
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }
  

  /*
  // Backend GET method
  getJoke(id: number): Observable<any>{
    return this.http.get<any>(`${this.springUrl}/random-joke`);
  }
  */

  getJokeQuestion(index: number): Observable<any>{
    return this.http.get<any>('${this.springUrl}/joke-question/{index}');
  }

  getJokeAnswer(index: number): Observable<any>{
    return this.http.get<any>('${this.springUrl}/joke-answer/{index}');
  }

  SendQuestion(question: string){
    this.jokeItem.setQuestion(question);
  }

  SendAnswer(answer: string){
    this.jokeItem.setAnswer(answer);
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
