import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { JokeService } from '../../services/joke.service';
import {Joke} from '../../Joke';
import {JOKES} from '../../mock-joke';

@Component({
  selector: 'app-joke',
  templateUrl: './joke.component.html',
  styleUrls: ['./joke.component.css']
})
export class JokeComponent implements OnInit {
  @Output() onGetRandom: EventEmitter<Joke> = new EventEmitter();


  jokes: Joke[] = [];


  constructor(private jokeService: JokeService) { }

  ngOnInit(): void {
  }

}
