import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CreateStateServiceService } from 'src/app/core/services/create-state-service.service';
import { JokeService } from 'src/app/core/services/joke.service';
import { Status } from 'src/app/shared/status';
import { CreateJoke } from 'src/app/models/CreateJoke';
@Component({
  selector: 'app-view-create-joke',
  templateUrl: './view-create-joke.component.html',
  styleUrls: ['./view-create-joke.component.css']
})
export class ViewCreateJokeComponent implements OnInit {
  backEndResponse!:Status;
  SubmittedForm!: boolean;
  constructor(private jokeService : JokeService, public createForm: CreateStateServiceService) { }

  ngOnInit(): void {
    this.backEndResponse = Status.None;
    this.createForm.needForm= true;
  
  }

  onSubmitCreateJoke(newJoke : CreateJoke ){
    this.createForm.needForm = false;
    this.jokeService.postJoke(newJoke).subscribe((response)=>{
      this.backEndResponse = Status.Success;
    },
    (error: HttpErrorResponse)=>{
      this.backEndResponse = Status.Fail;
    }
    )
  }
}
