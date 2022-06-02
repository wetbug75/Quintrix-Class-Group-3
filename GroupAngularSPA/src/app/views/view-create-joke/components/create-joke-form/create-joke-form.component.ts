import { Component, OnInit, Output, EventEmitter, SimpleChange, OnChanges, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { newJoke } from 'src/app/models/newJoke';
@Component({
  selector: 'app-create-joke-form',
  templateUrl: './create-joke-form.component.html',
  styleUrls: ['./create-joke-form.component.css']
})
export class CreateJokeFormComponent implements OnInit {
  @Output() public onFormGroupSubmit = new EventEmitter<newJoke>();
  newJokeForm!: FormGroup;
 
  
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
    
  }
  
  initializeForm(): void{
    this.newJokeForm = this.fb.group({
      question: '',
      answer: '',
      createdBy: ''
    });
  }

  onSubmit():void{
    
     // answer: this.newJokeForm.value.answer,
    //  question: this.newJokeForm.value.question
    

    let newJokeData = new newJoke(this.newJokeForm.value.answer, this.newJokeForm.value.question);
    this.onFormGroupSubmit.emit(newJokeData);

  }
  

}
