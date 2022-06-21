import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Users } from 'src/app/models/User';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  @Output() public onRegisterFormGroupSubmit = new EventEmitter<Users>();
  userRegisterForm?: FormGroup;
  userRegisterData: Users;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
  }
  initializeForm():void{
    this.userRegisterForm = this.fb.group({
      //username: '',
      username: new FormControl('', Validators.required),
      //password:'',
      password: new FormControl('', Validators.required),
      email:'',
    });
  }

  onSubmit():void{
    this.userRegisterData = {username: this.userRegisterForm.value.username,
                          password: this.userRegisterForm.value.password,
                          email: this.userRegisterForm.value.email,
                           id: 0 } //set id to 0 because backend will auto generate new key. constructor hasn't been implemented yet to handle null
    this.onRegisterFormGroupSubmit.emit(this.userRegisterData);
  }
}
