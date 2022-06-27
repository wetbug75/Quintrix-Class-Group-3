import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Users } from 'src/app/models/User';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  @Output() public onUserFormGroupSubmit = new EventEmitter<Users>();
  submitted: boolean;
  userLoginForm?: FormGroup;
  userLoginData: Users;
  redmessage: string;
  constructor(private fb: FormBuilder) {
    this.submitted= false;
    this.redmessage="Required";
   }

  ngOnInit(): void {
     this.initializeForm();
  }

  initializeForm():void{
    this.userLoginForm = this.fb.group({
      userName: ['',Validators.required],
      password: ['',Validators.required]
    });
  }

  get userName(){
    return this.userLoginForm.get('userName');
  }

  get password(){
    return this.userLoginForm.get('password');
  }

  onSubmit():void{
    if(this.userLoginForm.invalid){
      this.submitted=true;
      console.log(this.submitted);
      
       return;
    }
    this.userLoginData = {username: this.userLoginForm.value.userName,
                          password: this.userLoginForm.value.password }
   
    this.onUserFormGroupSubmit.emit(this.userLoginData);
  }

}
