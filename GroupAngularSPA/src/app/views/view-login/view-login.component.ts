import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-view-login',
  templateUrl: './view-login.component.html',
  styleUrls: ['./view-login.component.css']
})
export class ViewLoginComponent implements OnInit {
  onCreateAccountForm:boolean;
  constructor(public userService: UserService) { 
    this.onCreateAccountForm = false;
  }

  ngOnInit(): void {
  }

  //submit login credentials to backend
  onUserFormGroupSubmit(userLogin : User){
    console.log(userLogin);
    //this is where we will use service to send the user and password
    //TODO

  }

  //determines whether login form or register form displays
  onWantsNewAccount(userWantsNewAccount: boolean){
    this.onCreateAccountForm = true;
  }

  //create new user 
  onRegisterFormGroupSubmit(userRegisterInfo: User){
    console.log(userRegisterInfo)
     console.log("INSIDE NEW USER");
    //this is where we will use service to send to backend
    //for registration
    this.userService.registerUser(userRegisterInfo).subscribe(result=>{
      console.log(result);
    })
  }
  
}
