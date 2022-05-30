import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}
@Injectable({
  providedIn: 'root'
})
export class JokeDataService {
  //private apiUrl = environment.backendurl;
  private apiUrl = "http://localhost:5000/jokes" //subject to change
  constructor(private myHttpRequest:HttpClient) { }

  postJoke(newJoke: any): Observable<any>{
    console.log('send to backend');
    return this.myHttpRequest.post<any>(`${this.apiUrl}`, newJoke, httpOptions);
  }

  }

