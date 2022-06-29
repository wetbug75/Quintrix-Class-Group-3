import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Users } from 'src/app/models/User';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  alertMessage: boolean;
  invalidEmailMessage: string = "Email is invalid";
  requiredInput : string = "Required";
  minLength : string = "Must be over 6 characters";
  @Output() public onRegisterFormGroupSubmit = new EventEmitter<Users>();
  userRegisterForm?: FormGroup;
  userRegisterData: Users;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
    this.alertMessage=false;
  }
  initializeForm():void {
    this.userRegisterForm = this.fb.group({
      //username: '',
      username: new FormControl('', [Validators.required, Validators.minLength(6)]),
      //password:'',
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      email: new FormControl('', [Validators.required, Validators.email]),
    });
  }

  onSubmit():void {
    if(this.email.invalid || this.username.invalid || this.password.invalid){
      this.showAlertInvalidForm();
      return;
    }
    this.userRegisterData = {username: this.userRegisterForm.value.username,
                          password: this.userRegisterForm.value.password,
                          email: this.userRegisterForm.value.email,
                           id: 0 } //set id to 0 because backend will auto generate new key. constructor hasn't been implemented yet to handle null
    this.onRegisterFormGroupSubmit.emit(this.userRegisterData);
  }

  showAlertInvalidForm() {
    this.alertMessage = true;
    setTimeout(()=>this.alertMessage=false,4000);
  }

  get username() {
    return this.userRegisterForm.get('username');
  }
  get password() {
    return this.userRegisterForm.get('password');
  }
  get email() {
    return this.userRegisterForm.get('email');
  }
}
