import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css']
})
export class RatingsComponent implements OnInit {

  likeCount: number;
  dislikeCount: number;

  constructor() { }

  ngOnInit(): void {
  }

  SetLikeCount(jokeLike: number)
  {
    this.likeCount = jokeLike;
    document.getElementById("thumbUpCount").innerHTML = this.likeCount.toString();
  }

  SetDislikeCount(jokeDislike: number)
  {
    this.dislikeCount = jokeDislike;
    document.getElementById("thumbDownCount").innerHTML = this.dislikeCount.toString();
  }

}
