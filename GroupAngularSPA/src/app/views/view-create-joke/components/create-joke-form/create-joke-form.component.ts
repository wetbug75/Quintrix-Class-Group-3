import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-create-joke-form',
  templateUrl: './create-joke-form.component.html',
  styleUrls: ['./create-joke-form.component.css']
})
export class CreateJokeFormComponent implements OnInit {
  
  newJokeForm!: FormGroup;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void{
    this.newJokeForm = this.fb.group({
      joke: '',
      answer: ''
    });
  }

  onSubmit():void{
    console.log(this.newJokeForm);
    console.log(this.newJokeForm.value);
  }

}
