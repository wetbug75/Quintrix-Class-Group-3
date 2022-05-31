import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import {Joke} from '../../Joke';


@Component({
  selector: 'app-joke-item',
  templateUrl: './joke-item.component.html',
  styleUrls: ['./joke-item.component.css']
})
export class JokeItemComponent implements OnInit {
  @Input() joke: Joke;
  @Output() getRandomJoke: EventEmitter<Joke> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  getRandom(joke){
    this.getRandomJoke.emit(joke);
  }
}
