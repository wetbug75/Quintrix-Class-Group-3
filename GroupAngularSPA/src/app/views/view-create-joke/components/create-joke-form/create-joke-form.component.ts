import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-create-joke-form',
  templateUrl: './create-joke-form.component.html',
  styleUrls: ['./create-joke-form.component.css']
})
export class CreateJokeFormComponent implements OnInit {
  @Output() private onFormGroupSubmit = new EventEmitter<FormGroup>();
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
    
    this.onFormGroupSubmit.emit(this.newJokeForm);

  }

}
