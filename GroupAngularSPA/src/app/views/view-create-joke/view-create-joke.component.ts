import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CreateStateServiceService } from 'src/app/views/view-create-joke/services/create-state-service.service';
import { Joke } from 'src/app/models/Joke';
import { JokePostService } from 'src/app/core/services/JokePOST/joke-post.service';
import { Status } from 'src/app/models/status';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';
@Component({
  selector: 'app-view-create-joke',
  templateUrl: './view-create-joke.component.html',
  styleUrls: ['./view-create-joke.component.css']
})
export class ViewCreateJokeComponent implements OnInit {
  backEndResponseStatus!:Status;
  
  SubmittedForm!: boolean;
  constructor(private jokePostService : JokePostService, public createFormService: CreateStateServiceService , public authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.backEndResponseStatus = Status.None;
    this.createFormService.needForm= true;
    this.authenticationService.isUserLoggedIn();
  }

  onSubmitCreateJoke(newJokeData : Joke ){
    this.createFormService.needForm = false;
    this.jokePostService.postJoke(newJokeData).subscribe((response)=>{
      console.log("insisde response");
      console.log(response);
      this.backEndResponseStatus = Status.Success;
    },
    (error: HttpErrorResponse)=>{
      if(error.status === 401){
        this.backEndResponseStatus = Status.IsNotLoggedIn
      }else{
        this.backEndResponseStatus = Status.Fail;

      }
    }
    )
  }
}
