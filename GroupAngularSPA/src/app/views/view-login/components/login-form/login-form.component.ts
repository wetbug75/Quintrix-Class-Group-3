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

  userLoginForm?: FormGroup;
  userLoginData: Users;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
     this.initializeForm();
  }

  initializeForm():void{
    this.userLoginForm = this.fb.group({
      userName: '',
      password:''
    });
  }

  onSubmit():void{
    this.userLoginData = {username: this.userLoginForm.value.userName,
                          password: this.userLoginForm.value.password }
    this.onUserFormGroupSubmit.emit(this.userLoginData);
  }

}
