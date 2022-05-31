import { Component, OnInit, Output, EventEmitter, SimpleChange, OnChanges, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-create-joke-form',
  templateUrl: './create-joke-form.component.html',
  styleUrls: ['./create-joke-form.component.css']
})
export class CreateJokeFormComponent implements OnInit {
  @Output() private onFormGroupSubmit = new EventEmitter<FormGroup>();
  @Output() private createButtonClicked = new EventEmitter<boolean>();
  @Input() checkChange!: boolean;
  newJokeForm!: FormGroup;
  isFormSubmitted!: boolean;;
  
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
    this.isFormSubmitted = false;
  }
  
  initializeForm(): void{
    this.newJokeForm = this.fb.group({
      question: '',
      answer: '',
      createdBy: ''
    });
  }

  onSubmit():void{
    this.isFormSubmitted= true;
    this.createButtonClicked.emit(this.isFormSubmitted);
    this.onFormGroupSubmit.emit(this.newJokeForm);

  }
  

}
