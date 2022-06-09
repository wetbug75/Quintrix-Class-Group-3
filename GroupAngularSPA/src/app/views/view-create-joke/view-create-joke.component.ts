import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CreateStateServiceService } from 'src/app/views/view-create-joke/services/create-state-service.service';
import { JokeService } from 'src/app/core/services/joke.service';
import { Status } from 'src/app/shared/status';
import { newJoke } from 'src/app/models/newJoke';
@Component({
  selector: 'app-view-create-joke',
  templateUrl: './view-create-joke.component.html',
  styleUrls: ['./view-create-joke.component.css']
})
export class ViewCreateJokeComponent implements OnInit {
  backEndResponseStatus!:Status;
  
  SubmittedForm!: boolean;
  constructor(private jokeService : JokeService, public createFormService: CreateStateServiceService) { }

  ngOnInit(): void {
    this.backEndResponseStatus = Status.None;
    this.createFormService.needForm= true;
  
  }

  onSubmitCreateJoke(newJokeData : newJoke ){
    this.createFormService.needForm = false;
    this.jokeService.postJoke(newJokeData).subscribe((response)=>{
      console.log("insisde response");
      console.log(response);
      this.backEndResponseStatus = Status.Success;
    },
    (error: HttpErrorResponse)=>{
      this.backEndResponseStatus = Status.Fail;
    }
    )
  }
}
