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
  lastId: number;
  noRepeat: boolean;
  constructor(private jokeService: JokeService) {
      this.lastId = -1;
   }

  ngOnInit(): void {
  }

  getRandomJoke(){
    this.noRepeat = false;
    // Checks if the random number is the same as the last number
    while(this.noRepeat == false)
    {
      this.id = Math.floor(Math.random() * JOKES.length);
      console.log("Number: " + this.id);
      if(this.lastId == -1 || this.lastId != this.id)
      {
        this.lastId = this.id;
        this.noRepeat = true;
      }
    }


    /*
    this.jokeService.getJokeQuestion(this.id).subscribe(Response => {
      console.log(Response);
      this.jokeService.SendQuestion(Response);
    })

    this.jokeService.getJokeAnswer(this.id).subscribe(Response => {
      this.jokeService.SendAnswer(Response);
    });
    */
    
    this.jokeService
    .getJoke(this.id)
    .subscribe(Response => {
      console.log(Response);
      this.jokeService.SendQuestion(Response.question); 
      this.jokeService.SendAnswer(Response.answer);
    });
    
  }

}
