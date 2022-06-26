import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import {Joke} from '../../../../models/Joke';


@Component({
  selector: 'app-joke-item',
  templateUrl: './joke-item.component.html',
  styleUrls: ['./joke-item.component.css']
})
export class JokeItemComponent implements OnInit {
  @Input() joke: Joke;

  jokeID: number;
  question: string;
  answer: string;
  currentJoke: Joke;
  upvote: number;
  downvote: number;

  constructor() {
    this.jokeID = null;
  }

  ngOnInit(): void {
  }

  SetQuestion(question2: string){
    this.question = question2;
    document.getElementById("jokeQuestion").innerHTML = this.question;
  }

  SetAnswer(answer2: string){
    this.answer = answer2;
    document.getElementById("jokeAnswer").innerHTML = this.answer;
  }

  SetJokeID(currentJokeID: number){
    this.jokeID = currentJokeID;
  }

  GetJokeID(){
    if(this.jokeID == null){
      return null;
    }
    else{
      return this.jokeID;
    }
  }

  SetJoke(cJoke: Joke){
    this.currentJoke = cJoke;
  }

  SetUpvote(upvoteCount: number){
    this.upvote = upvoteCount;

  }

  SetDownvote(downvoteCount: number){
    this.downvote = downvoteCount;
  }

  GetJoke(){
    return this.currentJoke;
  }

  GetQuestion(){
    return this.question;
  }

  GetAnswer(){
    return this.answer;
  }

  GetUpvote(){
    return this.upvote;
  }

  GetDownvote(){
    return this.downvote;
  }
}
