import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserPostService } from 'src/app/core/services/UserPOST/user-post.service';
import { Users } from 'src/app/models/User';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';
import { Status } from 'src/app/models/status';
@Component({
  selector: 'app-view-login',
  templateUrl: './view-login.component.html',
  styleUrls: ['./view-login.component.css']
})
export class ViewLoginComponent implements OnInit {
  //Class too complicated. Needs refactoring. Too many
  //states
  onCreateAccountForm:boolean;

  errorMessage : string= 'Invalid Credentials';
  registerSuccessMessage: string = 'Account created. Please login';
  registerFailMessage: string = 'Something went wrong, try again or try later';
  registrationDuplicate: boolean;

  invalidLoginAlert : boolean= false;
  successRegisterAlert : boolean = false;
  notsuccessRegisterAlert: boolean = false;
  duplicateMessage : string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService, public userPost: UserPostService) {
    this.onCreateAccountForm = false;
  }

  ngOnInit(): void { }

  //submit login credentials to backend
  onUserFormGroupSubmit(userLogin : Users) {
    console.log(userLogin);
    //this is where we will use service to send the user and password
    //TODO
    this.authenticationService.authenticationService(userLogin).subscribe((result)=> {
      this.invalidLoginAlert = false;
      this.router.navigate(['']); //navigate to the
    }, (error) => {
      this.invalidLoginAlert = true;
      setTimeout(() => {this.invalidLoginAlert=false},3000);
    });
  }

  //determines whether login form or register form displays
  onWantsNewAccount(userWantsNewAccount: boolean) {
    this.onCreateAccountForm = true;
  }

  //create new user
  onRegisterFormGroupSubmit(userRegisterInfo: Users) {
    //this is where we will use service to send to backend
    //for registration
    this.userPost.registerUser(userRegisterInfo).subscribe(result=>{
      this.onCreateAccountForm = false;
      this.successRegisterAlert = true;
      setTimeout(() => {this.successRegisterAlert=false},3000);
    },(error)=>{
      console.log(error.status);
      console.log("------------");
      if(error.status == 400) {
          this.registrationDuplicate = true;
          this.duplicateMessage= "Emailed already registered. Use another email";
          setTimeout(()=>{this.registrationDuplicate=false},3000);
      } else if(error.status == 409) {
          this.registrationDuplicate = true;
          this.duplicateMessage= "Username already registered. Use another username";
          setTimeout(()=>{this.registrationDuplicate=false},3000);
      } else if(error.status == 424) {
          this.registrationDuplicate = true;
          this.duplicateMessage= "You must've already registered, because both email and username has been used already!";
          setTimeout(()=>{this.registrationDuplicate=false},3000);
      } else {
          this.notsuccessRegisterAlert = true;
          setTimeout(() => {this.notsuccessRegisterAlert=false},3000);
      }
    })
  }
}
