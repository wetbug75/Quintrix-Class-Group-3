import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { JokePostService } from 'src/app/core/services/JokePOST/joke-post.service';
import { Joke } from 'src/app/models/Joke';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';
import { JokeService } from 'src/app/core/services/joke.service';
import { JokeGetService } from 'src/app/core/services/JokeGET/joke-get.service';


@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RatingsComponent implements OnInit {

  likeCount: number;
  dislikeCount: number;
  tempJokeID: number = -1;
  tempJoke: Joke;
  upclick: boolean;
  downclick: boolean;


  constructor(
    private jokePostSer: JokePostService,
    private jokeItem: JokeItemComponent,
    private JokeGetSer: JokeGetService) {
      this.upclick = false;
      this.downclick = false;

  }

  ngOnInit(): void {
    this.upclick = false;
    this.downclick = false;
  }

   UpvoteTapped(){
    this.tempJokeID = this.JokeGetSer.GetCurrentJokeID();
    this.tempJoke = this.JokeGetSer.GetCurrentJoke();
    this.tempJokeID = this.tempJoke.id;
    console.log(this.likeCount);

    //Toggle class
    this.SetLikeClass(!this.GetLikeClass());

    if(this.GetLikeClass() == true){
      this.likeCount = this.tempJoke.upvotes + 1;
    }
    else{
      this.likeCount = this.tempJoke.upvotes - 1;
    }

    this.tempJoke.upvotes = this.likeCount;
    this.jokePostSer.UpdateLikeCount(this.tempJoke, this.tempJokeID);

    this.SetLikeCount(this.likeCount);
  }

  DownvoteTapped(){
    this.tempJokeID = this.JokeGetSer.GetCurrentJokeID();
    this.tempJoke = this.JokeGetSer.GetCurrentJoke();
    this.tempJokeID = this.tempJoke.id;

    this.SetDislikeClass(!this.GetDislikeClass());

    if(this.GetDislikeClass() == true){
      this.dislikeCount = this.tempJoke.downvotes + 1;
    }

    else{
      this.dislikeCount = this.tempJoke.downvotes - 1;
    }
    this.tempJoke.downvotes = this.dislikeCount;
    this.jokePostSer.UpdateDislikeCount(this.tempJoke, this.tempJokeID);

    this.SetDislikeCount(this.dislikeCount);
  }

  SetLikeCount(jokeLike: number){
    this.likeCount = jokeLike;
    document.getElementById("thumbUpCount").innerHTML = this.likeCount.toString();
  }

  SetDislikeCount(jokeDislike: number){
    this.dislikeCount = jokeDislike;
    document.getElementById("thumbDownCount").innerHTML = this.dislikeCount.toString();
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
