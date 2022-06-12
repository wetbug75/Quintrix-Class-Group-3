import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/User';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}
@Injectable({
  providedIn: 'root'
})
export class UserService {
  springUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  registerUser(newUser : User):Observable<any>{
    //const user = {id: 0, username:"oo",email:"lsdjf",password:"sdkfjksd" };
    //const myJSON = JSON.stringify(user);
    //console.log(myJSON);
    return this.http.post<User>(`${this.springUrl}/newUser`, newUser , httpOptions);
  }
}
