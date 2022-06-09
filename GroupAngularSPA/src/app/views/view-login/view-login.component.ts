import { Component, OnInit } from '@angular/core';
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
  onWantsNewAccount(userWantsNewAccount: boolean){
    this.onCreateAccountForm = true;
  }
  
}
