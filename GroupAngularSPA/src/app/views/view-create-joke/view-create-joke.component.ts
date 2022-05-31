import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { JokeDataService } from 'src/app/core/services/joke-data.service';
import { Status } from 'src/app/shared/status';
@Component({
  selector: 'app-view-create-joke',
  templateUrl: './view-create-joke.component.html',
  styleUrls: ['./view-create-joke.component.css']
})
export class ViewCreateJokeComponent implements OnInit {
  backEndResponse!:Status;
  SubmittedForm!: boolean;
  constructor(private jokeService : JokeDataService) { }

  ngOnInit(): void {
    this.backEndResponse = Status.None;
    this.SubmittedForm = false;
  }
  isFormSubmitted(createButtonClicked : boolean ){
    this.SubmittedForm = createButtonClicked;
  }


  onSubmitCreateJoke(newJokeForm : FormGroup){
    //we can use a model here to send to backend
     //TODO
     
    console.log(newJokeForm.value);

    //temporary code because the json server won't accept anything that doesn't have an id
    const newJoke = {
      answer : newJokeForm.value.answer,
      question: newJokeForm.value.question,
      createdBy: newJokeForm.value.createdBy,
      id: Math.random()
    }
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
