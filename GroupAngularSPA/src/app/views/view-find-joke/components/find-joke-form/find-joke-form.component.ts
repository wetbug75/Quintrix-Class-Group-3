import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-find-joke-form',
  templateUrl: './find-joke-form.component.html',
  styleUrls: ['./find-joke-form.component.css']
})
export class FindJokeFormComponent implements OnInit {
  @Output() keyWord = new EventEmitter<string>();
  inputKeyword: string;
  constructor() { }

  ngOnInit(): void {
    
  }
  onSubmit(){
    this.keyWord.emit(this.inputKeyword);

  }

}
