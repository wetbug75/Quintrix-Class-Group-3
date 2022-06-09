import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { RegisterUser } from 'src/app/models/Register';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  @Output() public onRegisterFormGroupSubmit = new EventEmitter<RegisterUser>();
  userRegisterForm?: FormGroup;
  userRegisterData: RegisterUser;
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
