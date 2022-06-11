import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from 'src/app/models/User';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  @Output() public onRegisterFormGroupSubmit = new EventEmitter<User>();
  userRegisterForm?: FormGroup;
  userRegisterData: User;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
  }
  initializeForm():void{
    this.userRegisterForm = this.fb.group({
      userName: '',
      password:'',
      email:''
    });
  }

  onSubmit():void{
    this.userRegisterData = {userName: this.userRegisterForm.value.userName,
                          password: this.userRegisterForm.value.password,
                          email: this.userRegisterForm.value.email }
    this.onRegisterFormGroupSubmit.emit(this.userRegisterData);
  }
}
