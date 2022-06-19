import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css'],

})
export class RatingsComponent implements OnInit {

  likeCount: number;
  dislikeCount: number;

  constructor() {

  }

  ngOnInit(): void {

  }


  SetLikeCount(jokeLike: number){
    document.getElementById("thumbUpCount").innerHTML = jokeLike.toString();
  }

  SetDislikeCount(jokeDislike: number){
    document.getElementById("thumbDownCount").innerHTML = jokeDislike.toString();
  }


}
