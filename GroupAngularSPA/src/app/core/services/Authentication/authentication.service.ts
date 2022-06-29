import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Joke } from 'src/app/models/Joke';
import { Users } from 'src/app/models/User';
import { JokeService } from '../joke.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  AUTH_TOKEN : string = 'authenticationToken'

  public username: String;
  public password: String;
  private userid: number;
  public isLoggedIn :EventEmitter<boolean> = new EventEmitter();
  springUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private jokeSer: JokeService) {
    this.isUserLoggedIn();
  }

  authenticationService(user: Users) {
    return this.http.get(`http://localhost:8080/basicauth`,
      { headers: { Authorization: this.createBasicAuthToken(user) } }).pipe(map((res) => {
        this.username = user.username;
        this.password = user.password;
        this.registerSuccessfulLogin(user);
      }));
  }

  createBasicAuthToken(user:Users) {
    return 'Basic ' + window.btoa(user.username + ":" + user.password);
  }
  createBasicAuthTokenNoHeader(user:Users) {
    return window.btoa(user.username + ":" + user.password);
  }

  registerSuccessfulLogin(user:Users) {
    localStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, user.username)
    localStorage.setItem(this.AUTH_TOKEN, this.createBasicAuthTokenNoHeader(user));
    this.isLoggedIn.emit(true);
    this.getUserIDwithName(user.username).subscribe(Response => {
      this.userid = Response;
      this.jokeSer.setUserID(this.userid);
    });
  }

  logout() {
    localStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    localStorage.removeItem(this.AUTH_TOKEN);
    this.username = null;
    this.password = null;
    this.isLoggedIn.emit(false);
    this.jokeSer.setUserID(-1);
  }

  isUserLoggedIn() {
    let user = localStorage.getItem(this.AUTH_TOKEN);
    if (user === null) {
        this.isLoggedIn.emit(false);
        return false;
    } else {
        this.isLoggedIn.emit(true);
        return true;
    }
  }

  getAuthToken() {
    return localStorage.getItem(this.AUTH_TOKEN);
  }

  getUserIDwithName(name: String): Observable<any> {
    return this.http.get<any>(`${this.springUrl}/getUserID/${name}`);
  }
}
