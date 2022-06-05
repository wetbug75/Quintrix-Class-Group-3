import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import { JokeItemComponent } from '../../views/view-randomizer/components/joke-item/joke-item.component';
import { newJoke } from 'src/app/models/newJoke';
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

  postJoke(newJoke: newJoke): Observable<newJoke>{
    console.log('send to backend');
    return this.http.post<any>(`${this.springUrl}/newjoke`, newJoke, httpOptions);
  }

}
