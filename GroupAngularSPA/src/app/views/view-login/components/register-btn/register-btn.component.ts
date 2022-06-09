import { Component, OnInit, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-register-btn',
  templateUrl: './register-btn.component.html',
  styleUrls: ['./register-btn.component.css']
})
export class RegisterBtnComponent implements OnInit {
  @Output() public onUserWantsNewAccount = new EventEmitter<boolean>(); 
  constructor() { }

  ngOnInit(): void {
  }
  onRegisterSubmit(){
    this.onUserWantsNewAccount.emit(true);
  }
}
