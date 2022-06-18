import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Users } from 'src/app/models/User';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  
  public username: String;
  public password: String;
  public isLoggedIn :EventEmitter<boolean> = new EventEmitter();

  constructor(private http: HttpClient) {
    this.isLoggedIn.emit(false);
  }

  authenticationService(user: Users) {
    return this.http.get(`http://localhost:8080/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(user) } }).pipe(map((res) => {
        this.username = user.username;
        this.password = user.password;
        this.registerSuccessfulLogin(user);
        
      }));
  }

  createBasicAuthToken(user:Users) {
      console.log("Creating token");
    return 'Basic ' + window.btoa(user.username + ":" + user.password)
  }

  registerSuccessfulLogin(user:Users) {
      console.log("session item set , save user name");
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, user.username)
    this.isLoggedIn.emit(true);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
    this.isLoggedIn.emit(false);
  }

  isUserLoggedIn() {
      console.log("Checking is is logged in");
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
      console.log("get loggedin user name")
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }
}