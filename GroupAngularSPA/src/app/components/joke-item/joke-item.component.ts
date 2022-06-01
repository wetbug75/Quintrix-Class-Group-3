import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import {Joke} from '../../Joke';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-joke-item',
  templateUrl: './joke-item.component.html',
  styleUrls: ['./joke-item.component.css']
})
export class JokeItemComponent implements OnInit {
  @Input() joke: Joke;


  question: string;
  answer: string;

  constructor() { }

  ngOnInit(): void {
  }

  setQuestion(question2: string){
    this.question = question2;
    document.getElementById("jokeQuestion").innerHTML = this.question;
  }

  setAnswer(answer2: string){
    this.answer = answer2;
    document.getElementById("jokeAnswer").innerHTML = this.answer;
  }
}
