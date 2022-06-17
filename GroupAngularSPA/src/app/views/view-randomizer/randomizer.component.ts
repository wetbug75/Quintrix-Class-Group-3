import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
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

  likeCount: number;
  dislikeCount: number;
  tempJokeID: number = -1;
  tempJoke: Joke;
  upclick: boolean;
  downclick: boolean;



  constructor(private jokeService: JokeService) {

   }

  ngOnInit(): void {
    this.lastId = -1;
    this.upclick = false;
    this.downclick = false;
    this.jokeService.GetJokeDatabaseSize().subscribe(Response => {
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
    this.jokeService.GetRandomJoke(this.randomId);
    this.ResetButtonDisplay();
  }

  UpvoteTapped(){
    this.tempJoke = this.jokeService.GetJoke();
    this.tempJokeID = this.tempJoke.id;

    //Toggle HTML Class
    this.SetLikeClass(!this.GetLikeClass());

    if(this.GetLikeClass() == true){
      this.likeCount = this.tempJoke.upvotes + 1;
    }
    else{
      this.likeCount = this.tempJoke.upvotes - 1;
    }

    this.tempJoke.upvotes = this.likeCount;
    this.jokeService.UpdateUpvote(this.tempJoke, this.likeCount, this.tempJokeID);
  }

  DownvoteTapped(){
    this.tempJoke = this.jokeService.GetJoke();
    this.tempJokeID = this.tempJoke.id;

    // Toggle HTML Class
    this.SetDislikeClass(!this.GetDislikeClass());

    if(this.GetDislikeClass() == true){
      this.dislikeCount = this.tempJoke.downvotes + 1;
    }

    else{
      this.dislikeCount = this.tempJoke.downvotes - 1;
    }
    this.tempJoke.downvotes = this.dislikeCount;
    this.jokeService.UpdateDownvote(this.tempJoke, this.dislikeCount, this.tempJokeID);
  }

  SetLikeClass(value: boolean){
    console.log(this.upclick);
    this.upclick = value;
  }

  GetLikeClass(){
    return this.upclick;
  }

  SetDislikeClass(value: boolean){
    this.downclick = value;
  }

  GetDislikeClass(){
    return this.downclick;
  }

  ResetButtonDisplay(){
    this.SetLikeClass(false);
    this.SetDislikeClass(false);
  }
}
