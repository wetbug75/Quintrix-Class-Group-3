import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {Joke} from '../../../../Joke';
import {JOKES} from '../../../../mock-joke';

@Component({
  selector: 'app-randomizer',
  templateUrl: './randomizer.component.html',
  styleUrls: ['./randomizer.component.css']
})
export class RandomizerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  getRandomJoke(){
    
  }
}
