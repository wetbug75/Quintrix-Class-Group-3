import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

}
