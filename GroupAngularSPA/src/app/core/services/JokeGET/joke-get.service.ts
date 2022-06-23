import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Joke } from 'src/app/models/Joke';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';

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

  getJokesPage(page: number, limit: number):Observable<any>{
    //Spring booot
    return this.http.get<any>(`${this.springUrl}/jokes/pagination/${limit}/${page}`,httpOptions);
    //json server
    //return this.http.get<pageJoke>(`${this.apiURL}?_page=${page}&_limit=${limit}`)
  }

  getJokeSize(): Observable<number>{
    return this.http.get<number>(`${this.springUrl}/jokeCount`, httpOptions);
  }


  // Request backend to get a joke using a joke id
  getJokeById(index: number): Observable<Joke>{
    return this.http.get<Joke>(`${this.springUrl}/jokes/find/${index}`);
  }
  // Request backend to get joke database size
  // Used by randomizer to calculate a random number and prevent index out of bounds error
  // getJokeSize(): Observable<number>{
  //   return this.http.get<number>(`${this.springUrl}/jokeCount`);
  // }

   getJokeByKeyword(word: String, page: number, pageSize: number): Observable<any>{
     return this.http.get<any>(`${this.springUrl}/jokesWith/${word}/${page}/${pageSize}`)
   }

   getKeywordSize(word:String): Observable<any>{
     return this.http.get<any>(`${this.springUrl}/jokesWithKeywordCount/${word}` )
   }
}
