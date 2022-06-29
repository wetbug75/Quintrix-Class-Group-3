import { Component, OnInit, Input } from '@angular/core';
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

  ngOnInit(): void { }

  setQuestion(question2: string) {
    this.question = question2;
    document.getElementById("jokeQuestion").innerHTML = this.question;
  }

  setAnswer(answer2: string) {
    this.answer = answer2;
    document.getElementById("jokeAnswer").innerHTML = this.answer;
  }

  setJokeID(currentJokeID: number) {
    this.jokeID = currentJokeID;
  }

  getJokeID() {
    if(this.jokeID == null) {
        return null;
    } else {
        return this.jokeID;
    }
  }

  setJoke(cJoke: Joke) {
    this.currentJoke = cJoke;
  }

  setUpvote(upvoteCount: number) {
    this.upvote = upvoteCount;
  }

  setDownvote(downvoteCount: number) {
    this.downvote = downvoteCount;
  }

  getJoke() {
    return this.currentJoke;
  }

  getQuestion() {
    return this.question;
  }

  getAnswer() {
    return this.answer;
  }

  getUpvote() {
    return this.upvote;
  }

  getDownvote() {
    return this.downvote;
  }
}
