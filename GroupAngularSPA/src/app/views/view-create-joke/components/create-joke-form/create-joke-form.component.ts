import { Component, OnInit, Output, EventEmitter, SimpleChange, OnChanges, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CreateJoke } from 'src/app/models/CreateJoke';
@Component({
  selector: 'app-create-joke-form',
  templateUrl: './create-joke-form.component.html',
  styleUrls: ['./create-joke-form.component.css']
})
export class CreateJokeFormComponent implements OnInit {
  @Output() private onFormGroupSubmit = new EventEmitter<CreateJoke>();
  @Output() private createButtonClicked = new EventEmitter<boolean>();
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
    const newJoke = {
      answer: this.newJokeForm.value.answer,
      question: this.newJokeForm.value.question
    }
    this.onFormGroupSubmit.emit(newJoke);

  }
  

}
