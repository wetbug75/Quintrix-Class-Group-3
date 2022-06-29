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

  constructor(private jokeService: JokeService, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.lastId = -1;
    this.upclick = false;
    this.downclick = false;
    this.hasVoted = false;
    this.jokeService.getJokeDatabaseSize().subscribe(Response => {
      this.jokeSize = Response;
    });
    this.userLoggedIn = this.authService.isUserLoggedIn();
    if(this.userLoggedIn == true) {
      document.getElementById("loginMsg").style.visibility = "hidden";
    }
  }

  getRandomJoke() {
    this.noRepeat = false;
    // While loop to ensure that id is not the same as the lastId
    while(this.noRepeat == false)
    {
      this.randomId = Math.floor(Math.random() * this.jokeSize) + 1;
      if(this.lastId == -1 || this.lastId != this.randomId) {
        this.lastId = this.randomId;
        this.noRepeat = true;
      }
    }
    this.jokeService.getRandomJoke(this.randomId);
    this.jokeService.getUserVote(this.jokeService.getUserID(), this.randomId).subscribe(Response =>{
      if(Response == "NONE") {
          this.setHasVoted(false);
          this.setLikeClass(false);
          this.setDislikeClass(false);
      } else if(Response == "UPVOTE") {
          this.setHasVoted(true);
          this.setLikeClass(true);
          this.setDislikeClass(false);
      } else if (Response == "DOWNVOTE") {
          this.setHasVoted(true);
          this.setLikeClass(false);
          this.setDislikeClass(true);
      }
    });
  }

  upvoteTapped() {
    // If statement to catch if the user has logged in
    if(this.authService.isUserLoggedIn() == false){
        document.getElementById("loginMsg").style.visibility = "visible";
    } else if(this.jokeService.getJokeID() != null) {
        if(this.getDislikeClass() == true && this.getHasVoted() == true) {
          this.undoDownvote();
        }
        this.setLikeClass(!this.getLikeClass());
        if(this.getLikeClass() == true) {
            this.tempJoke = this.jokeService.getJoke();
            this.tempJokeID = this.tempJoke.id;
            this.voteStat = this.checkVoteStat();
            this.likeCount = this.tempJoke.upvotes + 1;
            this.setHasVoted(true);
            this.tempJoke.upvotes = this.likeCount;
            this.jokeService.updateUpvote(this.tempJoke, this.likeCount, this.tempJokeID);
            this.jokeService.updateUserJokeVote(this.jokeService.getUserID(), this.tempJokeID, this.voteStat);
        } else {
            this.undoUpvote();
      }
    }
  }

  undoUpvote() {
    if(this.authService.isUserLoggedIn() == false){
      document.getElementById("loginMsg").style.visibility = "visible";
    } else if(this.jokeService.getJokeID() != null) {
        this.setLikeClass(false);
        if(this.getLikeClass() == false && this.getDislikeClass() == false) {
            this.setHasVoted(false);
        }
        this.tempJoke = this.jokeService.getJoke();
        this.tempJokeID = this.tempJoke.id;
        this.voteStat = this.checkVoteStat();
        this.likeCount = this.tempJoke.upvotes - 1;
        this.tempJoke.upvotes = this.likeCount;
        this.jokeService.updateUpvote(this.tempJoke, this.likeCount, this.tempJokeID);
        this.jokeService.updateUserJokeVote(this.jokeService.getUserID(), this.tempJokeID, this.voteStat);
    }
  }

  downvoteTapped() {
    // If statement to catch if the user has logged in
    if(this.authService.isUserLoggedIn() == false){
      document.getElementById("loginMsg").style.visibility = "visible";
    } else if(this.jokeService.getJokeID() != null){
        if(this.getLikeClass() == true && this.getHasVoted() == true) {
          this.undoUpvote();
        }
        this.setDislikeClass(!this.getDislikeClass());
        if(this.getDislikeClass() == true) {
          this.tempJoke = this.jokeService.getJoke();
          this.tempJokeID = this.tempJoke.id;
          this.voteStat = this.checkVoteStat();
          this.dislikeCount = this.tempJoke.downvotes + 1;
          this.setHasVoted(true);
          this.tempJoke.downvotes = this.dislikeCount;
          this.jokeService.updateDownvote(this.tempJoke, this.dislikeCount, this.tempJokeID);
          this.jokeService.updateUserJokeVote(this.jokeService.getUserID(), this.tempJokeID, this.voteStat);
        } else {
            this.undoDownvote();
        }
    }
  }

  undoDownvote() {
    if(this.authService.isUserLoggedIn() == false){
      document.getElementById("loginMsg").style.visibility = "visible";
    } else if(this.jokeService.getJokeID() != null){
        this.setDislikeClass(false);
        if(this.getLikeClass() == false && this.getDislikeClass() == false){
          this.setHasVoted(false);
        }
        this.tempJoke = this.jokeService.getJoke();
        this.tempJokeID = this.tempJoke.id;
        this.voteStat = this.checkVoteStat();
        this.dislikeCount = this.tempJoke.downvotes - 1;
        this.tempJoke.downvotes = this.dislikeCount;
        this.jokeService.updateDownvote(this.tempJoke, this.dislikeCount, this.tempJokeID);
        this.jokeService.updateUserJokeVote(this.jokeService.getUserID(), this.tempJokeID, this.voteStat);
    }
  }

  setLikeClass(value: boolean) {
    this.upclick = value;
  }

  getLikeClass() {
    return this.upclick;
  }

  setDislikeClass(value: boolean) {
    this.downclick = value;
  }

  getDislikeClass() {
    return this.downclick;
  }

  setHasVoted(value: boolean) {
    this.hasVoted = value;
  }

  getHasVoted() {
    return this.hasVoted;
  }

  checkVoteStat() {
    if(this.getLikeClass() == true && this.getDislikeClass() == false) {
      return "UPVOTE";
    } else if (this.getLikeClass() == false && this.getDislikeClass() == true) {
      return "DOWNVOTE";
    } else if (this.getLikeClass() == false && this.getDislikeClass() == false) {
      return "NONE";
    } else {
      console.log("Unexpected error: Both upvote and downvote are pressed?");
      return null;
    }
  }
}
