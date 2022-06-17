import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { JokeGetService } from 'src/app/core/services/JokeGET/joke-get.service';
import { JokeItemComponent } from './components/joke-item/joke-item.component';
import { RatingsComponent } from 'src/app/shared/components/ratings/ratings.component';
import { Joke } from 'src/app/models/Joke';
import { JokeService } from 'src/app/core/services/joke.service';

@Component({
  selector: 'app-randomizer',
  templateUrl: './randomizer.component.html',
  styleUrls: ['./randomizer.component.css']
})

export class RandomizerComponent implements OnInit {
  @Output() randomNumberEvent = new EventEmitter<number>();

  randomId: number;
  lastId: number;
  noRepeat: boolean;
  jokeSize: number;
  theJoke: Joke;
  constructor(private JokeService: JokeService, private JokeGetSer: JokeGetService, private jokeItem: JokeItemComponent, private jokeRating: RatingsComponent) {
      this.lastId = -1;
   }

  ngOnInit(): void {
      this.JokeGetSer.getJokeSize().subscribe(Response => {
          this.jokeSize = Response;
        });
  }

  getRandomJoke(){
    this.noRepeat = false;
    // While loop to ensure that id is not the same as the lastId
    while(this.noRepeat == false)
    {
      this.randomId = Math.floor(Math.random() * this.jokeSize) + 1;

      if(this.lastId == -1 || this.lastId != this.randomId)
      {
        this.lastId = this.randomId;
        this.noRepeat = true;
      }
    }

    this.JokeGetSer.getJokeById(this.randomId).subscribe(Response => {
      // Setter methods to respective component
      this.jokeItem.SetJoke(Response);
      this.jokeItem.SetJokeID(Response.id);
      this.jokeItem.SetQuestion(Response.question);
      this.jokeItem.SetAnswer(Response.answer);
      this.jokeItem.SetUpvote(Response.upvotes);
      this.jokeItem.SetDownvote(Response.downvotes);
      this.jokeRating.SetLikeCount(Response.upvotes);
      this.jokeRating.SetDislikeCount(Response.downvotes);
      this.jokeRating.ResetButtonDisplay();
    })
  }

}
