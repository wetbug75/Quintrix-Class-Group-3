import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { JOKES } from '../../mock-joke';
import { JokeService } from 'src/app/core/services/joke.service';



@Component({
  selector: 'app-randomizer',
  templateUrl: './randomizer.component.html',
  styleUrls: ['./randomizer.component.css']
})

export class RandomizerComponent implements OnInit {
  @Output() randomNumberEvent = new EventEmitter<number>();
  
  id: number;

  constructor(private jokeService: JokeService) {

   }

  ngOnInit(): void {
  }

  getRandomJoke(){
    this.id = Math.floor(Math.random() * JOKES.length);

    this.jokeService
    .getJoke(this.id)
    .subscribe(Response => {
      console.log(Response);
      this.jokeService.SendQuestion(Response.question); 
      this.jokeService.SendAnswer(Response.answer);
      /*
      document.getElementById("jokeBox").style.visibility = "visible";
      */
    });
  }

}
