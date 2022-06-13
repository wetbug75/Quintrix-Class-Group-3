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
  jokeSize: number;
  constructor(private jokeService: JokeService) {
      this.lastId = -1;
   }

  ngOnInit(): void {
      this.jokeService.getJokeSize().subscribe(Response => {
          console.log("Joke size: " + Response.toString());
          this.jokeSize = Response;
        });

  }

  getRandomJoke(){
    this.noRepeat = false;
    // While loop to ensure that id is not the same as the lastId
    while(this.noRepeat == false)
    {
      this.id = Math.floor(Math.random() * this.jokeSize) + 1;
      console.log("Number: " + this.id);

      if(this.lastId == -1 || this.lastId != this.id)
      {
        this.lastId = this.id;
        this.noRepeat = true;
      }
    }

    this.jokeService.getJokeById(this.id).subscribe(Response => {
      console.log("Question: " + Response.question);
      this.jokeService.SendQuestion(Response.question);
      console.log("Answer: " + Response.answer);
      this.jokeService.SendAnswer(Response.answer);
      console.log("Upvotes: " + Response.upvotes);
      this.jokeService.SendLike(Response.upvotes);
      console.log("Downvotes: " + Response.downvotes);
      this.jokeService.SendDislike(Response.downvotes);
    })
  }

}
