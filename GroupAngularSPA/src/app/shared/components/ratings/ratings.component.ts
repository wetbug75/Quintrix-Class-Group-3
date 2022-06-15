import { Component, OnInit } from '@angular/core';
import { JokePostService } from 'src/app/core/services/JokePOST/joke-post.service';
import { Joke } from 'src/app/models/Joke';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';
import { JokeService } from 'src/app/core/services/joke.service';
import { JokeGetService } from 'src/app/core/services/JokeGET/joke-get.service';

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css']
})
export class RatingsComponent implements OnInit {

  likeCount: number;
  dislikeCount: number;
  tempJokeID: number = -1;
  tempJoke: Joke;
  likeButtonTapped: boolean;
  dislikeButtonTapped: boolean;



  constructor(
    private jokePostSer: JokePostService,
    private jokeItem: JokeItemComponent,
    private JokeGetSer: JokeGetService) {
    this.likeButtonTapped = false;
    this.dislikeButtonTapped = false;
  }

  ngOnInit(): void {
  }
   UpvoteTapped(){
    this.tempJokeID = this.JokeGetSer.GetCurrentJokeID();
    this.tempJoke = this.JokeGetSer.GetCurrentJoke();
    this.tempJokeID = this.tempJoke.id;

    if(this.likeButtonTapped == true){
      console.log("User undo the upvote.");
      this.likeCount = this.tempJoke.upvotes - 1;
    }
    else if (this.likeButtonTapped == false){
      console.log("User upvoted the joke.");
      this.likeCount = this.tempJoke.upvotes + 1;
    }

    this.tempJoke.upvotes = this.likeCount;
    this.jokePostSer.UpdateLikeCount(this.tempJoke, this.tempJokeID);

    this.likeButtonTapped = !this.likeButtonTapped;
    this.SetLikeCount(this.likeCount);
  }

  DownvoteTapped(){
    this.tempJokeID = this.JokeGetSer.GetCurrentJokeID();
    this.tempJoke = this.JokeGetSer.GetCurrentJoke();
    this.tempJokeID = this.tempJoke.id;

    if(this.dislikeButtonTapped == true){
      this.dislikeCount = this.tempJoke.downvotes - 1;
    }

    else if(this.dislikeButtonTapped == false){
      this.dislikeCount = this.tempJoke.downvotes + 1;
    }

    this.tempJoke.downvotes = this.dislikeCount;
    this.jokePostSer.UpdateDislikeCount(this.tempJoke, this.tempJokeID);
    this.dislikeButtonTapped = !this.dislikeButtonTapped;
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
}
