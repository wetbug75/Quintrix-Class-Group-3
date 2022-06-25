import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Joke } from 'src/app/models/Joke';
import { JokeService } from 'src/app/core/services/joke.service';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';

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
  userLoggedIn: boolean;
  likeCount: number;
  dislikeCount: number;
  tempJokeID: number = -1;
  tempJoke: Joke;
  upclick: boolean;
  downclick: boolean;
  hasVoted: boolean;

  voteStat: string;

  constructor(private jokeService: JokeService, private authService: AuthenticationService) {

   }

  ngOnInit(): void {
    this.lastId = -1;
    this.upclick = false;
    this.downclick = false;
    this.hasVoted = false;
    this.jokeService.GetJokeDatabaseSize().subscribe(Response => {
      this.jokeSize = Response;
    });
    this.userLoggedIn = this.authService.isUserLoggedIn();
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
    this.jokeService.GetUserVote(this.jokeService.GetUserID(), this.randomId).subscribe(Response =>{
      if(Response == "NONE")
      {
        this.SetHasVoted(false);
        this.SetLikeClass(false);
        this.SetDislikeClass(false);
      } else if(Response == "UPVOTE"){
          this.SetHasVoted(true);
          this.SetLikeClass(true);
          this.SetDislikeClass(false);
      } else if (Response == "DOWNVOTE"){
          this.SetHasVoted(true);
          this.SetLikeClass(false);
          this.SetDislikeClass(true);
      }
    });
  }

  UpvoteTapped(){
    // If statement to catch if the user has logged in
    if(this.authService.isUserLoggedIn() == false){
      console.log("Upvote button clicked, but no user is logged in.");
      document.getElementById("loginMsg").style.visibility = "visible";
    } else{
        /**
         *  Check if the user has an active downvote.
         *  If true, toggle the downvote off and update the database before proceeding
         **/
        if(this.GetDislikeClass() == true && this.GetHasVoted() == true){
          console.log("User has an existing downvote. Undoing downvote first");
          this.UndoDownvote();
        }
        /**
         * Toggle the upvote status
         * If new status is true, user has upvoted
         * If new status is false, user undo the upvote
         */
        this.SetLikeClass(!this.GetLikeClass());
        if(this.GetLikeClass() == true) {
          this.tempJoke = this.jokeService.GetJoke();
          this.tempJokeID = this.tempJoke.id;
          this.voteStat = this.CheckVoteStat();
          this.likeCount = this.tempJoke.upvotes + 1;
          this.SetHasVoted(true);
          this.tempJoke.upvotes = this.likeCount;
          this.jokeService.UpdateUpvote(this.tempJoke, this.likeCount, this.tempJokeID);
          this.jokeService.UpdateUserJokeVote(this.jokeService.GetUserID(), this.tempJokeID, this.voteStat);
        }
        else {
          console.log("Undoing upvote");
          this.UndoUpvote();
        }
     }
  }

  UndoUpvote() {
    if(this.authService.isUserLoggedIn() == false){
      console.log("Upvote button clicked, but no user is logged in.");
      document.getElementById("loginMsg").style.visibility = "visible";
    } else {
        //Toggle HTML Class
        this.SetLikeClass(false);
        if(this.GetLikeClass() == false && this.GetDislikeClass() == false){
          console.log("UndoUpvote sees no vote now.");
          this.SetHasVoted(false);
        }
        this.tempJoke = this.jokeService.GetJoke();
        this.tempJokeID = this.tempJoke.id;
        this.voteStat = this.CheckVoteStat();
        this.likeCount = this.tempJoke.upvotes - 1;
        this.tempJoke.upvotes = this.likeCount;
        this.jokeService.UpdateUpvote(this.tempJoke, this.likeCount, this.tempJokeID);
        this.jokeService.UpdateUserJokeVote(this.jokeService.GetUserID(), this.tempJokeID, this.voteStat);
    }
  }

  DownvoteTapped(){
    // If statement to catch if the user has logged in
    if(this.authService.isUserLoggedIn() == false){
      console.log("Downvote button clicked, but no user is logged in.");
      document.getElementById("loginMsg").style.visibility = "visible";
    } else {
        /**
         *  Check if the user has an active upvote.
         *  If true, toggle the upvote off and update the database before proceeding
         **/
        if(this.GetLikeClass() == true && this.GetHasVoted() == true) {
          console.log("User has an existing upvote. Undoing upvote first.");
          this.UndoUpvote();
        }
        /**
         * Toggle the upvote status
         * If new status is true, user has upvoted
         * If new status is false, user undo the upvote
         */
        this.SetDislikeClass(!this.GetDislikeClass());
        if(this.GetDislikeClass() == true) {
          this.tempJoke = this.jokeService.GetJoke();
          this.tempJokeID = this.tempJoke.id;
          this.voteStat = this.CheckVoteStat();
          this.dislikeCount = this.tempJoke.downvotes + 1;
          this.SetHasVoted(true);
          this.tempJoke.downvotes = this.dislikeCount;
          this.jokeService.UpdateDownvote(this.tempJoke, this.dislikeCount, this.tempJokeID);
          this.jokeService.UpdateUserJokeVote(this.jokeService.GetUserID(), this.tempJokeID, this.voteStat);
        } else {
            console.log("Undoing downvote");
            this.UndoDownvote();
        }

    }
  }

  UndoDownvote() {
    if(this.authService.isUserLoggedIn() == false){
      console.log("Upvote button clicked, but no user is logged in.");
      document.getElementById("loginMsg").style.visibility = "visible";
    }
    else {
      this.SetDislikeClass(false);
      if(this.GetLikeClass() == false && this.GetDislikeClass() == false){
        console.log("UndoDownvote sees no vote now.");
        this.SetHasVoted(false);
      }
      this.tempJoke = this.jokeService.GetJoke();
      this.tempJokeID = this.tempJoke.id;
      this.voteStat = this.CheckVoteStat();
      this.dislikeCount = this.tempJoke.downvotes - 1;
      this.tempJoke.downvotes = this.dislikeCount;
      this.jokeService.UpdateDownvote(this.tempJoke, this.dislikeCount, this.tempJokeID);
      this.jokeService.UpdateUserJokeVote(this.jokeService.GetUserID(), this.tempJokeID, this.voteStat);
    }
  }


  SetLikeClass(value: boolean) {
    this.upclick = value;
  }

  GetLikeClass() {
    return this.upclick;
  }

  SetDislikeClass(value: boolean) {
    this.downclick = value;
  }

  GetDislikeClass() {
    return this.downclick;
  }

  SetHasVoted(value: boolean) {
    this.hasVoted = value;
  }

  GetHasVoted() {
    return this.hasVoted;
  }

  CheckVoteStat() {
    if(this.GetLikeClass() == true && this.GetDislikeClass() == false) {
      return "UPVOTE";
    } else if (this.GetLikeClass() == false && this.GetDislikeClass() == true) {
      return "DOWNVOTE";
    } else if (this.GetLikeClass() == false && this.GetDislikeClass() == false) {
      return "NONE";
    } else {
      console.log("Unexpected error: Both upvote and downvote are pressed?");
      return null;
    }
  }

}
