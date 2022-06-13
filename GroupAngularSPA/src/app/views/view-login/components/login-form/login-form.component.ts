import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from 'src/app/models/User';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  @Output() public onUserFormGroupSubmit = new EventEmitter<User>();

  userLoginForm?: FormGroup;
  userLoginData: User;
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
    this.userLoginData = {userName: this.userLoginForm.value.userName,
                          password: this.userLoginForm.value.password }
    this.onUserFormGroupSubmit.emit(this.userLoginData);
  }

}
