import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Joke } from 'src/app/models/Joke';

import { JOKES } from '../../mock-joke';
import { JokeService } from 'src/app/core/services/joke.service';



@Component({
  selector: 'app-randomizer',
  templateUrl: './randomizer.component.html',
  styleUrls: ['./randomizer.component.css']
})

export class RandomizerComponent implements OnInit {
  @Output() randomNumberEvent = new EventEmitter<number>();
    
  joke: Joke[];
  id: number;
  question: string;
  answer: string;

  constructor(private jokeService: JokeService) {

   }

  ngOnInit(): void {
  }

  getRandomJoke(){
    this.id = Math.floor(Math.random() * JOKES.length);

    this.jokeService
    .getJoke(this.id)
    .subscribe((Response) => {console.log(Response), this.question = Response.question, this.answer = Response.answer});

    this.jokeService.SendQuestion(this.question);
    this.jokeService.SendAnswer(this.answer);
  }

}
