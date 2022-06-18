import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserPostService } from 'src/app/core/services/UserPOST/user-post.service';
import { Users } from 'src/app/models/User';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';
@Component({
  selector: 'app-view-login',
  templateUrl: './view-login.component.html',
  styleUrls: ['./view-login.component.css']
})
export class ViewLoginComponent implements OnInit {
  onCreateAccountForm:boolean;

  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService, public userPost: UserPostService) { 
    this.onCreateAccountForm = false;
  }

  ngOnInit(): void {
  }

  //submit login credentials to backend
  onUserFormGroupSubmit(userLogin : Users){
    console.log(userLogin);
    //this is where we will use service to send the user and password
    //TODO
    this.authenticationService.authenticationService(userLogin.username, userLogin.password).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['login']); //navigate to the 
      console.log("SUCCESS LOGIN");
    }, (error) => {
      this.invalidLogin = true;
      this.loginSuccess = false;
      console.log("UNSUCESSFUL LOGIN");
    });  

  }

  //determines whether login form or register form displays
  onWantsNewAccount(userWantsNewAccount: boolean){
    this.onCreateAccountForm = true;
  }

  //create new user 
  onRegisterFormGroupSubmit(userRegisterInfo: Users){
    console.log(userRegisterInfo)
     console.log("INSIDE NEW USER");
    //this is where we will use service to send to backend
    //for registration
    this.userPost.registerUser(userRegisterInfo).subscribe(result=>{
      console.log(result);
    })
  }
  
}
