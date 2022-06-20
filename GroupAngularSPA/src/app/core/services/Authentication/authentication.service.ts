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
  AUTH_TOKEN : string = 'authenticationToken'

  public username: String;
  public password: String;
  public isLoggedIn :EventEmitter<boolean> = new EventEmitter();

  constructor(private http: HttpClient) {
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
    return 'Basic ' + window.btoa(user.username + ":" + user.password)
  }
  createBasicAuthTokenNoHeader(user:Users) {
  return window.btoa(user.username + ":" + user.password)
}

  registerSuccessfulLogin(user:Users) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, user.username)
    sessionStorage.setItem(this.AUTH_TOKEN, this.createBasicAuthTokenNoHeader(user));
    this.isLoggedIn.emit(true);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.AUTH_TOKEN);
    this.username = null;
    this.password = null;
    this.isLoggedIn.emit(false);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.AUTH_TOKEN)
    if (user === null){
      this.isLoggedIn.emit(false);
      return false;
    }else{
      this.isLoggedIn.emit(true);
      return true;
    }
    
  }

  getAuthToken(){
    return sessionStorage.getItem(this.AUTH_TOKEN);
  }
}