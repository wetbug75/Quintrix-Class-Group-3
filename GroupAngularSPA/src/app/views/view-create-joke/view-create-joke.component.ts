import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
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
  constructor(private jokeService : JokeService) { }

  ngOnInit(): void {
    this.backEndResponse = Status.None;
    this.SubmittedForm = false;
  }


  onSubmitCreateJoke(newJoke : CreateJoke){
    //we can use a model here to send to backend
     //TODO
    //temporary code because the json server won't accept anything that doesn't have an id
   
    console.log(newJoke); 
    //above is place holder code. 
   
    
    this.jokeService.postJoke(newJoke).subscribe((response)=>{
      this.backEndResponse = Status.Success;
    },
    (error: HttpErrorResponse)=>{
      this.backEndResponse = Status.Fail;
    }
    )
  }
}
