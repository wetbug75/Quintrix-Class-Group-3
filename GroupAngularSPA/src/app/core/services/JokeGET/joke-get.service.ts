import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
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

export class JokeGetService {
  private apiURL = 'http://localhost:5000/jokes';
  springUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) { }

  getJokesPage(page: number, limit: number):Observable<any> {
    return this.http.get<any>(`${this.springUrl}/jokes/pagination/${limit}/${page}`,httpOptions);
  }

  getJokeSize(): Observable<number> {
    return this.http.get<number>(`${this.springUrl}/jokeCount`, httpOptions);
  }

  getJokeById(index: number): Observable<Joke> {
    return this.http.get<Joke>(`${this.springUrl}/jokes/find/${index}`);
  }

  getJokeByKeyword(word: String, page: number, pageSize: number): Observable<any> {
    return this.http.get<any>(`${this.springUrl}/jokesWith/${word}/${page}/${pageSize}`);
  }

  getKeywordSize(word:String): Observable<any> {
    return this.http.get<any>(`${this.springUrl}/jokesWithKeywordCount/${word}`);
  }

  getUserVote(user_id: number, joke_id: number) {
    return this.http.get<any>(`${this.springUrl}/voteStatus/${user_id}/${joke_id}`);
  }
}
