import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JokeGetService {
  private apiURL = 'http://localhost:5000/jokes';
  springUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) { }
  
  getJokesPage(page: number, limit: number):Observable<any>{
    //Spring booot
    return this.http.get<any>(`${this.springUrl}/jokes/pagination/${limit}/${page}`);
    //json server
    //return this.http.get<pageJoke>(`${this.apiURL}?_page=${page}&_limit=${limit}`)
  }

}
