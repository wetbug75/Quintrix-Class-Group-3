import { Component, OnInit, Output, EventEmitter, SimpleChange, OnChanges, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Joke } from 'src/app/models/Joke';
@Component({
  selector: 'app-create-joke-form',
  templateUrl: './create-joke-form.component.html',
  styleUrls: ['./create-joke-form.component.css']
})
export class CreateJokeFormComponent implements OnInit {
  @Output() public onFormGroupSubmit = new EventEmitter<Joke>();
  newJokeForm!: FormGroup;
  newJokeData: Joke;
  
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
    
  }
  
  initializeForm(): void{
    this.newJokeForm = this.fb.group({
      question: '',
      answer: ''
    });
  }

  onSubmit():void{
    this.newJokeData = {answer:this.newJokeForm.value.answer, question: this.newJokeForm.value.question}
    
    this.onFormGroupSubmit.emit(this.newJokeData);

  }
  

}
