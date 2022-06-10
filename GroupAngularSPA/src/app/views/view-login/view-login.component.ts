import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { RegisterUser } from 'src/app/models/Register';
import { userLogin } from 'src/app/models/UserLogin';

@Component({
  selector: 'app-view-login',
  templateUrl: './view-login.component.html',
  styleUrls: ['./view-login.component.css']
})
export class ViewLoginComponent implements OnInit {
  onCreateAccountForm:boolean;
  constructor() { 
    this.onCreateAccountForm = false;
  }

  ngOnInit(): void {
  }

  //submit login credentials to backend
  onUserFormGroupSubmit(userLogin : userLogin){
    console.log(userLogin);
    //this is where we will use service to send the user and password
    //TODO

  }

  //determines whether login form or register form displays
  onWantsNewAccount(userWantsNewAccount: boolean){
    this.onCreateAccountForm = true;
  }

  onRegisterFormGroupSubmit(userRegisterInfo: RegisterUser){
    console.log(userRegisterInfo)
   
    //this is where we will use service to send to backend
    //for registration
  }
  
}
