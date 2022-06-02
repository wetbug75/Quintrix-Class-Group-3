import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { JokeService } from '../../../../core/services/joke.service';
import { Joke } from 'src/app/models/Joke';


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
