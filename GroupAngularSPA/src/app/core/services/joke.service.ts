import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import { JokeItemComponent } from '../../views/view-randomizer/components/joke-item/joke-item.component';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}
@Injectable({
  providedIn: 'root'
})

export class JokeService {
  private apiURL = 'http://localhost:8080/newjoke';

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

  postJoke(newJoke: any): Observable<any>{
    console.log('send to backend');
    return this.http.post<any>(`${this.apiURL}`, newJoke, httpOptions);
  }

}
